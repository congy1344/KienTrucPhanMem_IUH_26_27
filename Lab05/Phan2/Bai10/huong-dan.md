# Bài 10: Lưu trữ dữ liệu với Docker Volumes

## Yêu cầu
- Chạy MySQL và gắn volume để dữ liệu không bị mất.

## Cấu trúc thư mục
```
Bai10/
├── docker-compose.yml
└── huong-dan.md
```

## Giải thích file `docker-compose.yml`
- **volumes trong service**:
  - `mysql_data:/var/lib/mysql`: Gắn named volume vào thư mục dữ liệu MySQL.
  - Dữ liệu sẽ được lưu bên ngoài container.
- **volumes (top-level)**: Khai báo named volume `mysql_data`.

## Hướng dẫn chạy

### Bước 1: Di chuyển đến thư mục Bai10
```bash
cd Bai10
```

### Bước 2: Chạy Docker Compose
```bash
docker-compose up -d
```

### Bước 3: Tạo dữ liệu test
```bash
docker exec -it bai10-mysql mysql -u user -p
```
Nhập password: `password`

```sql
USE mydb;
CREATE TABLE students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100));
INSERT INTO students (name) VALUES ('Nguyen Van A'), ('Tran Thi B');
SELECT * FROM students;
EXIT;
```

### Bước 4: Dừng container (KHÔNG xóa volume)
```bash
docker-compose down
```

### Bước 5: Chạy lại và kiểm tra dữ liệu vẫn còn
```bash
docker-compose up -d
docker exec -it bai10-mysql mysql -u user -p
```
```sql
USE mydb;
SELECT * FROM students;
```
→ Dữ liệu vẫn còn nguyên!

### Bước 6: Xóa hoàn toàn (bao gồm volume)
```bash
docker-compose down -v
```

### Bước 7: Kiểm tra volume
```bash
docker volume ls
```
