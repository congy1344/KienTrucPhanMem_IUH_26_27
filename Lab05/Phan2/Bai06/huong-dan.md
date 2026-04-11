# Bài 6: Chạy WordPress với MySQL

## Yêu cầu
- Chạy WordPress với MySQL bằng Docker Compose.

## Cấu trúc thư mục
```
Bai06/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **mysql service**: Chạy MySQL 8.0, tạo database `wordpress` cho WordPress.
- **wordpress service**:
  - `WORDPRESS_DB_HOST`: Trỏ đến service MySQL.
  - `WORDPRESS_DB_USER/PASSWORD/NAME`: Thông tin kết nối database.
  - `ports: "8080:80"`: WordPress chạy trên cổng 8080.
  - `depends_on`: MySQL khởi động trước WordPress.
- **volumes**: `mysql_data` lưu trữ dữ liệu MySQL bền vững.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai06
```bash
cd Bai06
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Đợi khởi động
Đợi khoảng 30 giây để MySQL và WordPress khởi động hoàn tất.

### Bước 4: Truy cập WordPress
- Mở trình duyệt: **http://localhost:8080**
- Bạn sẽ thấy trang cài đặt WordPress.
- Chọn ngôn ngữ, điền thông tin site, tạo tài khoản admin.

### Bước 5: Kiểm tra container
```bash
docker-compose ps
```

### Bước 6: Dừng container
```bash
docker-compose down
```

> **Lưu ý**: Dữ liệu MySQL được lưu trong volume `mysql_data`, khi chạy lại sẽ không mất dữ liệu. Muốn xóa hoàn toàn:
> ```bash
> docker-compose down -v
> ```
