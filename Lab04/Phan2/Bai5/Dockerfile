# ==================================================
# Stage 1: Build - Biên dịch ứng dụng Go
# ==================================================
FROM golang:1.21-alpine AS builder

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file go.mod trước để tận dụng Docker cache layer
COPY go.mod ./

# Tải dependencies (nếu có)
RUN go mod download

# Sao chép toàn bộ source code
COPY . .

# Build ra file binary, tắt CGO để binary chạy được trên alpine tối giản
RUN CGO_ENABLED=0 GOOS=linux go build -o server .

# ==================================================
# Stage 2: Run - Chạy file binary đã build
# ==================================================
FROM alpine:latest AS runner

# Thiết lập thư mục làm việc
WORKDIR /app

# Chỉ sao chép file binary từ stage builder
COPY --from=builder /app/server .

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng
CMD ["./server"]
