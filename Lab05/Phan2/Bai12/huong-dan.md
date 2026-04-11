# Bài 12: Giám sát container với Prometheus và Grafana

## Yêu cầu
- Chạy Prometheus, Grafana và Node Exporter bằng Docker Compose để giám sát hệ thống.

## Cấu trúc thư mục
```
Bai12/
├── docker-compose.yml
├── huong-dan.md
└── prometheus/
    └── prometheus.yml
```

## Giải thích các file

### `prometheus/prometheus.yml`
- Cấu hình Prometheus scrape metrics mỗi 15 giây.
- Job `prometheus`: Tự giám sát chính nó.
- Job `node-exporter`: Thu thập metrics từ Node Exporter.

### `docker-compose.yml`
- **prometheus**: Thu thập metrics, chạy trên cổng 9090.
- **grafana**: Dashboard hiển thị metrics, cổng 3000.
  - Login: admin/admin
- **node-exporter**: Thu thập metrics hệ thống (CPU, RAM, Disk...).

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai12
```bash
cd Bai12
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Truy cập Prometheus
- Mở trình duyệt: **http://localhost:9090**
- Vào **Status > Targets** để kiểm tra các job đang scrape.

### Bước 4: Truy cập Grafana
- Mở trình duyệt: **http://localhost:3000**
- Đăng nhập:
  - Username: `admin`
  - Password: `admin`

### Bước 5: Cấu hình Grafana Data Source
1. Vào **Configuration > Data Sources**.
2. Chọn **Add data source > Prometheus**.
3. URL: `http://prometheus:9090`
4. Click **Save & Test**.

### Bước 6: Tạo Dashboard
1. Vào **Create > Dashboard**.
2. Thêm panel mới.
3. Query: `node_cpu_seconds_total` hoặc `node_memory_MemTotal_bytes`.
4. Click **Apply**.

### Bước 7: Truy cập Node Exporter
- **http://localhost:9100/metrics** - Xem raw metrics.

### Bước 8: Dừng container
```bash
docker-compose down
```
