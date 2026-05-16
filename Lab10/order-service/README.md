# Order Service

```bash
npm install
npm start
```

Runs on `http://localhost:3003`.

## APIs

- `GET /health`
- `POST /orders`
- `GET /orders`
- `GET /orders/:id`
- `PATCH /orders/:id/status`

`POST /orders` calls User Service and Food Service with retry, circuit breaker, and rate limiting.

