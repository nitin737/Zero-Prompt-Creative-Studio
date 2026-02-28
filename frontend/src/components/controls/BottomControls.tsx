import { Box, TextField, Button, ToggleButtonGroup, ToggleButton, Typography, IconButton, Tooltip } from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import RestartAltIcon from '@mui/icons-material/RestartAlt';
import BoltIcon from '@mui/icons-material/Bolt';
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import { useStudioStore } from '../../store/useStudioStore';
import { useImageGeneration } from '../../hooks/useImageGeneration';
import { useOperationMode } from '../../hooks/useOperationMode';
import type { ThinkingLevel } from '../../types/studio.types';

export default function BottomControls() {
    const store = useStudioStore();
    const { generate, isGenerating, canGenerate } = useImageGeneration();
    const { showImageUpload, subjectPlaceholder } = useOperationMode();

    const onDrop = useCallback(
        (acceptedFiles: File[]) => {
            const file = acceptedFiles[0];
            if (file) {
                store.setUploadedImage(file);
                const reader = new FileReader();
                reader.onload = () => {
                    const base64 = (reader.result as string).split(',')[1];
                    store.setSourceImageBase64(base64);
                };
                reader.readAsDataURL(file);
            }
        },
        [store]
    );

    const { getRootProps, getInputProps, isDragActive } = useDropzone({
        onDrop,
        accept: { 'image/*': ['.png', '.jpg', '.jpeg', '.webp'] },
        maxFiles: 1,
        disabled: !showImageUpload,
    });

    const handleKeyPress = (e: React.KeyboardEvent) => {
        if (e.key === 'Enter' && !e.shiftKey && canGenerate) {
            e.preventDefault();
            generate();
        }
    };

    return (
        <Box
            className="studio-bottom"
            sx={{
                p: 2,
                display: 'flex',
                flexDirection: 'column',
                gap: 1.5,
            }}
        >
            {/* Image upload zone */}
            {showImageUpload && (
                <Box
                    {...getRootProps()}
                    sx={{
                        p: 2,
                        borderRadius: '12px',
                        border: `2px dashed ${isDragActive ? '#7C3AED' : 'rgba(255,255,255,0.1)'}`,
                        background: isDragActive ? 'rgba(124,58,237,0.05)' : 'transparent',
                        textAlign: 'center',
                        cursor: 'pointer',
                        transition: 'all 200ms ease-out',
                        '&:hover': {
                            border: '2px dashed rgba(124,58,237,0.4)',
                            background: 'rgba(124,58,237,0.03)',
                        },
                    }}
                >
                    <input {...getInputProps()} />
                    <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 1 }}>
                        <CloudUploadIcon sx={{ fontSize: 20, color: '#94A3B8' }} />
                        <Typography variant="body2" sx={{ color: '#94A3B8' }}>
                            {store.uploadedImage ? store.uploadedImage.name : 'Drop image here or click to upload'}
                        </Typography>
                    </Box>
                </Box>
            )}

            {/* Main input row */}
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1.5 }}>
                <TextField
                    id="subject-input"
                    fullWidth
                    multiline
                    maxRows={3}
                    placeholder={subjectPlaceholder}
                    value={store.subject}
                    onChange={(e) => store.setSubject(e.target.value)}
                    onKeyDown={handleKeyPress}
                    disabled={isGenerating}
                    sx={{
                        '& .MuiOutlinedInput-root': {
                            borderRadius: '14px',
                            background: 'rgba(255,255,255,0.03)',
                            fontSize: '0.95rem',
                        },
                    }}
                />

                <ToggleButtonGroup
                    value={store.thinkingLevel}
                    exclusive
                    onChange={(_, val) => val && store.setThinkingLevel(val as ThinkingLevel)}
                    size="small"
                    sx={{
                        '& .MuiToggleButton-root': {
                            border: '1px solid rgba(255,255,255,0.1)',
                            color: '#94A3B8',
                            px: 1.5,
                            py: 1,
                            '&.Mui-selected': {
                                background: 'rgba(124,58,237,0.15)',
                                color: '#A78BFA',
                                borderColor: 'rgba(124,58,237,0.3)',
                            },
                        },
                    }}
                >
                    <ToggleButton value="FAST" id="thinking-fast">
                        <Tooltip title="Fast — minimal thinking">
                            <BoltIcon sx={{ fontSize: 18 }} />
                        </Tooltip>
                    </ToggleButton>
                    <ToggleButton value="CREATIVE" id="thinking-creative">
                        <Tooltip title="Creative — deep reasoning">
                            <AutoAwesomeIcon sx={{ fontSize: 18 }} />
                        </Tooltip>
                    </ToggleButton>
                </ToggleButtonGroup>

                <Tooltip title="Reset all options">
                    <IconButton
                        onClick={store.resetOptions}
                        size="small"
                        sx={{
                            color: '#94A3B8',
                            '&:hover': { color: '#F1F5F9', background: 'rgba(255,255,255,0.05)' },
                        }}
                    >
                        <RestartAltIcon />
                    </IconButton>
                </Tooltip>

                <Button
                    id="generate-button"
                    variant="contained"
                    onClick={generate}
                    disabled={!canGenerate}
                    endIcon={
                        isGenerating ? (
                            <AutoAwesomeIcon
                                sx={{
                                    animation: 'spin 1s linear infinite',
                                    '@keyframes spin': { '100%': { transform: 'rotate(360deg)' } },
                                }}
                            />
                        ) : (
                            <SendIcon />
                        )
                    }
                    sx={{
                        minWidth: 140,
                        height: 48,
                        borderRadius: '14px',
                        fontWeight: 700,
                        fontSize: '0.95rem',
                    }}
                >
                    {isGenerating ? 'Creating...' : 'Generate'}
                </Button>
            </Box>
        </Box>
    );
}
