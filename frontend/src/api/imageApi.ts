import apiClient from './axiosClient';
import type { GenerateImagePayload, GeneratedImageResponse, OptionsMap } from '../types/studio.types';

export const imageApi = {
    generateImage: async (payload: GenerateImagePayload): Promise<GeneratedImageResponse> => {
        const { data } = await apiClient.post<GeneratedImageResponse>('/api/v1/images/generate', payload);
        return data;
    },

    editImage: async (payload: GenerateImagePayload): Promise<GeneratedImageResponse> => {
        const { data } = await apiClient.post<GeneratedImageResponse>('/api/v1/images/edit', payload);
        return data;
    },

    getImageUrl: (id: string): string => {
        const baseUrl = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';
        return `${baseUrl}/api/v1/images/${id}/file`;
    },

    getOptions: async (): Promise<OptionsMap> => {
        const { data } = await apiClient.get<OptionsMap>('/api/v1/options');
        return data;
    },

    getGallery: async (page = 0, size = 20) => {
        const { data } = await apiClient.get('/api/v1/gallery', { params: { page, size } });
        return data;
    },

    deleteImage: async (id: string) => {
        await apiClient.delete(`/api/v1/gallery/${id}`);
    },
};
