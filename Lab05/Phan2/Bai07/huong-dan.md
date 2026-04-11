# Bài 7: Chạy MongoDB với Docker Compose

## Yêu cầu
- Chạy MongoDB và Mongo Express để quản lý.

## Cấu trúc thư mục
```
Bai07/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **mongodb service**:
  - `MONGO_INITDB_ROOT_USERNAME/PASSWORD`: Tài khoản admin cho MongoDB.
  - `ports: "27017:27017"`: Cổng MongoDB mặc định.
- **mongo-express service**:
  - Giao diện web để quản lý MongoDB.
  - `ME_CONFIG_MONGODB_SERVER`: Trỏ đến service MongoDB.
  - `ports: "8082:8081"`: Mongo Express chạy trên cổng 8082.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai07
```bash
cd Bai07
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra container
```bash
docker-compose ps
```

### Bước 4: Truy cập Mongo Express
- Mở trình duyệt: **http://localhost:8082**
- Đăng nhập (nếu được yêu cầu):
  - Username: `admin`
  - Password: `pass`

### Bước 5: Test với MongoDB CLI
```bash
docker exec -it bai07-mongodb mongosh -u admin -p password
```
```javascript
show dbs
use testdb
db.users.insertOne({ name: "Test User", email: "test@example.com" })
db.users.find()
```

### Bước 6: Dừng container
```bash
docker-compose down
```
