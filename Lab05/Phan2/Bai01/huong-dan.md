# Bài 1: Chạy một container đơn giản với Docker Compose

## Yêu cầu
- Tạo một container chạy Nginx bằng Docker Compose.
- Map cổng 8080 của máy host với cổng 80 của container.

## Cấu trúc thư mục
```
Bai01/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **version**: Phiên bản Docker Compose file format.
- **services**: Định nghĩa các dịch vụ (container) cần chạy.
- **nginx**: Tên dịch vụ.
- **image: nginx:latest**: Sử dụng image Nginx phiên bản mới nhất.
- **ports: "8080:80"**: Map cổng 8080 máy host → cổng 80 container.

## Hướng dẫn chạy

### Bước 1: Mở terminal và di chuyển đến thư mục Bai01
```bash
cd Bai01
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra
- Mở trình duyệt, truy cập: **http://localhost:8080**
- Nếu thấy trang chào mừng Nginx → thành công!

### Bước 4: Kiểm tra container đang chạy
```bash
docker-compose ps
```

### Bước 5: Xem logs
```bash
docker-compose logs nginx
```

### Bước 6: Dừng container
```bash
docker-compose down
```
