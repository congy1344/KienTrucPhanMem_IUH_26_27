# Bài 9: Chạy ứng dụng Python Flask với Docker Compose

## Yêu cầu
- Chạy ứng dụng Flask đơn giản với Docker Compose.

## Cấu trúc thư mục
```
Bai09/
├── docker-compose.yml
├── huong-dan.md
└── app/
    ├── Dockerfile
    ├── requirements.txt
    └── app.py
```

## Giải thích các file

### `app/app.py`
- Ứng dụng Flask đơn giản, lắng nghe trên cổng 5000.
- Endpoint `/`: Trả về JSON message.

### `app/requirements.txt`
- Danh sách thư viện Python cần cài: `flask==3.0.0`.

### `app/Dockerfile`
- Sử dụng Python 3.11 Slim.
- Cài dependencies từ `requirements.txt`.
- Chạy `app.py`.

### `docker-compose.yml`
- **build: ./app**: Build image từ Dockerfile.
- **ports: "5000:5000"**: Map cổng 5000.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai09
```bash
cd Bai09
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d --build
```

### Bước 3: Kiểm tra
- Mở trình duyệt: **http://localhost:5000**
- Kết quả: `{"message":"Hello from Python Flask with Docker Compose!"}`

### Bước 4: Xem logs
```bash
docker-compose logs flask-app
```

### Bước 5: Dừng container
```bash
docker-compose down
```
