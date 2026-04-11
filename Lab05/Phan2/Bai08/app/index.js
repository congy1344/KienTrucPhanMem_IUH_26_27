; const express = require('express');
const mysql = require('mysql2');

const app = express();
const PORT = 3000;

let connection;

// Kết nối MySQL
function connectWithRetry() {
  connection = mysql.createConnection({
    host: 'mysql',
    user: 'user',
    password: 'password',
    database: 'mydb'
  });

  connection.connect((err) => {
    if (err) {
      console.log('Waiting for MySQL...', err.message);
      setTimeout(connectWithRetry, 5000);
    } else {
      console.log('Connected to MySQL!');
    }
  });
}

connectWithRetry();

app.get('/', (req, res) => {
  res.json({ message: 'Node.js connected to MySQL with Docker Compose!' });
});

app.get('/db-test', (req, res) => {
  connection.query('SELECT 1 + 1 AS result', (err, results) => {
    if (err) {
      res.status(500).json({ error: err.message });
    } else {
      res.json({ result: results[0].result, message: 'Database connection OK!' });
    }
  });
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
