# Sử dụng PostgreSQL phiên bản 15 làm base image
FROM postgres:15

# Khai báo biến môi trường cho PostgreSQL
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin123
ENV POSTGRES_DB=postgres

# Sao chép file SQL vào thư mục init của PostgreSQL
# Mọi file .sql trong thư mục này sẽ tự động chạy khi container khởi động lần đầu
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# Mở cổng PostgreSQL mặc định
EXPOSE 5432
