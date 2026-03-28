# 1. Sử dụng node:18 làm base image theo yêu cầu
FROM node:18

# 2. Thiết lập thư mục làm việc mặc định bên trong container
WORKDIR /usr/src/app

# 3. Copy file package.json (và package-lock.json nếu có) vào container
COPY package*.json ./

# 4. Cài đặt các thư viện (mặc dù app này chưa cần, nhưng đây là best practice)
RUN npm install

# 5. Copy toàn bộ mã nguồn còn lại (app.js) vào thư mục làm việc
COPY . .

# 6. Thông báo container sẽ lắng nghe trên cổng 3000
EXPOSE 3000

# 7. Lệnh khởi chạy ứng dụng khi container bắt đầu chạy
CMD ["npm", "start"]