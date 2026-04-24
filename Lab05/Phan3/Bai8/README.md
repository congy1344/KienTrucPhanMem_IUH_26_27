# Bai 8: Django + Celery + Redis

## Mục tiêu
Triển khai một ứng dụng Django với Celery worker và Redis làm message broker.

## Hướng dẫn chạy
```bash
cd Bai8
docker-compose up -d --build
```
- Django sẽ chạy trên `http://localhost:8000`.
- Celery worker sẽ tự động khởi động và kết nối tới Redis.
- Redis chạy trên cổng 6379 nội bộ.

## Cấu trúc thư mục
- `docker-compose.yml` – Định nghĩa các service Django, Celery và Redis.
- `Dockerfile` – Dockerfile cho Django (cũng dùng cho Celery).
- `app/` – Thư mục chứa mã nguồn Django (placeholder).
- `README.md` – Hướng dẫn này.

## Ghi chú
- Đảm bảo Docker daemon đang chạy.
- Thay đổi `DJANGO_SECRET_KEY` và các biến môi trường khác nếu cần.
