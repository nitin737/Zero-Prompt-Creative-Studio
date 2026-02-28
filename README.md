# Zero-Prompt Creative Studio

AI Image Generation Studio powered by Gemini 2.0 Flash Experimental.

## Project Structure

- `backend/`: Java Spring Boot application (Gradle).
- `frontend/`: React + Vite + TypeScript application (Nginx).
- `docker-compose.yml`: Local development and deployment orchestration.

## Prerequisites

- **Docker** & **Docker Compose** (Recommended)
- **Java 21** (For local backend development)
- **Node.js 20+** & **npm** (For local frontend development)
- A **Gemini API Key** from [Google AI Studio](https://aistudio.google.com/)

---

## Getting Started

### 1. Environment Configuration

Create a `.env` file in the project root by copying the template:

```bash
cp .env.example .env
```

Open `.env` and configure your settings:
- `GEMINI_API_KEY`: Your personal Gemini API secret.
- `GEMINI_MODEL`: (Optional) Defaults to `gemini-2.0-flash-exp-image-generation`.

---

### 2. Quick Start (Docker)

The fastest way to get the entire studio running is via Docker Compose:

```bash
docker compose up -d --build
```

- **Frontend**: [http://localhost:5173](http://localhost:5173)
- **Backend API**: [http://localhost:8080](http://localhost:8080)
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### 3. Local Development (Manual)

#### Backend (Spring Boot)
Navigate to the `backend` folder and use the Gradle Wrapper:

```bash
cd backend
./gradlew bootRun
```

#### Frontend (React + Vite)
Navigate to the `frontend` folder and start the Vite dev server:

```bash
cd frontend
npm install
npm run dev
```

---

## Features

- **Prompt-to-Image**: Generate stunning AI images using the latest Gemini models.
- **Gallery Viewer**: Browse and manage your creations in a sleek, modern UI.
- **Async Execution**: Handles long-running generative tasks smoothly in the background.
- **Microservice Architecture**: Clean separation between React frontend and Spring Boot backend.
- **Containerized**: Fully Dockerized for "one-click" startup.

## License

MIT
