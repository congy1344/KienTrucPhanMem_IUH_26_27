# Bài 11: Chạy dịch vụ Postgres với Adminer

## Yêu cầu
- Chạy PostgreSQL và Adminer bằng Docker Compose.
- PostgreSQL phải có database tên `mydb`, user là `user`, password là `password`.
- Adminer chạy trên cổng 8083.

## Cấu trúc thư mục
```
Bai11/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **postgres service**:
  - `image: postgres:15`: PostgreSQL phiên bản 15.
  - `POSTGRES_DB/USER/PASSWORD`: Tạo database và user.
  - `ports: "5432:5432"`: Cổng PostgreSQL mặc định.
- **adminer service**:
  - Giao diện web quản lý database (hỗ trợ nhiều loại DB).
  - `ports: "8083:8080"`: Adminer chạy trên cổng 8083.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai11
```bash
cd Bai11
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Truy cập Adminer
- Mở trình duyệt: **http://localhost:8083**
- Đăng nhập:
  - System: `PostgreSQL`
  - Server: `postgres`
  - Username: `user`
  - Password: `password`
  - Database: `mydb`

### Bước 5: Test với PostgreSQL CLI
```bash
docker exec -it bai11-postgres psql -U user -d mydb
```
```sql
CREATE TABLE test (id SERIAL PRIMARY KEY, name VARCHAR(100));
INSERT INTO test (name) VALUES ('Hello PostgreSQL');
SELECT * FROM test;
\q
```

### Bước 6: Dừng container
```bash
docker-compose down
```
