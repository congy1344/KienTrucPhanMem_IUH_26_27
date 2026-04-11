# Bài 15: Giới hạn tài nguyên cho container

## Yêu cầu
- Giới hạn CPU và RAM cho một container Redis.

## Cấu trúc thư mục
```
Bai15/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **deploy.resources.limits**: Giới hạn tài nguyên tối đa.
  - `cpus: '0.5'`: Tối đa 50% CPU.
  - `memory: 256M`: Tối đa 256MB RAM.
- **deploy.resources.reservations**: Tài nguyên tối thiểu được đảm bảo.
  - `cpus: '0.25'`: Đảm bảo ít nhất 25% CPU.
  - `memory: 128M`: Đảm bảo ít nhất 128MB RAM.

> **Lưu ý**: Với Docker Compose v2, `deploy` hoạt động khi chạy với `docker compose` (không cần Swarm). Với Docker Compose v1, cần thêm `--compatibility` flag.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai15
```bash
cd Bai15
```

### Bước 2: Chạy Docker Compose
Với Docker Compose v2:
```bash
docker compose up -d
```

Với Docker Compose v1 (cần flag `--compatibility`):
```bash
docker-compose --compatibility up -d
```

### Bước 3: Kiểm tra giới hạn tài nguyên
```bash
docker stats bai15-redis --no-stream
```
Cột `MEM LIMIT` sẽ hiện `256M`.

### Bước 4: Kiểm tra chi tiết
```bash
docker inspect bai15-redis | findstr -i "Memory\|NanoCpus"
```
Trên Linux/Mac:
```bash
docker inspect bai15-redis | grep -i "Memory\|NanoCpus"
```

### Bước 5: Test Redis vẫn hoạt động bình thường
```bash
docker exec -it bai15-redis redis-cli
```
```bash
SET test "Hello"
GET test
INFO memory
exit
```

### Bước 6: Dừng container
```bash
docker-compose down
```
