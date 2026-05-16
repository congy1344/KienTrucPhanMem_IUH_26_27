const express = require("express");
const cors = require("cors");
const axios = require("axios");
const axiosRetryModule = require("axios-retry");
const rateLimit = require("express-rate-limit");
const CircuitBreaker = require("opossum");

const axiosRetry = axiosRetryModule.default || axiosRetryModule;
const app = express();
const port = Number(process.env.PORT || 3003);
const userServiceUrl = process.env.USER_SERVICE_URL || "http://localhost:3001";
const foodServiceUrl = process.env.FOOD_SERVICE_URL || "http://localhost:3002";

app.use(cors());
app.use(express.json());

const http = axios.create({ timeout: 2500 });
axiosRetry(http, {
  retries: 2,
  retryDelay: axiosRetry.exponentialDelay,
  retryCondition: (error) => {
    return axiosRetry.isNetworkOrIdempotentRequestError(error) || error.code === "ECONNABORTED";
  }
});

const breakerOptions = {
  timeout: 4000,
  errorThresholdPercentage: 50,
  resetTimeout: 10000
};

const validateUserBreaker = new CircuitBreaker(async (userId) => {
  try {
    const response = await http.get(`${userServiceUrl}/users/${userId}/validate`);
    return response.data;
  } catch (error) {
    if (error.response?.status === 404) {
      return { valid: false, message: "User not found" };
    }

    throw error;
  }
}, breakerOptions);

const getFoodBreaker = new CircuitBreaker(async (foodId) => {
  try {
    const response = await http.get(`${foodServiceUrl}/foods/${foodId}`);
    return response.data;
  } catch (error) {
    if (error.response?.status === 404) {
      return { notFound: true, message: "Food not found" };
    }

    throw error;
  }
}, breakerOptions);

const createOrderLimiter = rateLimit({
  windowMs: 60 * 1000,
  limit: 20,
  standardHeaders: true,
  legacyHeaders: false,
  message: { message: "Too many order requests, please try again later" }
});

const orders = [];

function toServiceError(error) {
  if (error.response) {
    return {
      status: error.response.status,
      message: error.response.data?.message || "Downstream service returned an error"
    };
  }

  return {
    status: 503,
    message: error.message || "Downstream service is unavailable"
  };
}

app.get("/health", (_req, res) => {
  res.json({
    service: "order-service",
    status: "UP",
    dependencies: {
      userServiceUrl,
      foodServiceUrl,
      userCircuit: validateUserBreaker.status.stats,
      foodCircuit: getFoodBreaker.status.stats
    }
  });
});

app.post("/orders", createOrderLimiter, async (req, res) => {
  const { userId, items } = req.body;

  if (!userId) {
    return res.status(400).json({ message: "userId is required" });
  }

  if (!Array.isArray(items) || items.length === 0) {
    return res.status(400).json({ message: "items must be a non-empty array" });
  }

  const invalidItem = items.find(
    (item) => !item.foodId || typeof item.quantity !== "number" || item.quantity <= 0
  );

  if (invalidItem) {
    return res.status(400).json({ message: "Each item must contain foodId and positive quantity" });
  }

  try {
    const userResult = await validateUserBreaker.fire(userId);

    if (!userResult.valid) {
      return res.status(404).json({ message: "User not found" });
    }

    const enrichedItems = [];
    for (const item of items) {
      const food = await getFoodBreaker.fire(item.foodId);

      if (food.notFound) {
        return res.status(404).json({ message: "Food not found" });
      }

      if (!food.available) {
        return res.status(400).json({ message: `${food.name} is not available` });
      }

      enrichedItems.push({
        foodId: food.id,
        name: food.name,
        price: food.price,
        quantity: item.quantity,
        subtotal: food.price * item.quantity
      });
    }

    const totalAmount = enrichedItems.reduce((sum, item) => sum + item.subtotal, 0);
    const order = {
      id: `o-${Date.now()}`,
      userId,
      userName: userResult.user.name,
      items: enrichedItems,
      totalAmount,
      status: "CREATED",
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    };

    orders.push(order);
    return res.status(201).json(order);
  } catch (error) {
    const serviceError = toServiceError(error);
    return res.status(serviceError.status).json({ message: serviceError.message });
  }
});

app.get("/orders", (_req, res) => {
  res.json(orders);
});

app.get("/orders/:id", (req, res) => {
  const order = orders.find((item) => item.id === req.params.id);

  if (!order) {
    return res.status(404).json({ message: "Order not found" });
  }

  return res.json(order);
});

app.patch("/orders/:id/status", (req, res) => {
  const { status } = req.body;
  const allowedStatuses = ["CREATED", "PAID", "CANCELLED", "FAILED"];

  if (!allowedStatuses.includes(status)) {
    return res.status(400).json({ message: `Status must be one of ${allowedStatuses.join(", ")}` });
  }

  const order = orders.find((item) => item.id === req.params.id);

  if (!order) {
    return res.status(404).json({ message: "Order not found" });
  }

  order.status = status;
  order.updatedAt = new Date().toISOString();

  return res.json(order);
});

app.listen(port, () => {
  console.log(`Order Service running at http://localhost:${port}`);
});
