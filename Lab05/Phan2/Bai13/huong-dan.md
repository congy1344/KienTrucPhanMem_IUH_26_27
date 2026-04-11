# Bài 13: Chạy ứng dụng React với Nginx

## Yêu cầu
- Chạy một ứng dụng React và serve nó bằng Nginx.

## Cấu trúc thư mục
```
Bai13/
├── docker-compose.yml
├── huong-dan.md
├── html/
│   └── index.html
└── nginx/
    └── default.conf
```

## Giải thích các file

### `html/index.html`
- Ứng dụng React đơn giản load trực tiếp từ CDN.
- Có component App với state counter (click để tăng số).

### `nginx/default.conf`
- Cấu hình Nginx serve static files từ `/usr/share/nginx/html`.
- `try_files`: Hỗ trợ React Router (SPA routing).

### `docker-compose.yml`
- Mount thư mục `html` vào Nginx.
- Mount `default.conf` để override cấu hình mặc định.
- Chạy trên cổng 8080.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai13
```bash
cd Bai13
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra
- Mở trình duyệt: **http://localhost:8080**
- Bạn sẽ thấy ứng dụng React với nút bấm đếm số.
- Click nút "Clicked: X times" để test.

### Bước 4: Chỉnh sửa React app
- Sửa file `html/index.html` trực tiếp.
- Refresh trình duyệt để thấy thay đổi (do dùng volume mount).

### Bước 5: Dừng container
```bash
docker-compose down
```
