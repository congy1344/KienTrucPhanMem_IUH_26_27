import cors from "cors";
import dotenv from "dotenv";
import express, { Request, Response } from "express";

dotenv.config();

type Booking = {
  id: string;
  userId: string;
  tourId: string;
  tourName: string;
  quantity: number;
  totalAmount: number;
  status: "CREATED" | "PAID" | "FAILED";
  createdAt: string;
};

type Payment = {
  id: string;
  bookingId: string;
  amount: number;
  status: "SUCCESS" | "FAILED";
  message: string;
  paidAt: string;
};

const bookings: Booking[] = [];
const payments: Payment[] = [];

const app = express();
const port = Number(process.env.PORT ?? 8083);
const host = process.env.HOST ?? "0.0.0.0";

app.use(cors());
app.use(express.json());

app.get("/health", (_req: Request, res: Response) => {
  res.json({ service: "booking-payment-service", status: "UP" });
});

app.post("/bookings", (req: Request, res: Response) => {
  const { userId, tourId, tourName, quantity, totalAmount } = req.body as {
    userId?: string;
    tourId?: string;
    tourName?: string;
    quantity?: number;
    totalAmount?: number;
  };

  if (!userId || !tourId || !tourName || !quantity || !totalAmount) {
    return res.status(400).json({ message: "Thieu thong tin tao booking" });
  }

  const booking: Booking = {
    id: `b-${Date.now()}`,
    userId,
    tourId,
    tourName,
    quantity,
    totalAmount,
    status: "CREATED",
    createdAt: new Date().toISOString()
  };

  bookings.push(booking);
  return res.status(201).json(booking);
});

app.post("/payments", (req: Request, res: Response) => {
  const { bookingId, amount } = req.body as { bookingId?: string; amount?: number };

  if (!bookingId || !amount) {
    return res.status(400).json({ message: "Thieu thong tin thanh toan" });
  }

  const booking = bookings.find((item) => item.id === bookingId);

  if (!booking) {
    return res.status(404).json({ message: "Khong tim thay booking de thanh toan" });
  }

  const success = Math.random() >= 0.3;
  const payment: Payment = {
    id: `p-${Date.now()}`,
    bookingId,
    amount,
    status: success ? "SUCCESS" : "FAILED",
    message: success ? "Thanh toan thanh cong" : "Thanh toan that bai, vui long thu lai",
    paidAt: new Date().toISOString()
  };

  booking.status = success ? "PAID" : "FAILED";
  payments.push(payment);

  if (!success) {
    return res.status(402).json(payment);
  }

  return res.status(201).json(payment);
});

app.listen(port, host, () => {
  console.log(`Booking + Payment Service running at http://${host}:${port}`);
});
