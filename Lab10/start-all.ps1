$root = Split-Path -Parent $MyInvocation.MyCommand.Path

Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$root\user-service'; npm start"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$root\food-service'; npm start"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$root\order-service'; npm start"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$root\payment-notification-service'; npm start"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$root\frontend'; npm run dev"

Write-Host "Started Lab10 services. Open http://localhost:5173"
