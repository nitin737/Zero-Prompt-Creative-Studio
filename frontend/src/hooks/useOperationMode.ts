import { useStudioStore } from '../store/useStudioStore';

export function useOperationMode() {
    const mode = useStudioStore((s) => s.operationMode);

    const showImageUpload = mode === 'EDIT_EXISTING' || mode === 'STYLE_TRANSFER';
    const showStyleIntensity = mode === 'STYLE_TRANSFER';
    const subjectPlaceholder =
        mode === 'EDIT_EXISTING'
            ? 'Describe the edit you want to make...'
            : 'Describe your image...';

    return { mode, showImageUpload, showStyleIntensity, subjectPlaceholder };
}
