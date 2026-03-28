# 1. Sử dụng python:3.9 làm base image theo yêu cầu
FROM python:3.9

# 2. Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# 3. Copy file requirements.txt vào container trước
COPY requirements.txt .

# 4. Cài đặt các thư viện Python thông qua pip
RUN pip install --no-cache-dir -r requirements.txt

# 5. Copy toàn bộ mã nguồn còn lại (app.py) vào thư mục làm việc
COPY . .

# 6. Mở cổng 5000 để container có thể giao tiếp với bên ngoài
EXPOSE 5000

# 7. Lệnh khởi chạy ứng dụng Flask khi container bắt đầu chạy
CMD ["python", "app.py"]