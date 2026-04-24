const express = require('express');
const mongoose = require('mongoose');

const app = express();
const port = 3000;
const mongoUrl = process.env.MONGO_URL || 'mongodb://mongo:27017/mydb';

// Kết nối tới MongoDB
mongoose.connect(mongoUrl)
  .then(() => console.log('✅ Đã kết nối tới MongoDB'))
  .catch(err => console.error('❌ Lỗi kết nối MongoDB:', err));

app.get('/', (req, res) => {
  res.send('<h1>Hello từ Node.js và MongoDB!</h1>');
});

app.listen(port, () => {
  console.log(`🚀 Server đang chạy tại http://localhost:${port}`);
});
