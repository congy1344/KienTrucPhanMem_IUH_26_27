const express = require("express");
const cors = require("cors");
const axios = require("axios");

const app = express();
const port = Number(process.env.PORT || 3004);
const orderServiceUrl = process.env.ORDER_SERVICE_URL || "http://localhost:3003";

app.use(cors());
app.use(express.json());

const payments = [];

app.get("/health", (_req, res) => {
  res.json({ service: "payment-notification-service", status: "UP", orderServiceUrl });
});

app.post("/payments", async (req, res) => {
  const { orderId, method } = req.body;
  const allowedMethods = ["COD", "BANKING"];

  if (!orderId) {
    return res.status(400).json({ message: "orderId is required" });
  }

  if (!allowedMethods.includes(method)) {
    return res.status(400).json({ message: "method must be COD or BANKING" });
  }

  try {
    const orderResponse = await axios.get(`${orderServiceUrl}/orders/${orderId}`, { timeout: 2500 });
    const order = orderResponse.data;

    if (order.status === "PAID") {
      return res.status(409).json({ message: "Order is already paid" });
    }

    const payment = {
      id: `p-${Date.now()}`,
      orderId,
      userId: order.userId,
      amount: order.totalAmount,
      method,
      status: "SUCCESS",
      paidAt: new Date().toISOString()
    };

    const updatedOrder = await axios.patch(
      `${orderServiceUrl}/orders/${orderId}/status`,
      { status: "PAID" },
      { timeout: 2500 }
    );

    payments.push(payment);
    console.log(`User ${order.userId} da dat don #${order.id} thanh cong`);

    return res.status(201).json({ payment, order: updatedOrder.data });
  } catch (error) {
    if (error.response) {
      return res
        .status(error.response.status)
        .json({ message: error.response.data?.message || "Order Service returned an error" });
    }

    return res.status(503).json({ message: "Order Service is unavailable" });
  }
});

app.get("/payments", (_req, res) => {
  res.json(payments);
});

app.listen(port, () => {
  console.log(`Payment + Notification Service running at http://localhost:${port}`);
});
