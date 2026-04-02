import express, { Request, Response } from 'express';

const app = express();
const PORT = 3000;

app.get('/', (req: Request, res: Response) => {
  res.send('Xin chao! Ung dung Node.js + TypeScript chay trong Docker (multi-stage build).');
});

app.get('/info', (req: Request, res: Response) => {
  res.json({
    app: 'nodejs-multistage-app',
    stage: 'production',
    runtime: process.version,
    uptime: process.uptime().toFixed(2) + 's',
  });
});

app.listen(PORT, () => {
  console.log(`Server dang chay tai http://localhost:${PORT}`);
});
