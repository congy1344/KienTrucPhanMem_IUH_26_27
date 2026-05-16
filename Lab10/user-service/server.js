const express = require("express");
const cors = require("cors");

const app = express();
const port = Number(process.env.PORT || 3001);

app.use(cors());
app.use(express.json());

const users = [
  {
    id: "u-1",
    name: "Admin",
    email: "admin@food.local",
    password: "123456",
    role: "ADMIN",
    createdAt: new Date().toISOString()
  },
  {
    id: "u-2",
    name: "Demo User",
    email: "user@food.local",
    password: "123456",
    role: "USER",
    createdAt: new Date().toISOString()
  }
];

function publicUser(user) {
  const { password, ...safeUser } = user;
  return safeUser;
}

function createToken(user) {
  return Buffer.from(`${user.id}:${user.role}:${Date.now()}`).toString("base64");
}

app.get("/health", (_req, res) => {
  res.json({ service: "user-service", status: "UP" });
});

app.post("/register", (req, res) => {
  const { name, email, password, role = "USER" } = req.body;

  if (!name || !email || !password) {
    return res.status(400).json({ message: "Name, email, and password are required" });
  }

  if (!/^\S+@\S+\.\S+$/.test(email)) {
    return res.status(400).json({ message: "Email is invalid" });
  }

  if (password.length < 6) {
    return res.status(400).json({ message: "Password must contain at least 6 characters" });
  }

  if (!["USER", "ADMIN"].includes(role)) {
    return res.status(400).json({ message: "Role must be USER or ADMIN" });
  }

  const existed = users.some((user) => user.email.toLowerCase() === email.toLowerCase());
  if (existed) {
    return res.status(409).json({ message: "Email already exists" });
  }

  const user = {
    id: `u-${Date.now()}`,
    name,
    email,
    password,
    role,
    createdAt: new Date().toISOString()
  };

  users.push(user);
  return res.status(201).json({ user: publicUser(user), token: createToken(user) });
});

app.post("/login", (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: "Email and password are required" });
  }

  const user = users.find(
    (item) => item.email.toLowerCase() === email.toLowerCase() && item.password === password
  );

  if (!user) {
    return res.status(401).json({ message: "Invalid email or password" });
  }

  return res.json({ user: publicUser(user), token: createToken(user) });
});

app.get("/users", (_req, res) => {
  res.json(users.map(publicUser));
});

app.get("/users/:id/validate", (req, res) => {
  const user = users.find((item) => item.id === req.params.id);

  if (!user) {
    return res.status(404).json({ valid: false, message: "User not found" });
  }

  return res.json({ valid: true, user: publicUser(user) });
});

app.listen(port, () => {
  console.log(`User Service running at http://localhost:${port}`);
});
