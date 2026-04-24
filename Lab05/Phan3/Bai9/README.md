# Bai 9: Nextcloud với MariaDB + Redis Caching

## Mục tiêu
Triển khai Nextcloud (self-hosted cloud) kết nối với cơ sở dữ liệu MariaDB và sử dụng Redis làm cache.

## Hướng dẫn chạy
```bash
cd Bai9
docker-compose up -d
```
- Truy cập Nextcloud tại `http://localhost:8080` (ánh xạ từ cổng 80 của container).
- Lần đầu truy cập, hệ thống có thể yêu cầu tạo tài khoản admin và cấu hình database là MySQL/MariaDB với thông tin đã cài đặt trong `docker-compose.yml`.

## Cấu trúc thư mục
- `docker-compose.yml` – Cấu hình các service Nextcloud, MariaDB và Redis.
- Volumes được quản lý thông qua Docker để duy trì dữ liệu lưu trữ.
