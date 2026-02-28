# 🎨 Zero-Prompt Creative Studio — Theme & Style Guide

Based on analysis of leading AI creative tools (Midjourney, Leonardo.ai, Runway ML, Adobe Firefly, DALL·E) and 2025–2026 design trends.

---

## Theme: "Cosmic Dark Studio"

A premium, dark-first interface with glassmorphism panels, neon accent lighting, and smooth micro-animations — designed to make generated images the visual hero while the UI fades elegantly into the background.

---

## Color Palette

### Core Colors

| Token | Hex | Usage |
| :--- | :--- | :--- |
| `--bg-primary` | `#0A0A0F` | Main app background (near-black with a hint of blue) |
| `--bg-secondary` | `#12121A` | Sidebar, panels, card backgrounds |
| `--bg-elevated` | `#1A1A2E` | Elevated surfaces, modals, tooltips |
| `--bg-glass` | `rgba(255, 255, 255, 0.05)` | Glassmorphism panels |
| `--border-glass` | `rgba(255, 255, 255, 0.08)` | Subtle glass borders |

### Accent Colors (Neon Duo)

| Token | Hex | Usage |
| :--- | :--- | :--- |
| `--accent-primary` | `#7C3AED` | Primary actions, active states (Electric Violet) |
| `--accent-secondary` | `#06B6D4` | Secondary highlights, links (Vivid Cyan) |
| `--accent-gradient` | `linear-gradient(135deg, #7C3AED, #06B6D4)` | CTAs, progress bars, hero elements |
| `--accent-glow` | `0 0 20px rgba(124, 58, 237, 0.3)` | Glow effect on focus/hover |

### Text Colors

| Token | Hex | Usage |
| :--- | :--- | :--- |
| `--text-primary` | `#F1F5F9` | Headings, primary content |
| `--text-secondary` | `#94A3B8` | Descriptions, labels |
| `--text-muted` | `#475569` | Disabled states, hints |

### Status Colors

| Token | Hex | Usage |
| :--- | :--- | :--- |
| `--success` | `#22C55E` | Generation complete |
| `--warning` | `#F59E0B` | Quota warnings |
| `--error` | `#EF4444` | Errors, failures |

---

## Typography

| Role | Font | Source | Fallback |
| :--- | :--- | :--- | :--- |
| **Headings / Display** | **Space Grotesk** | Google Fonts | `system-ui, sans-serif` |
| **Body / UI** | **Inter** | Google Fonts | `system-ui, sans-serif` |
| **Code / Mono** | **JetBrains Mono** | Google Fonts | `monospace` |

### Type Scale

| Element | Size | Weight | Letter Spacing |
| :--- | :--- | :--- | :--- |
| H1 (Page Title) | 2.5rem (40px) | 700 | -0.02em |
| H2 (Section) | 1.75rem (28px) | 600 | -0.01em |
| H3 (Card Title) | 1.25rem (20px) | 600 | 0 |
| Body | 1rem (16px) | 400 | 0 |
| Small / Labels | 0.875rem (14px) | 500 | 0.02em |
| Caption | 0.75rem (12px) | 400 | 0.04em |

---

## Glassmorphism System

```css
.glass-panel {
    background: rgba(255, 255, 255, 0.05);
    backdrop-filter: blur(16px);
    -webkit-backdrop-filter: blur(16px);
    border: 1px solid rgba(255, 255, 255, 0.08);
    border-radius: 16px;
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
}

.glass-panel-elevated {
    background: rgba(255, 255, 255, 0.08);
    backdrop-filter: blur(24px);
    border: 1px solid rgba(255, 255, 255, 0.12);
    border-radius: 20px;
    box-shadow: 0 12px 48px rgba(0, 0, 0, 0.4);
}
```

---

## Micro-Animations

| Element | Animation | Duration | Easing |
| :--- | :--- | :--- | :--- |
| Buttons (hover) | Scale up 1.02 + glow | 200ms | `ease-out` |
| Dropdown open | Slide down + fade in | 250ms | `cubic-bezier(0.16, 1, 0.3, 1)` |
| Card hover | Subtle lift (-2px) + border glow | 300ms | `ease-out` |
| Page transitions | Fade + slide (20px) | 350ms | `cubic-bezier(0.22, 1, 0.36, 1)` |
| Image reveal | Scale 0.95 → 1 + fade | 500ms | `ease-out` |
| Loading | Pulsing gradient shimmer | 1.5s loop | `ease-in-out` |
| Thinking indicator | Orbiting dots with glow trails | 2s loop | `linear` |

---

## Component Styles

### Buttons

- **Primary**: Gradient fill (violet → cyan), white text, glow on hover
- **Secondary**: Glass bg, accent border, accent text
- **Ghost**: Transparent, text-only, underline on hover
- **Danger**: Subtle red bg, red text

### Dropdowns (Core UI)

- Glass background with blur
- Active item: left-border gradient + subtle highlight
- Hover: subtle background brighten
- Smooth open/close animation

### Generated Image Display

- Full-width canvas area with subtle inner shadow
- Glass overlay toolbar (download, share, regenerate)
- Skeleton shimmer loader during generation
- Image appears with scale + fade animation

---

## Layout Structure

```
┌──────────────────────────────────────────────────────────────────┐
│  🧭 Top Bar  [Logo]  Zero-Prompt Creative Studio       [User]  │
├──────────┬───────────────────────────────────────────────────────┤
│          │                                                       │
│  Options │              🖼️  Canvas / Preview                     │
│  Panel   │                                                       │
│  ─────── │           (Generated image appears here)              │
│  Style   │                                                       │
│  Light   │                                                       │
│  Camera  │                                                       │
│  Color   │  ┌─────────────────────────────────────────────────┐  │
│  Lens    │  │  Subject: [ describe your image... ]      [⚡]  │  │
│  Ratio   │  └─────────────────────────────────────────────────┘  │
│  Quality │                                                       │
│          │  [Fast ○ ●──── ○ Creative]    [Generate ▶]            │
├──────────┴───────────────────────────────────────────────────────┤
│  📊 Footer: API Status • Quota • History                         │
└──────────────────────────────────────────────────────────────────┘
```

---

## Design Inspirations

| Product | What to Borrow |
| :--- | :--- |
| **Midjourney** | Grid image preview, dark canvas-first layout |
| **Linear** | Glassmorphism, smooth transitions, violet accents |
| **Vercel** | Clean dark theme, Inter typography, minimal borders |
| **Runway ML** | Timeline-style UI for creative tools |
| **Figma** | Toolbar overlays on canvas, collaborative feel |

---

## Summary

| Aspect | Choice |
| :--- | :--- |
| **Theme** | Dark-first ("Cosmic Dark Studio") |
| **Style** | Glassmorphism + Neon Accents |
| **Primary Accent** | Electric Violet `#7C3AED` |
| **Secondary Accent** | Vivid Cyan `#06B6D4` |
| **Heading Font** | Space Grotesk (700/600) |
| **Body Font** | Inter (400/500) |
| **Animations** | Smooth, subtle, purposeful |
| **Border Radius** | 12px (buttons), 16px (cards), 20px (modals) |
| **Glass Blur** | 16px standard, 24px elevated |
