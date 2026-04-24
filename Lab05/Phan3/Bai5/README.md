# Bai 5: Multi-tier Voting App

## Mục tiêu
Triển khai một ứng dụng voting gồm 5 services:
1. **vote** – Frontend Python Flask (port 5000)
2. **result** – Backend Node.js (port 5001)
3. **redis** – Redis để lưu tạm votes
4. **worker** – Java worker xử lý votes từ Redis và ghi vào PostgreSQL
5. **postgres** – PostgreSQL lưu kết quả

## Hướng dẫn chạy
```bash
cd Bai5
docker-compose up -d --build
```
- Truy cập frontend tại `http://localhost:5000`
- Backend API tại `http://localhost:5001`

## Cấu trúc thư mục
- `docker-compose.yml` – Định nghĩa các service, volume và network.
- `vote/` – Mã nguồn Flask (placeholder).
- `result/` – Mã nguồn Node.js (placeholder).
- `worker/` – Mã nguồn Java (placeholder).
- `README.md` – Hướng dẫn này.

## Ghi chú
- Các service được kết nối qua network `voting_net`.
- Volumes được dùng cho PostgreSQL và Redis để dữ liệu bền vững.
- Đảm bảo Docker daemon đang chạy.
