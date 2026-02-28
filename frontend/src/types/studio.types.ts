// ═══════════════════════════════════════════════════
// Type definitions for Zero-Prompt Creative Studio
// ═══════════════════════════════════════════════════

export type OperationMode = 'TEXT_TO_IMAGE' | 'EDIT_EXISTING' | 'STYLE_TRANSFER' | 'MULTI_IMAGE';
export type AestheticStyle = 'PHOTOREALISTIC' | 'ISOMETRIC_3D' | 'FLAT_VECTOR' | 'CINEMATIC' | 'CYBERPUNK' | 'WATERCOLOR' | 'SKETCH' | 'POP_ART';
export type LightingSetup = 'GOLDEN_HOUR' | 'STUDIO_SOFTBOX' | 'NEON_VOLUMETRIC' | 'HIGH_CONTRAST' | 'HARSH_SUNLIGHT';
export type CameraComposition = 'MACRO' | 'WIDE_ANGLE' | 'DRONE_AERIAL' | 'EYE_LEVEL' | 'ISOMETRIC';
export type ColorPalette = 'VIBRANT' | 'MUTED' | 'MONOCHROMATIC' | 'SEPIA' | 'HIGH_CONTRAST_BW';
export type LensEffect = 'DEEP_FOCUS' | 'SHALLOW_DOF' | 'MOTION_BLUR' | 'FISHEYE';
export type AspectRatio = 'RATIO_1_1' | 'RATIO_16_9' | 'RATIO_9_16' | 'RATIO_4_3' | 'RATIO_21_9';
export type ResolutionQuality = 'DRAFT' | 'STANDARD' | 'PRODUCTION';
export type StyleIntensity = 'SUBTLE' | 'BALANCED' | 'AGGRESSIVE';
export type ThinkingLevel = 'FAST' | 'CREATIVE';

export type GenerationStatus = 'idle' | 'loading' | 'success' | 'error';

export interface GenerateImagePayload {
    subject: string;
    operationMode: OperationMode;
    aestheticStyle?: AestheticStyle;
    lighting?: LightingSetup;
    cameraComposition?: CameraComposition;
    colorPalette?: ColorPalette;
    lensEffect?: LensEffect;
    aspectRatio: AspectRatio;
    resolution: ResolutionQuality;
    thinkingLevel: ThinkingLevel;
    styleIntensity?: StyleIntensity;
    sourceImageBase64?: string;
}

export interface GeneratedImageResponse {
    id: string;
    imageUrl: string;
    prompt: string;
    generationTimeMs: number;
    metadata: ImageMetadata;
}

export interface ImageMetadata {
    model: string;
    thinkingLevel: ThinkingLevel;
    aspectRatio: string;
    resolution: string;
    createdAt: string;
}

export interface ErrorResponse {
    error: string;
    message: string;
    timestamp: string;
    retryAfterSeconds?: number;
}

export interface OptionItem {
    value: string;
    label: string;
}

export interface OptionsMap {
    operationModes: OptionItem[];
    aestheticStyles: OptionItem[];
    lightingSetups: OptionItem[];
    cameraCompositions: OptionItem[];
    colorPalettes: OptionItem[];
    lensEffects: OptionItem[];
    aspectRatios: OptionItem[];
    resolutions: OptionItem[];
    styleIntensities: OptionItem[];
    thinkingLevels: OptionItem[];
}
