# Bai 7: Elasticsearch + Kibana

## Mục tiêu
Triển khai ELK stack đơn giản để thu thập và hiển thị log.

## Hướng dẫn chạy
```bash
cd Bai7
docker-compose up -d
```
- Elasticsearch sẽ chạy trên `http://localhost:9200`.
- Kibana sẽ chạy trên `http://localhost:5601` và tự động kết nối tới Elasticsearch.

## Cấu trúc thư mục
- `docker-compose.yml` – Định nghĩa service Elasticsearch và Kibana, volume và network.
- `elasticsearch.yml` – Cấu hình cơ bản cho Elasticsearch (đặt trong thư mục `config`).
- `README.md` – Hướng dẫn này.

## Ghi chú
- Đảm bảo máy có ít nhất 2GB RAM để chạy Elasticsearch.
- Thay đổi mật khẩu `ELASTIC_PASSWORD` nếu cần bảo mật.
