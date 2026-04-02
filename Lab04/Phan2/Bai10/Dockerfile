# Sử dụng PHP 8.2 tích hợp sẵn Apache
FROM php:8.2-apache

# Khai báo biến môi trường
ENV APP_ENV=development

# Kích hoạt module rewrite của Apache (cần cho routing)
RUN a2enmod rewrite

# Thiết lập thư mục gốc Apache trỏ đến /var/www/html
WORKDIR /var/www/html

# Sao chép mã nguồn vào container (dùng khi build image)
# Khi phát triển, thư mục này sẽ được mount từ máy host (xem lệnh docker run bên dưới)
COPY src/ .

# Mở cổng 80
EXPOSE 80
