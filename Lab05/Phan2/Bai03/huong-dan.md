# Bài 3: Kết nối MySQL với PHPMyAdmin

## Yêu cầu
- Chạy MySQL và PHPMyAdmin với Docker Compose.
- PHPMyAdmin chạy trên cổng 8081.

## Cấu trúc thư mục
```
Bai03/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **mysql service**: Chạy MySQL 8.0, cấu hình user/password/database.
- **phpmyadmin service**:
  - `PMA_HOST: mysql`: Trỏ PHPMyAdmin đến service MySQL.
  - `ports: "8081:80"`: PHPMyAdmin chạy trên cổng 8081.
  - `depends_on`: Đảm bảo MySQL khởi động trước.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai03
```bash
cd Bai03
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Truy cập PHPMyAdmin
- Mở trình duyệt: **http://localhost:8081**
- Đăng nhập:
  - Server: `mysql`
  - Username: `user`
  - Password: `password`

### Bước 5: Kiểm tra
- Sau khi đăng nhập, bạn sẽ thấy database `mydb` ở cột bên trái.
- Có thể tạo bảng, thêm dữ liệu qua giao diện web.

### Bước 6: Dừng container
```bash
docker-compose down
```
