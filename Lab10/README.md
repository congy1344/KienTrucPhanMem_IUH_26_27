# Lab10 - Mini Food Ordering System

He thong dat mon noi bo chay local tren mot may, gom 5 folder rieng:

- `frontend` - ReactJS + Axios
- `user-service` - Express, register/login/users/validate
- `food-service` - Express, CRUD foods, seed data
- `order-service` - Express, create orders, REST calls with retry, circuit breaker, rate limiter
- `payment-notification-service` - Express, payment and console notification

## Ports

| App | URL |
| --- | --- |
| Frontend | `http://localhost:5173` |
| User Service | `http://localhost:3001` |
| Food Service | `http://localhost:3002` |
| Order Service | `http://localhost:3003` |
| Payment + Notification Service | `http://localhost:3004` |

## Install

Run once in each folder:

```bash
cd user-service
npm install

cd ../food-service
npm install

cd ../order-service
npm install

cd ../payment-notification-service
npm install

cd ../frontend
npm install
```

## Run

Open 5 terminals:

```bash
cd Lab10/user-service
npm start
```

```bash
cd Lab10/food-service
npm start
```

```bash
cd Lab10/order-service
npm start
```

```bash
cd Lab10/payment-notification-service
npm start
```

```bash
cd Lab10/frontend
npm run dev
```

Or on Windows PowerShell, after installing dependencies:

```powershell
.\start-all.ps1
```

Stop all local apps:

```powershell
.\stop-all.ps1
```

## Demo Account

- Email: `user@food.local`
- Password: `123456`

Admin:

- Email: `admin@food.local`
- Password: `123456`

## Main Flow

1. Open `http://localhost:5173`
2. Login or register a user
3. Add food to cart
4. Create order
5. Select order and payment method
6. Pay order
7. Check `payment-notification-service` console for notification log

## Health Checks

- `GET http://localhost:3001/health`
- `GET http://localhost:3002/health`
- `GET http://localhost:3003/health`
- `GET http://localhost:3004/health`
