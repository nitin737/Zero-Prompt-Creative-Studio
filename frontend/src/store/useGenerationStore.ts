import { create } from 'zustand';
import type { GenerationStatus, GeneratedImageResponse } from '../types/studio.types';
import { imageApi } from '../api/imageApi';
import type { GenerateImagePayload } from '../types/studio.types';

interface GenerationState {
    status: GenerationStatus;
    generatedImageUrl: string | null;
    generatedImageId: string | null;
    generationTime: number | null;
    prompt: string | null;
    errorMessage: string | null;
    history: GeneratedImageResponse[];

    generate: (payload: GenerateImagePayload) => Promise<void>;
    setResult: (result: GeneratedImageResponse) => void;
    setError: (message: string) => void;
    reset: () => void;
}

export const useGenerationStore = create<GenerationState>((set, get) => ({
    status: 'idle',
    generatedImageUrl: null,
    generatedImageId: null,
    generationTime: null,
    prompt: null,
    errorMessage: null,
    history: [],

    generate: async (payload: GenerateImagePayload) => {
        set({ status: 'loading', errorMessage: null });
        try {
            const result = await imageApi.generateImage(payload);
            const fullImageUrl = imageApi.getImageUrl(result.id);
            set({
                status: 'success',
                generatedImageUrl: fullImageUrl,
                generatedImageId: result.id,
                generationTime: result.generationTimeMs,
                prompt: result.prompt,
                history: [result, ...get().history],
            });
        } catch (error: unknown) {
            const message = error instanceof Error ? error.message : 'Generation failed';
            const apiMessage = (error as { response?: { data?: { message?: string } } })?.response?.data?.message;
            set({
                status: 'error',
                errorMessage: apiMessage || message,
            });
        }
    },

    setResult: (result: GeneratedImageResponse) => {
        const fullImageUrl = imageApi.getImageUrl(result.id);
        set({
            status: 'success',
            generatedImageUrl: fullImageUrl,
            generatedImageId: result.id,
            generationTime: result.generationTimeMs,
            prompt: result.prompt,
        });
    },

    setError: (message: string) => {
        set({ status: 'error', errorMessage: message });
    },

    reset: () => {
        set({
            status: 'idle',
            generatedImageUrl: null,
            generatedImageId: null,
            generationTime: null,
            prompt: null,
            errorMessage: null,
        });
    },
}));
