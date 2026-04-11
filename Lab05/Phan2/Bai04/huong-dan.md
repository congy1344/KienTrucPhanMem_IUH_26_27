# Bài 4: Chạy ứng dụng Node.js với Docker Compose

## Yêu cầu
- Chạy một ứng dụng Node.js đơn giản với Express.

## Cấu trúc thư mục
```
Bai04/
├── docker-compose.yml
├── huong-dan.md
└── app/
    ├── Dockerfile
    ├── package.json
    └── index.js
```

## Giải thích các file

### `app/index.js`
- Ứng dụng Express đơn giản, lắng nghe trên cổng 3000.
- Trả về JSON message khi truy cập `/`.

### `app/Dockerfile`
- Sử dụng Node.js 18 Alpine (image nhẹ).
- Copy `package.json`, cài dependencies, rồi copy source code.
- Expose cổng 3000 và chạy `npm start`.

### `docker-compose.yml`
- **build: ./app**: Build image từ Dockerfile trong thư mục `app`.
- **ports: "3000:3000"**: Map cổng 3000.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai04
```bash
cd Bai04
```

### Bước 2: Chạy Docker Compose (build và start)
```bash
docker-compose up -d --build
```

### Bước 3: Kiểm tra
- Mở trình duyệt: **http://localhost:3000**
- Kết quả: `{"message":"Hello from Node.js with Docker Compose!"}`

### Bước 4: Xem logs
```bash
docker-compose logs nodejs-app
```

### Bước 5: Dừng container
```bash
docker-compose down
```
