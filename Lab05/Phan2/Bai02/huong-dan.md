# Bài 2: Chạy MySQL với Docker Compose

## Yêu cầu
- Tạo một container chạy MySQL phiên bản 8.0.
- Đặt username là `user`, password là `password` và database là `mydb`.

## Cấu trúc thư mục
```
Bai02/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **image: mysql:8.0**: Sử dụng MySQL phiên bản 8.0.
- **environment**: Thiết lập biến môi trường:
  - `MYSQL_ROOT_PASSWORD`: Mật khẩu root.
  - `MYSQL_DATABASE`: Tên database tự động tạo.
  - `MYSQL_USER`: Username cho database.
  - `MYSQL_PASSWORD`: Password cho user.
- **ports: "3306:3306"**: Map cổng MySQL mặc định.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai02
```bash
cd Bai02
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Kết nối vào MySQL
```bash
docker exec -it bai02-mysql mysql -u user -p
```
Nhập password: `password`

### Bước 5: Kiểm tra database
```sql
SHOW DATABASES;
USE mydb;
SHOW TABLES;
```

### Bước 6: Dừng container
```bash
docker-compose down
```
