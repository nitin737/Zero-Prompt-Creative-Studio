import { create } from 'zustand';
import type {
    OperationMode,
    AestheticStyle,
    LightingSetup,
    CameraComposition,
    ColorPalette,
    LensEffect,
    AspectRatio,
    ResolutionQuality,
    StyleIntensity,
    ThinkingLevel,
    GenerateImagePayload,
} from '../types/studio.types';

interface StudioState {
    // Options state
    subject: string;
    operationMode: OperationMode;
    aestheticStyle: AestheticStyle | undefined;
    lighting: LightingSetup | undefined;
    cameraComposition: CameraComposition | undefined;
    colorPalette: ColorPalette | undefined;
    lensEffect: LensEffect | undefined;
    aspectRatio: AspectRatio;
    resolution: ResolutionQuality;
    styleIntensity: StyleIntensity | undefined;
    thinkingLevel: ThinkingLevel;
    uploadedImage: File | null;
    sourceImageBase64: string | undefined;

    // Actions
    setSubject: (subject: string) => void;
    setOperationMode: (mode: OperationMode) => void;
    setAestheticStyle: (style: AestheticStyle | undefined) => void;
    setLighting: (lighting: LightingSetup | undefined) => void;
    setCameraComposition: (cam: CameraComposition | undefined) => void;
    setColorPalette: (palette: ColorPalette | undefined) => void;
    setLensEffect: (effect: LensEffect | undefined) => void;
    setAspectRatio: (ratio: AspectRatio) => void;
    setResolution: (res: ResolutionQuality) => void;
    setStyleIntensity: (intensity: StyleIntensity | undefined) => void;
    setThinkingLevel: (level: ThinkingLevel) => void;
    setUploadedImage: (file: File | null) => void;
    setSourceImageBase64: (base64: string | undefined) => void;
    resetOptions: () => void;
    toRequestPayload: () => GenerateImagePayload;
}

const defaultState = {
    subject: '',
    operationMode: 'TEXT_TO_IMAGE' as OperationMode,
    aestheticStyle: undefined as AestheticStyle | undefined,
    lighting: undefined as LightingSetup | undefined,
    cameraComposition: undefined as CameraComposition | undefined,
    colorPalette: undefined as ColorPalette | undefined,
    lensEffect: undefined as LensEffect | undefined,
    aspectRatio: 'RATIO_16_9' as AspectRatio,
    resolution: 'STANDARD' as ResolutionQuality,
    styleIntensity: undefined as StyleIntensity | undefined,
    thinkingLevel: 'CREATIVE' as ThinkingLevel,
    uploadedImage: null as File | null,
    sourceImageBase64: undefined as string | undefined,
};

export const useStudioStore = create<StudioState>((set, get) => ({
    ...defaultState,

    setSubject: (subject) => set({ subject }),
    setOperationMode: (operationMode) => set({ operationMode }),
    setAestheticStyle: (aestheticStyle) => set({ aestheticStyle }),
    setLighting: (lighting) => set({ lighting }),
    setCameraComposition: (cameraComposition) => set({ cameraComposition }),
    setColorPalette: (colorPalette) => set({ colorPalette }),
    setLensEffect: (lensEffect) => set({ lensEffect }),
    setAspectRatio: (aspectRatio) => set({ aspectRatio }),
    setResolution: (resolution) => set({ resolution }),
    setStyleIntensity: (styleIntensity) => set({ styleIntensity }),
    setThinkingLevel: (thinkingLevel) => set({ thinkingLevel }),
    setUploadedImage: (uploadedImage) => set({ uploadedImage }),
    setSourceImageBase64: (sourceImageBase64) => set({ sourceImageBase64 }),
    resetOptions: () => set(defaultState),

    toRequestPayload: (): GenerateImagePayload => {
        const state = get();
        return {
            subject: state.subject,
            operationMode: state.operationMode,
            aestheticStyle: state.aestheticStyle,
            lighting: state.lighting,
            cameraComposition: state.cameraComposition,
            colorPalette: state.colorPalette,
            lensEffect: state.lensEffect,
            aspectRatio: state.aspectRatio,
            resolution: state.resolution,
            thinkingLevel: state.thinkingLevel,
            styleIntensity: state.styleIntensity,
            sourceImageBase64: state.sourceImageBase64,
        };
    },
}));
