# Sử dụng Redis phiên bản mới nhất làm base image
FROM redis:latest

# Sao chép file cấu hình tùy chỉnh vào container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Mở cổng Redis mặc định
EXPOSE 6379

# Khởi động Redis với file cấu hình tùy chỉnh
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]
