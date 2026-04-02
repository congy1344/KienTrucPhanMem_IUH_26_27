# ==================================================
# Stage 1: BUILD
# Dung node:18 (image day du) de cai dependencies va bien dich TypeScript
# ==================================================
FROM node:18 AS builder

# Thiet lap thu muc lam viec
WORKDIR /app

# Copy file khai bao dependencies truoc (tan dung Docker cache)
COPY package.json package-lock.json* ./

# Cai toan bo dependencies (ke ca devDependencies de build TypeScript)
RUN npm install

# Copy toan bo source code
COPY . .

# Bien dich TypeScript -> JavaScript, output ra thu muc /app/dist
RUN npm run build

# ==================================================
# Stage 2: RUN
# Dung node:18-alpine (image nhe) de chay ung dung da build
# ==================================================
FROM node:18-alpine AS runner

# Thiet lap thu muc lam viec
WORKDIR /app

# Copy file khai bao dependencies
COPY package.json package-lock.json* ./

# Chi cai production dependencies (khong can devDependencies nua)
RUN npm install --omit=dev

# Chi copy thu muc dist da bien dich tu stage builder
COPY --from=builder /app/dist ./dist

# Mo cong 3000
EXPOSE 3000

# Chay ung dung tu file da bien dich
CMD ["node", "dist/index.js"]
