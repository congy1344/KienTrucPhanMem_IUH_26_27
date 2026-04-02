# ==================================================
# Stage 1: Build - Biên dịch ứng dụng React
# ==================================================
FROM node:18-alpine AS builder

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file khai báo dependencies trước (tận dụng Docker cache layer)
COPY package.json package-lock.json* ./

# Cài đặt dependencies
RUN npm install --frozen-lockfile

# Sao chép toàn bộ source code vào container
COPY . .

# Build ứng dụng React ra thư mục /app/build
RUN npm run build

# ==================================================
# Stage 2: Serve - Chạy ứng dụng đã được build
# ==================================================
FROM node:18-alpine AS runner

# Cài đặt package "serve" để phục vụ file tĩnh
RUN npm install -g serve

# Thiết lập thư mục làm việc
WORKDIR /app

# Chỉ sao chép thư mục build từ stage builder (giảm kích thước image)
COPY --from=builder /app/build ./build

# Mở cổng 3000
EXPOSE 3000

# Khởi động server phục vụ ứng dụng React đã build
CMD ["serve", "-s", "build", "-l", "3000"]
