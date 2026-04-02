# Sử dụng Python 3.11 phiên bản alpine để image nhẹ
FROM python:3.11-alpine

# Khai báo biến môi trường mặc định
ENV APP_ENV=development

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép source code vào container
COPY app.py .

# Chạy ứng dụng Python
CMD ["python", "app.py"]
