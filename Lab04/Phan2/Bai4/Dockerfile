# Sử dụng nginx:latest làm base image
FROM nginx:latest

# Xóa trang mặc định của Nginx
RUN rm -rf /usr/share/nginx/html/*

# Sao chép file index.html vào thư mục phục vụ web của Nginx
COPY index.html /usr/share/nginx/html/index.html

# Mở cổng 80 (cổng mặc định của Nginx)
EXPOSE 80

# Nginx tự động khởi động khi container chạy (lệnh mặc định của image)
CMD ["nginx", "-g", "daemon off;"]
