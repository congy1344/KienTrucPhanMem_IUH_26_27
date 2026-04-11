# Bài 8: Kết nối nhiều dịch vụ với Docker Compose

## Yêu cầu
- Chạy Node.js kết nối với MySQL.

## Cấu trúc thư mục
```
Bai08/
├── docker-compose.yml
├── huong-dan.md
└── app/
    ├── Dockerfile
    ├── package.json
    └── index.js
```

## Giải thích các file

### `app/index.js`
- Ứng dụng Express kết nối MySQL bằng thư viện `mysql2`.
- Có cơ chế **retry** kết nối (đợi MySQL sẵn sàng).
- Endpoint `/`: Trả về message chào mừng.
- Endpoint `/db-test`: Test kết nối database.

### `docker-compose.yml`
- **mysql service**: MySQL 8.0 với database `mydb`.
- **nodejs-app service**: Build từ `./app`, kết nối MySQL qua hostname `mysql`.
- **depends_on**: Node.js chỉ start sau khi MySQL đã start.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai08
```bash
cd Bai08
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d --build
```

### Bước 3: Đợi khởi động
Đợi khoảng 20-30 giây để MySQL khởi động hoàn tất và Node.js kết nối thành công.

### Bước 4: Kiểm tra logs
```bash
docker-compose logs nodejs-app
```
Tìm dòng: `Connected to MySQL!`

### Bước 5: Test ứng dụng
- Mở trình duyệt: **http://localhost:3000**
- Test kết nối DB: **http://localhost:3000/db-test**
- Kết quả mong đợi: `{"result":2,"message":"Database connection OK!"}`

### Bước 6: Dừng container
```bash
docker-compose down
```
