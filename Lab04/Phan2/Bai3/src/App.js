import React from 'react';
import './App.css';

function App() {
  return (
    <div className="app">
      <header className="app-header">
        <h1>React App chạy trong Docker</h1>
        <p>Ứng dụng React được build và chạy bằng Docker với <code>node:18-alpine</code></p>
        <div className="badge">
          <span>Running on Docker</span>
        </div>
      </header>
    </div>
  );
}

export default App;
