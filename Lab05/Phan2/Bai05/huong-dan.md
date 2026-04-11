# Bài 5: Chạy Redis với Docker Compose

## Yêu cầu
- Chạy một container Redis trên cổng 6379.

## Cấu trúc thư mục
```
Bai05/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **image: redis:latest**: Sử dụng Redis phiên bản mới nhất.
- **ports: "6379:6379"**: Map cổng Redis mặc định.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai05
```bash
cd Bai05
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Kết nối vào Redis CLI
```bash
docker exec -it bai05-redis redis-cli
```

### Bước 5: Test Redis
```bash
SET mykey "Hello Docker Compose"
GET mykey
```
Kết quả: `"Hello Docker Compose"`

### Bước 6: Thoát Redis CLI
```bash
exit
```

### Bước 7: Dừng container
```bash
docker-compose down
```
