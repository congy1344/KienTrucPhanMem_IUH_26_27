# Bài 14: Cấu hình mạng riêng giữa các container

## Yêu cầu
- Chạy 2 container có thể giao tiếp với nhau trong một mạng riêng.

## Cấu trúc thư mục
```
Bai14/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **app1, app2**: Hai container Nginx cùng nằm trong một mạng riêng.
- **networks**:
  - `my_private_network`: Mạng riêng dạng bridge.
  - Các container trong cùng network có thể gọi nhau bằng tên service.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai14
```bash
cd Bai14
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Kiểm tra mạng
```bash
docker network ls
```
Tìm network có tên chứa `my_private_network`.

### Bước 5: Test giao tiếp giữa 2 container
Từ container app1, ping đến app2:
```bash
docker exec -it bai14-app1 ping app2 -c 3
```

Từ container app2, ping đến app1:
```bash
docker exec -it bai14-app2 ping app1 -c 3
```

### Bước 6: Test kết nối HTTP
Từ app1, gọi HTTP đến app2:
```bash
docker exec -it bai14-app1 curl http://app2:80
```
→ Sẽ nhận được trang HTML mặc định của Nginx.

### Bước 7: Kiểm tra chi tiết network
```bash
docker network inspect bai14_my_private_network
```

### Bước 8: Dừng container
```bash
docker-compose down
```
