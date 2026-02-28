# 🏗️ Zero-Prompt Creative Studio — Architecture Design

## Table of Contents

- [1. Monorepo Structure](#1-monorepo-structure)
- [2. High-Level System Architecture](#2-high-level-system-architecture)
- [3. Frontend Architecture](#3-frontend-architecture)
- [4. Backend Architecture](#4-backend-architecture)
- [5. Data Flow](#5-data-flow)
- [6. API Contract](#6-api-contract)
- [7. Deployment Architecture](#7-deployment-architecture)

---

## 1. Monorepo Structure

```
Zero-Prompt-Creative-Studio/
├── frontend/                        # React application
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── api/                     # API client layer
│   │   │   ├── axiosClient.ts       # Axios instance with interceptors
│   │   │   └── imageApi.ts          # Image generation API calls
│   │   ├── assets/                  # Static assets (icons, fonts)
│   │   ├── components/              # Reusable UI components
│   │   │   ├── common/              # Buttons, Inputs, Modals
│   │   │   ├── canvas/              # Image display, Canvas area
│   │   │   ├── controls/            # Dropdowns, Sliders, Toggles
│   │   │   └── layout/              # Header, Sidebar, Footer
│   │   ├── hooks/                   # Custom React hooks
│   │   ├── pages/                   # Page-level components
│   │   │   ├── StudioPage.tsx       # Main creative studio
│   │   │   ├── GalleryPage.tsx      # History / gallery view
│   │   │   └── SettingsPage.tsx     # API key & preferences
│   │   ├── store/                   # Zustand state management
│   │   │   ├── useStudioStore.ts    # Studio options state
│   │   │   ├── useGenerationStore.ts# Generation status state
│   │   │   └── useAuthStore.ts      # Auth state
│   │   ├── styles/                  # CSS design system
│   │   │   ├── index.css            # Global styles + CSS variables
│   │   │   ├── glass.css            # Glassmorphism utilities
│   │   │   └── animations.css       # Micro-animation keyframes
│   │   ├── types/                   # TypeScript type definitions
│   │   │   └── studio.types.ts
│   │   ├── utils/                   # Helper utilities
│   │   ├── App.tsx
│   │   └── main.tsx
│   ├── package.json
│   ├── tsconfig.json
│   └── vite.config.ts
│
├── backend/                         # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/zpcs/
│   │   │   │   ├── ZpcsApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   ├── AsyncConfig.java           # Thread pool for @Async generation
│   │   │   │   │   ├── CorsConfig.java
│   │   │   │   │   ├── WebClientConfig.java
│   │   │   │   │   ├── GeminiProperties.java      # @ConfigurationProperties (type-safe)
│   │   │   │   │   └── StorageProperties.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── ImageGenerationController.java
│   │   │   │   │   ├── GalleryController.java
│   │   │   │   │   ├── OptionsController.java     # GET /api/v1/options
│   │   │   │   │   └── HealthController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/
│   │   │   │   │   │   ├── GenerateImageRequest.java
│   │   │   │   │   │   └── EditImageRequest.java
│   │   │   │   │   └── response/
│   │   │   │   │       ├── GeneratedImageResponse.java
│   │   │   │   │       ├── ImageMetadata.java
│   │   │   │   │       └── ErrorResponse.java
│   │   │   │   ├── orchestrator/                   # ← Interfaces + Orchestrator (DIP)
│   │   │   │   │   ├── ImageGenerationOrchestrator.java     # Interface
│   │   │   │   │   └── DefaultImageGenerationOrchestrator.java
│   │   │   │   ├── strategy/                       # ← Strategy Pattern (OCP)
│   │   │   │   │   ├── GenerationStrategy.java              # Interface
│   │   │   │   │   ├── GenerationStrategyFactory.java
│   │   │   │   │   ├── TextToImageStrategy.java
│   │   │   │   │   ├── EditImageStrategy.java
│   │   │   │   │   └── StyleTransferStrategy.java
│   │   │   │   ├── prompt/                         # ← Chain of Responsibility (OCP)
│   │   │   │   │   ├── PromptComposer.java                  # Interface
│   │   │   │   │   ├── ChainedPromptComposer.java
│   │   │   │   │   ├── PromptBuilder.java                   # Builder Pattern
│   │   │   │   │   ├── PromptFragmentHandler.java            # Interface
│   │   │   │   │   └── handlers/
│   │   │   │   │       ├── SubjectHandler.java
│   │   │   │   │       ├── AestheticStyleHandler.java
│   │   │   │   │       ├── LightingHandler.java
│   │   │   │   │       ├── CameraHandler.java
│   │   │   │   │       ├── ColorPaletteHandler.java
│   │   │   │   │       ├── LensEffectHandler.java
│   │   │   │   │       └── ResolutionHandler.java
│   │   │   │   ├── client/                         # ← Adapter Pattern (DIP)
│   │   │   │   │   ├── AiModelClient.java                   # Interface
│   │   │   │   │   ├── GeminiClientAdapter.java
│   │   │   │   │   └── GeminiResponseParser.java
│   │   │   │   ├── storage/                        # ← Repository + Storage (ISP)
│   │   │   │   │   ├── StorageService.java                  # Interface
│   │   │   │   │   ├── LocalFileStorageService.java
│   │   │   │   │   ├── ImageRecordRepository.java           # Interface
│   │   │   │   │   └── InMemoryImageRecordRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── GalleryService.java
│   │   │   │   │   └── OptionsProvider.java
│   │   │   │   ├── event/                          # ← Observer Pattern
│   │   │   │   │   ├── ImageGeneratedEvent.java
│   │   │   │   │   └── GalleryEventListener.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── GenerationContext.java       # @Value @Builder (immutable)
│   │   │   │   │   ├── GenerationResult.java
│   │   │   │   │   ├── AiModelRequest.java
│   │   │   │   │   ├── AiModelResponse.java
│   │   │   │   │   ├── ImageRecord.java
│   │   │   │   │   ├── GeminiRequestConfig.java
│   │   │   │   │   ├── PromptDescribable.java       # Interface for enum self-mapping
│   │   │   │   │   └── enums/
│   │   │   │   │       ├── AestheticStyle.java       # implements PromptDescribable
│   │   │   │   │       ├── LightingSetup.java
│   │   │   │   │       ├── CameraComposition.java
│   │   │   │   │       ├── ColorPalette.java
│   │   │   │   │       ├── LensEffect.java
│   │   │   │   │       ├── AspectRatio.java
│   │   │   │   │       ├── ResolutionQuality.java
│   │   │   │   │       ├── StyleIntensity.java
│   │   │   │   │       ├── OperationMode.java
│   │   │   │   │       └── ThinkingLevel.java
│   │   │   │   └── exception/                      # ← Polymorphic hierarchy (LSP)
│   │   │   │       ├── ZpcsException.java            # Abstract base
│   │   │   │       ├── GlobalExceptionHandler.java
│   │   │   │       ├── GeminiApiException.java
│   │   │   │       ├── QuotaExceededException.java
│   │   │   │       └── ImageNotFoundException.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── application-dev.yml
│   │   └── test/
│   │       └── java/com/zpcs/
│   │           ├── orchestrator/
│   │           │   └── DefaultImageGenerationOrchestratorTest.java
│   │           ├── strategy/
│   │           │   └── TextToImageStrategyTest.java
│   │           ├── prompt/
│   │           │   └── ChainedPromptComposerTest.java
│   │           └── controller/
│   │               └── ImageGenerationControllerTest.java
│   ├── pom.xml
│   └── Dockerfile
│
├── docs/
│   └── specs/
│       ├── initial.md
│       ├── theme-style-guide.md
│       ├── architecture.md          # ← This file
│       └── lld.md
├── .gitignore
├── README.md
└── docker-compose.yml               # Local dev orchestration
```

---

## 2. High-Level System Architecture

```mermaid
graph TB
    subgraph Client["🖥️ Browser - React SPA"]
        UI["UI Components<br/>Dropdowns, Canvas, Controls"]
        Store["Zustand Store<br/>State Management"]
        APIClient["Axios API Client"]
        UI <--> Store
        Store --> APIClient
    end

    subgraph Backend["☕ Spring Boot Backend"]
        Controller["REST Controller<br/>/api/v1/images/**"]
        Orchestrator["ImageGenerationOrchestrator<br/>interface - DIP"]
        PromptComposer["PromptComposer<br/>Chain of Responsibility"]
        StrategyFactory["GenerationStrategyFactory<br/>Strategy Pattern"]
        Strategy["GenerationStrategy<br/>TextToImage / Edit / StyleTransfer"]
        AIClient["AiModelClient<br/>interface - Adapter Pattern"]
        EventBus["ApplicationEventPublisher<br/>Observer Pattern"]
        ExHandler["GlobalExceptionHandler<br/>Polymorphic ZpcsException"]
    end

    subgraph Infra["🗄️ Infrastructure"]
        GeminiAdapter["GeminiClientAdapter<br/>implements AiModelClient"]
        StorageSvc["StorageService<br/>interface - ISP"]
        Repository["ImageRecordRepository<br/>interface - DIP"]
    end

    subgraph External["☁️ External Services"]
        GeminiAPI["Google Gemini 3.1 Flash<br/>Image Generation API"]
    end

    APIClient -->|"HTTP REST JSON"| Controller
    Controller --> Orchestrator
    Orchestrator --> PromptComposer
    Orchestrator --> StrategyFactory
    StrategyFactory --> Strategy
    Strategy --> AIClient
    AIClient -.->|"implemented by"| GeminiAdapter
    GeminiAdapter -->|"WebClient"| GeminiAPI
    Orchestrator --> StorageSvc
    Orchestrator --> Repository
    Orchestrator --> EventBus

    style Client fill:#1A1A2E,stroke:#7C3AED,stroke-width:2px,color:#F1F5F9
    style Backend fill:#12121A,stroke:#06B6D4,stroke-width:2px,color:#F1F5F9
    style Infra fill:#0A0A0F,stroke:#F59E0B,stroke-width:2px,color:#F1F5F9
    style External fill:#0A0A0F,stroke:#22C55E,stroke-width:2px,color:#F1F5F9
```

---

## 3. Frontend Architecture

### 3.1 Component Library: **MUI (Material UI) v6** with Custom Dark Theme

**Why MUI?**
- Rich set of pre-built components (Select, Slider, AppBar, Drawer, Skeleton loaders)
- Excellent theming system — fully customizable to our "Cosmic Dark Studio" design tokens
- TypeScript-first, well-maintained, massive community
- Built-in responsive breakpoints

### 3.2 Component Tree

```mermaid
graph TD
    App["App.tsx"]
    App --> Router["React Router"]
    Router --> StudioPage["StudioPage"]
    Router --> GalleryPage["GalleryPage"]
    Router --> SettingsPage["SettingsPage"]

    StudioPage --> Header["Header<br/>(Logo + Nav + Avatar)"]
    StudioPage --> Sidebar["OptionsSidebar"]
    StudioPage --> Canvas["CanvasArea"]
    StudioPage --> BottomBar["BottomControls"]
    StudioPage --> Footer["StatusFooter"]

    Sidebar --> OpMode["OperationModeSelect"]
    Sidebar --> StyleDrop["AestheticStyleSelect"]
    Sidebar --> LightDrop["LightingSelect"]
    Sidebar --> CamDrop["CameraCompositionSelect"]
    Sidebar --> ColorDrop["ColorPaletteSelect"]
    Sidebar --> LensDrop["LensEffectSelect"]
    Sidebar --> RatioDrop["AspectRatioSelect"]
    Sidebar --> QualityDrop["ResolutionSelect"]
    Sidebar --> IntensityDrop["StyleIntensitySlider"]

    Canvas --> ImageDisplay["ImageDisplay"]
    Canvas --> ImageToolbar["ImageToolbar<br/>(Download, Share, Regen)"]
    Canvas --> SkeletonLoader["ShimmerLoader"]

    BottomBar --> SubjectInput["SubjectInput<br/>(Text Field)"]
    BottomBar --> ThinkingToggle["ThinkingModeToggle<br/>(Fast ↔ Creative)"]
    BottomBar --> GenerateBtn["GenerateButton"]
    BottomBar --> ImageUpload["ImageUploadZone<br/>(for Edit/Style Transfer)"]

    style App fill:#1A1A2E,stroke:#7C3AED,color:#F1F5F9
    style StudioPage fill:#12121A,stroke:#06B6D4,color:#F1F5F9
```

### 3.3 State Management (Zustand)

```mermaid
graph LR
    subgraph StudioStore["useStudioStore"]
        S1["operationMode"]
        S2["aestheticStyle"]
        S3["lighting"]
        S4["camera"]
        S5["colorPalette"]
        S6["lensEffect"]
        S7["aspectRatio"]
        S8["resolution"]
        S9["styleIntensity"]
        S10["subject (text)"]
        S11["thinkingLevel"]
        S12["uploadedImage (File)"]
    end

    subgraph GenerationStore["useGenerationStore"]
        G1["status: idle | loading | success | error"]
        G2["generatedImageUrl"]
        G3["generationTime"]
        G4["errorMessage"]
    end

    subgraph Actions
        A1["setOption(key, value)"]
        A2["resetOptions()"]
        A3["generate()"]
        A4["setResult()"]
    end

    StudioStore --> A1
    StudioStore --> A2
    A3 --> GenerationStore
    A4 --> GenerationStore

    style StudioStore fill:#1A1A2E,stroke:#7C3AED,color:#F1F5F9
    style GenerationStore fill:#1A1A2E,stroke:#06B6D4,color:#F1F5F9
```

### 3.4 Key Dependencies

| Package | Version | Purpose |
|:---|:---|:---|
| `react` | ^19 | UI framework |
| `react-router-dom` | ^7 | Client-side routing |
| `@mui/material` | ^6 | Component library |
| `@emotion/react` | ^11 | CSS-in-JS (MUI dependency) |
| `zustand` | ^5 | Lightweight state management |
| `axios` | ^1.7 | HTTP client |
| `react-dropzone` | ^14 | Image upload drag & drop |
| `framer-motion` | ^11 | Advanced animations |
| `react-hot-toast` | ^2 | Toast notifications |

---

## 4. Backend Architecture

### 4.1 Spring Boot Stack

| Dependency | Purpose |
|:---|:---|
| `spring-boot-starter-web` | REST API (Tomcat) |
| `spring-boot-starter-webflux` | WebClient for non-blocking Gemini API calls |
| `spring-boot-starter-validation` | Request validation (`@Valid`, `@NotNull`) |
| `spring-boot-starter-actuator` | Health checks & metrics |
| `lombok` | Boilerplate reduction (`@Data`, `@Builder`, `@Slf4j`) |
| `springdoc-openapi` | Auto-generated Swagger/OpenAPI docs |
| `google-cloud-vertexai` | Google Gemini SDK (or REST via WebClient) |

### 4.2 Layered Architecture (SOLID)

```mermaid
graph TD
    subgraph Presentation["Presentation Layer"]
        C1["ImageGenerationController"]
        C2["GalleryController"]
        C3["OptionsController"]
        C4["HealthController"]
    end

    subgraph Orchestration["Orchestration Layer"]
        O1["ImageGenerationOrchestrator<br/>interface"]
        O2["DefaultImageGenerationOrchestrator<br/>Template Method"]
    end

    subgraph Business["Business / Strategy Layer"]
        S1["GenerationStrategyFactory"]
        S2["TextToImageStrategy"]
        S3["EditImageStrategy"]
        S4["StyleTransferStrategy"]
        P1["PromptComposer - interface"]
        P2["ChainedPromptComposer<br/>Chain of Responsibility"]
    end

    subgraph Integration["Integration Layer - Interfaces"]
        I1["AiModelClient interface"]
        I2["StorageService interface"]
        I3["ImageRecordRepository interface"]
    end

    subgraph Infra["Infrastructure - Implementations"]
        IA["GeminiClientAdapter"]
        IB["LocalFileStorageService"]
        IC["InMemoryImageRecordRepository"]
    end

    subgraph Events["Event Layer - Observer"]
        E1["ImageGeneratedEvent"]
        E2["GalleryEventListener"]
    end

    subgraph CrossCut["Cross-Cutting"]
        X1["GlobalExceptionHandler - LSP"]
        X2["CorsConfig"]
        X3["RateLimitFilter"]
    end

    C1 --> O1
    O1 -.-> O2
    O2 --> P1
    P1 -.-> P2
    O2 --> S1
    S1 --> S2
    S1 --> S3
    S1 --> S4
    S2 --> I1
    S3 --> I1
    S4 --> I1
    I1 -.-> IA
    O2 --> I2
    O2 --> I3
    I2 -.-> IB
    I3 -.-> IC
    O2 --> E1
    E1 --> E2
    IA -->|"WebClient"| GeminiAPI["Gemini API"]

    style Presentation fill:#1A1A2E,stroke:#7C3AED,color:#F1F5F9
    style Orchestration fill:#12121A,stroke:#7C3AED,color:#F1F5F9
    style Business fill:#12121A,stroke:#06B6D4,color:#F1F5F9
    style Integration fill:#0A0A0F,stroke:#22C55E,color:#F1F5F9
    style Infra fill:#0A0A0F,stroke:#F59E0B,color:#F1F5F9
    style Events fill:#0A0A0F,stroke:#06B6D4,color:#F1F5F9
    style CrossCut fill:#0A0A0F,stroke:#EF4444,color:#F1F5F9
```

> **Note:** Dashed arrows (`-.->`) represent "implements" relationships — every layer depends on interfaces, not concrete classes (Dependency Inversion Principle).

### 4.3 Backend Package Structure

```
com.zpcs
├── ZpcsApplication.java                  # @SpringBootApplication entry point
├── config/                               # Cross-cutting configuration
│   ├── AsyncConfig.java                  # Thread pool for @Async generation
│   ├── CorsConfig.java                   # CORS for React dev server
│   ├── WebClientConfig.java              # WebClient bean
│   ├── GeminiProperties.java             # @ConfigurationProperties (type-safe)
│   └── StorageProperties.java
├── controller/                           # Thin controllers (SRP)
│   ├── ImageGenerationController.java    # Depends on Orchestrator interface (DIP)
│   ├── GalleryController.java
│   ├── OptionsController.java
│   └── HealthController.java
├── dto/                                  # Immutable DTOs with validation
│   ├── request/
│   │   ├── GenerateImageRequest.java     # @Valid + @NotBlank
│   │   └── EditImageRequest.java
│   └── response/
│       ├── GeneratedImageResponse.java   # @Value @Builder
│       ├── ImageMetadata.java
│       └── ErrorResponse.java
├── orchestrator/                         # Orchestration (DIP)
│   ├── ImageGenerationOrchestrator.java  # Interface
│   └── DefaultImageGenerationOrchestrator.java
├── strategy/                             # Strategy Pattern (OCP + LSP)
│   ├── GenerationStrategy.java           # Interface
│   ├── GenerationStrategyFactory.java
│   ├── TextToImageStrategy.java
│   ├── EditImageStrategy.java
│   └── StyleTransferStrategy.java
├── prompt/                               # Chain of Responsibility (OCP + SRP)
│   ├── PromptComposer.java               # Interface
│   ├── ChainedPromptComposer.java        # Assembles handlers via Spring DI
│   ├── PromptBuilder.java                # Builder Pattern
│   ├── PromptFragmentHandler.java        # Interface
│   └── handlers/                         # One handler per dropdown (SRP)
│       ├── SubjectHandler.java
│       ├── AestheticStyleHandler.java
│       ├── LightingHandler.java
│       ├── CameraHandler.java
│       ├── ColorPaletteHandler.java
│       ├── LensEffectHandler.java
│       └── ResolutionHandler.java
├── client/                               # Adapter Pattern (DIP)
│   ├── AiModelClient.java                # Interface — swap AI provider here
│   ├── GeminiClientAdapter.java          # Adapts Gemini API → AiModelResponse
│   └── GeminiResponseParser.java
├── storage/                              # Repository + Storage (ISP + DIP)
│   ├── StorageService.java               # Interface
│   ├── LocalFileStorageService.java
│   ├── ImageRecordRepository.java        # Interface
│   └── InMemoryImageRecordRepository.java
├── service/
│   ├── GalleryService.java
│   └── OptionsProvider.java
├── event/                                # Observer Pattern
│   ├── ImageGeneratedEvent.java
│   └── GalleryEventListener.java
├── model/                                # Domain models (@Value @Builder)
│   ├── GenerationContext.java
│   ├── GenerationResult.java
│   ├── AiModelRequest.java
│   ├── AiModelResponse.java
│   ├── ImageRecord.java
│   ├── GeminiRequestConfig.java
│   ├── PromptDescribable.java            # Interface for enum self-mapping
│   └── enums/                            # Enums implement PromptDescribable
│       ├── AestheticStyle.java
│       ├── LightingSetup.java
│       ├── CameraComposition.java
│       ├── ColorPalette.java
│       ├── LensEffect.java
│       ├── AspectRatio.java
│       ├── ResolutionQuality.java
│       ├── StyleIntensity.java
│       ├── OperationMode.java
│       └── ThinkingLevel.java
└── exception/                            # Polymorphic hierarchy (LSP)
    ├── ZpcsException.java                # Abstract base — getHttpStatus() + getErrorCode()
    ├── GlobalExceptionHandler.java       # Single handler using polymorphism
    ├── GeminiApiException.java
    ├── QuotaExceededException.java
    └── ImageNotFoundException.java
```

---

## 5. Data Flow

### 5.1 Image Generation — Full Request/Response Flow

```mermaid
sequenceDiagram
    participant User as 👤 User
    participant UI as 🖥️ React UI
    participant Store as 📦 Zustand Store
    participant API as 🌐 Axios Client
    participant Ctrl as ☕ Controller
    participant Orch as 🎯 Orchestrator
    participant Chain as 🔗 ChainedPromptComposer
    participant Factory as 🏭 StrategyFactory
    participant Strat as ♟️ TextToImageStrategy
    participant AI as 🤖 AiModelClient
    participant Adapter as 🔌 GeminiClientAdapter
    participant ExtAPI as ☁️ Gemini 3.1 Flash API
    participant Store2 as 💾 StorageService
    participant Repo as 📋 Repository
    participant Evt as 📢 EventPublisher

    User->>UI: Selects options from dropdowns
    UI->>Store: setOption(key, value)
    User->>UI: Types subject "A cat on Mars"
    UI->>Store: setSubject("A cat on Mars")
    User->>UI: Clicks [Generate ▶]
    
    UI->>Store: setStatus("loading")
    Note over UI: Shows shimmer loader
    
    Store->>API: POST /api/v1/images/generate
    API->>Ctrl: GenerateImageRequest (JSON)
    Ctrl->>Ctrl: @Valid — validate request
    Ctrl->>Orch: generate(request) via interface
    
    Note over Orch: Chain of Responsibility
    Orch->>Chain: compose(request)
    Note over Chain: SubjectHandler → AestheticStyleHandler<br/>→ LightingHandler → CameraHandler<br/>→ ColorPaletteHandler → LensEffectHandler<br/>→ ResolutionHandler
    Chain-->>Orch: prompt string
    
    Note over Orch: Strategy Pattern
    Orch->>Factory: getStrategy(TEXT_TO_IMAGE)
    Factory-->>Orch: TextToImageStrategy
    Orch->>Strat: execute(context)
    Strat->>AI: generateImage(aiRequest) via interface
    AI->>Adapter: delegated to GeminiClientAdapter
    Adapter->>ExtAPI: HTTP POST (prompt + params)
    
    Note over ExtAPI: AI generates image<br/>(3–5 seconds)
    
    ExtAPI-->>Adapter: Raw JSON response
    Note over Adapter: Adapter Pattern:<br/>converts to AiModelResponse
    Adapter-->>AI: AiModelResponse
    AI-->>Strat: AiModelResponse
    Strat-->>Orch: GenerationResult
    
    Orch->>Store2: save(bytes, id) via interface
    Orch->>Repo: save(record) via interface
    
    Note over Orch: Observer Pattern
    Orch->>Evt: publish(ImageGeneratedEvent)
    
    Orch-->>Ctrl: GeneratedImageResponse
    Ctrl-->>API: 200 OK (imageUrl, metadata)
    API-->>Store: setResult(imageUrl)
    Store->>UI: status = "success"
    
    Note over UI: Reveals image with<br/>scale + fade animation
    
    UI->>User: 🖼️ Generated image displayed!
```

### 5.2 Operation Mode Routing (Strategy Pattern)

```mermaid
flowchart TD
    Request["Incoming Request"] --> Orch["Orchestrator"]
    Orch --> Chain["ChainedPromptComposer<br/>builds prompt via handlers"]
    Chain --> Factory["GenerationStrategyFactory<br/>resolves strategy"]
    
    Factory --> ModeCheck{"strategy.supports?"}
    
    ModeCheck -->|"TEXT_TO_IMAGE"| T2I["TextToImageStrategy<br/>prompt → AiModelClient"]
    ModeCheck -->|"EDIT_EXISTING"| Edit["EditImageStrategy<br/>prompt + source → AiModelClient"]
    ModeCheck -->|"STYLE_TRANSFER"| Style["StyleTransferStrategy<br/>prompt + source + intensity → AiModelClient"]
    ModeCheck -->|"MULTI_IMAGE"| Multi["MultiImageStrategy<br/>prompt + images → AiModelClient"]
    
    T2I --> AIClient["AiModelClient interface"]
    Edit --> AIClient
    Style --> AIClient
    Multi --> AIClient
    
    AIClient --> Adapter["GeminiClientAdapter<br/>Adapter Pattern"]
    Adapter --> Result["GenerationResult<br/>returned to Orchestrator"]
    
    Result --> Save["StorageService.save + Repository.save"]
    Save --> Event["publish ImageGeneratedEvent"]

    style Orch fill:#1A1A2E,stroke:#7C3AED,color:#F1F5F9
    style Factory fill:#1A1A2E,stroke:#06B6D4,color:#F1F5F9
    style ModeCheck fill:#12121A,stroke:#7C3AED,color:#F1F5F9
    style AIClient fill:#0A0A0F,stroke:#22C55E,color:#F1F5F9
    style Event fill:#0A0A0F,stroke:#F59E0B,color:#F1F5F9
```

> **OCP in action:** To add `VIDEO_GENERATION` mode, create a new `VideoGenerationStrategy` class that implements `GenerationStrategy`. Register it as a `@Component`. Zero changes to the Orchestrator, Factory, or any existing strategy.

---

## 6. API Contract

### 6.1 Endpoints

| Method | Endpoint | Description |
|:---|:---|:---|
| `POST` | `/api/v1/images/generate` | Generate a new image from options |
| `POST` | `/api/v1/images/edit` | Edit an existing image |
| `GET` | `/api/v1/images/{id}` | Get a specific generated image |
| `GET` | `/api/v1/gallery` | List generation history (paginated) |
| `DELETE` | `/api/v1/images/{id}` | Delete a generated image |
| `GET` | `/api/v1/health` | Health check + API quota status |
| `GET` | `/api/v1/options` | Get all dropdown options (enum values) |

### 6.2 Request / Response Schemas

**POST `/api/v1/images/generate`**

```json
// REQUEST
{
  "subject": "A cat sitting on the surface of Mars",
  "operationMode": "TEXT_TO_IMAGE",
  "aestheticStyle": "CINEMATIC",
  "lighting": "GOLDEN_HOUR",
  "cameraComposition": "WIDE_ANGLE",
  "colorPalette": "VIBRANT",
  "lensEffect": "SHALLOW_DOF",
  "aspectRatio": "RATIO_16_9",
  "resolution": "PRODUCTION",
  "thinkingLevel": "CREATIVE",
  "styleIntensity": null,
  "sourceImage": null
}

// RESPONSE — 200 OK
{
  "id": "img_abc123",
  "imageUrl": "/api/v1/images/img_abc123/file",
  "prompt": "A cat sitting on the surface of Mars, rendered in a Cinematic aesthetic...",
  "generationTimeMs": 4230,
  "metadata": {
    "model": "gemini-3.1-flash-image-preview",
    "thinkingLevel": "CREATIVE",
    "aspectRatio": "16:9",
    "resolution": "PRODUCTION",
    "createdAt": "2026-02-28T22:54:00Z"
  }
}

// RESPONSE — 400 Bad Request
{
  "error": "VALIDATION_ERROR",
  "message": "Subject is required for TEXT_TO_IMAGE mode",
  "timestamp": "2026-02-28T22:54:00Z"
}

// RESPONSE — 429 Too Many Requests
{
  "error": "QUOTA_EXCEEDED",
  "message": "API rate limit reached. Try again in 60 seconds.",
  "retryAfterSeconds": 60,
  "timestamp": "2026-02-28T22:54:00Z"
}
```

---

## 7. Deployment Architecture

```mermaid
graph TB
    subgraph Dev["🖥️ Local Development"]
        DevFE["Vite Dev Server<br/>localhost:5173"]
        DevBE["Spring Boot<br/>localhost:8080"]
        DevFE -->|"Proxy /api"| DevBE
    end

    subgraph Prod["☁️ Production (Future)"]
        CDN["CDN / Static Hosting<br/>(Vercel / Netlify)"]
        API["Spring Boot API<br/>(Cloud Run / Railway)"]
        Bucket["Cloud Storage<br/>(GCS Bucket)"]
        CDN -->|"HTTPS"| API
        API --> Bucket
        API -->|"SDK"| GeminiProd["Gemini API"]
    end

    style Dev fill:#1A1A2E,stroke:#7C3AED,stroke-width:2px,color:#F1F5F9
    style Prod fill:#0A0A0F,stroke:#22C55E,stroke-width:2px,color:#F1F5F9
```

### Local Dev Setup

```yaml
# docker-compose.yml
services:
  frontend:
    build: ./frontend
    ports:
      - "5173:5173"
    volumes:
      - ./frontend/src:/app/src
    environment:
      - VITE_API_BASE_URL=http://localhost:8080

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      - GEMINI_API_KEY=${GEMINI_API_KEY}
      - SPRING_PROFILES_ACTIVE=dev
    volumes:
      - ./backend/generated-images:/app/generated-images
```
