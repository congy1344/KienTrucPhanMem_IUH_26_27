const express = require("express");
const cors = require("cors");

const app = express();
const port = Number(process.env.PORT || 3002);

app.use(cors());
app.use(express.json());

let foods = [
  {
    id: "f-1",
    name: "Com ga xoi mo",
    description: "Com ga chien gion, dua leo, rau ram va nuoc mam gung",
    price: 45000,
    imageUrl: "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?auto=format&fit=crop&w=900&q=80",
    available: true
  },
  {
    id: "f-2",
    name: "Bun bo Hue",
    description: "To bun bo dam vi voi cha, thit bo va sa te",
    price: 55000,
    imageUrl: "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&w=900&q=80",
    available: true
  },
  {
    id: "f-3",
    name: "Banh mi thit nuong",
    description: "Banh mi nong, thit nuong, do chua va pate",
    price: 28000,
    imageUrl: "https://images.unsplash.com/photo-1606755962773-d324e0a13086?auto=format&fit=crop&w=900&q=80",
    available: true
  },
  {
    id: "f-4",
    name: "Tra dao cam sa",
    description: "Tra dao mat lanh cho bua trua van phong",
    price: 25000,
    imageUrl: "https://images.unsplash.com/photo-1556679343-c7306c1976bc?auto=format&fit=crop&w=900&q=80",
    available: true
  }
];

function validateFoodPayload(payload, partial = false) {
  const errors = [];

  if (!partial || payload.name !== undefined) {
    if (!payload.name || typeof payload.name !== "string") {
      errors.push("Food name is required");
    }
  }

  if (!partial || payload.price !== undefined) {
    if (typeof payload.price !== "number" || payload.price <= 0) {
      errors.push("Food price must be a positive number");
    }
  }

  if (payload.available !== undefined && typeof payload.available !== "boolean") {
    errors.push("Available must be boolean");
  }

  return errors;
}

app.get("/health", (_req, res) => {
  res.json({ service: "food-service", status: "UP" });
});

app.get("/foods", (_req, res) => {
  res.json(foods);
});

app.get("/foods/:id", (req, res) => {
  const food = foods.find((item) => item.id === req.params.id);

  if (!food) {
    return res.status(404).json({ message: "Food not found" });
  }

  return res.json(food);
});

app.post("/foods", (req, res) => {
  const errors = validateFoodPayload(req.body);

  if (errors.length > 0) {
    return res.status(400).json({ message: errors.join(", ") });
  }

  const food = {
    id: `f-${Date.now()}`,
    name: req.body.name,
    description: req.body.description || "",
    price: req.body.price,
    imageUrl: req.body.imageUrl || "",
    available: req.body.available !== false
  };

  foods.push(food);
  return res.status(201).json(food);
});

app.put("/foods/:id", (req, res) => {
  const food = foods.find((item) => item.id === req.params.id);

  if (!food) {
    return res.status(404).json({ message: "Food not found" });
  }

  const errors = validateFoodPayload(req.body, true);
  if (errors.length > 0) {
    return res.status(400).json({ message: errors.join(", ") });
  }

  Object.assign(food, {
    name: req.body.name ?? food.name,
    description: req.body.description ?? food.description,
    price: req.body.price ?? food.price,
    imageUrl: req.body.imageUrl ?? food.imageUrl,
    available: req.body.available ?? food.available
  });

  return res.json(food);
});

app.delete("/foods/:id", (req, res) => {
  const existed = foods.some((item) => item.id === req.params.id);

  if (!existed) {
    return res.status(404).json({ message: "Food not found" });
  }

  foods = foods.filter((item) => item.id !== req.params.id);
  return res.status(204).send();
});

app.listen(port, () => {
  console.log(`Food Service running at http://localhost:${port}`);
});
