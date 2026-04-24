# Bai 1: WordPress + MySQL

## Mục tiêu
Triển khai một stack WordPress kết nối với MySQL, sử dụng Docker volumes để lưu trữ dữ liệu bền vững.

## Hướng dẫn chạy
```bash
cd Bai1
docker-compose up -d
```
- WordPress sẽ chạy trên `http://localhost:8080` (cổng 8080 được ánh xạ tới cổng 80 của container).
- MySQL chạy trên cổng 3306 nội bộ, không cần expose ra ngoài.

## Cấu trúc thư mục
- `docker-compose.yml` – Định nghĩa các service WordPress và MySQL, volume và network.
- Volumes:
  - `db_data` lưu dữ liệu MySQL tại `/var/lib/mysql`.
  - `wp_data` lưu dữ liệu WordPress tại `/var/www/html`.

## Ghi chú
- Thay đổi các biến môi trường trong `docker-compose.yml` nếu muốn sử dụng mật khẩu hoặc tên DB khác.
- Đảm bảo Docker daemon đang chạy và có đủ tài nguyên.
