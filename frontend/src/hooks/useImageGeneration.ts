import { useCallback } from 'react';
import { useStudioStore } from '../store/useStudioStore';
import { useGenerationStore } from '../store/useGenerationStore';

export function useImageGeneration() {
    const toRequestPayload = useStudioStore((s) => s.toRequestPayload);
    const subject = useStudioStore((s) => s.subject);
    const generate = useGenerationStore((s) => s.generate);
    const status = useGenerationStore((s) => s.status);

    const handleGenerate = useCallback(async () => {
        const payload = toRequestPayload();
        await generate(payload);
    }, [toRequestPayload, generate]);

    const isGenerating = status === 'loading';
    const canGenerate = subject.trim().length > 0 && !isGenerating;

    return { generate: handleGenerate, isGenerating, canGenerate };
}
