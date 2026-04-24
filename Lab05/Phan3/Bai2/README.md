# Bai 2: Node.js + MongoDB

## Mục tiêu
Triển khai một ứng dụng Node.js đơn giản (REST API) kết nối tới MongoDB và sử dụng volume để lưu trữ dữ liệu.

## Hướng dẫn chạy
1. **Xây dựng và khởi động**
   ```bash
   cd Bai2
   docker-compose up -d --build
   ```
2. Ứng dụng Node.js sẽ lắng nghe trên cổng **3000** (`http://localhost:3000`).
3. MongoDB sẽ chạy trên cổng **27017** và dữ liệu được lưu trong volume `mongo_data`.
4. Kiểm tra healthcheck của MongoDB bằng cách truy cập `http://localhost:27017` (nếu bật).

## Cấu trúc dự án
- `Dockerfile` – Dockerfile cho ứng dụng Node.js.
- `docker-compose.yml` – Định nghĩa các service MongoDB và Node.js.
- `app/` – Thư mục chứa mã nguồn Node.js (ví dụ `index.js`, `package.json`).
