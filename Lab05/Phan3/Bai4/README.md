# Bai 4: Prometheus + Grafana Monitoring

## Mục tiêu
Giám sát các Docker containers bằng Prometheus và Grafana.

## Hướng dẫn chạy
```bash
cd Bai4
docker-compose up -d
```
- Truy cập Prometheus tại `http://localhost:9090`.
- Truy cập Grafana tại `http://localhost:3000` (username: `admin`, password: `admin`).

## Cấu trúc thư mục
- `docker-compose.yml` – Định nghĩa service Prometheus và Grafana, volume và network.
- `prometheus.yml` – Cấu hình Prometheus để thu thập metrics từ Docker.
- `README.md` – Hướng dẫn này.

## Ghi chú
- Đảm bảo Docker daemon đang chạy và có đủ tài nguyên (ít nhất 2GB RAM).
- Bạn có thể tùy chỉnh `prometheus.yml` để thêm các job scrape khác.
