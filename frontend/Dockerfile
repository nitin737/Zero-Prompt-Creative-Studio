# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copy root .env for build-time env vars (Vite needs them)
COPY .env ./
COPY frontend/package.json frontend/package-lock.json ./
RUN --mount=type=cache,target=/root/.npm npm ci

# Copy frontend source
COPY frontend/ .
RUN npm run build

# Production stage
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
# Default nginx port
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
