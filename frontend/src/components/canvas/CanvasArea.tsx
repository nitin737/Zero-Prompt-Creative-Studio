import { Box, Typography, IconButton, Tooltip } from '@mui/material';
import DownloadIcon from '@mui/icons-material/Download';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import { motion, AnimatePresence } from 'framer-motion';
import { useGenerationStore } from '../../store/useGenerationStore';
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';

function ShimmerLoader() {
    return (
        <Box
            sx={{
                width: '100%',
                maxWidth: 600,
                aspectRatio: '16/9',
                borderRadius: '16px',
                overflow: 'hidden',
                position: 'relative',
            }}
        >
            <Box
                className="shimmer-loader"
                sx={{
                    width: '100%',
                    height: '100%',
                    borderRadius: '16px',
                    border: '1px solid rgba(255,255,255,0.06)',
                }}
            />
            <Box
                sx={{
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    gap: 2,
                }}
            >
                <motion.div
                    animate={{ rotate: 360 }}
                    transition={{ duration: 2, repeat: Infinity, ease: 'linear' }}
                >
                    <AutoAwesomeIcon sx={{ fontSize: 32, color: '#7C3AED' }} />
                </motion.div>
                <Typography variant="body2" sx={{ color: '#94A3B8' }}>
                    Generating your masterpiece...
                </Typography>
            </Box>
        </Box>
    );
}

function EmptyCanvas() {
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                gap: 2,
                py: 8,
            }}
        >
            <motion.div
                animate={{ y: [0, -6, 0] }}
                transition={{ duration: 3, repeat: Infinity, ease: 'easeInOut' }}
            >
                <Box
                    sx={{
                        width: 80,
                        height: 80,
                        borderRadius: '20px',
                        background: 'linear-gradient(135deg, rgba(124,58,237,0.15), rgba(6,182,212,0.15))',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        border: '1px solid rgba(124,58,237,0.2)',
                    }}
                >
                    <AutoAwesomeIcon sx={{ fontSize: 36, color: '#7C3AED' }} />
                </Box>
            </motion.div>
            <Typography
                variant="h3"
                sx={{
                    fontFamily: '"Space Grotesk", sans-serif',
                    color: '#F1F5F9',
                    fontWeight: 600,
                }}
            >
                Ready to Create
            </Typography>
            <Typography
                variant="body2"
                sx={{
                    color: '#94A3B8',
                    textAlign: 'center',
                    maxWidth: 340,
                    lineHeight: 1.6,
                }}
            >
                Configure your options in the sidebar, type a subject below, and hit Generate to bring your vision to life.
            </Typography>
        </Box>
    );
}

export default function CanvasArea() {
    const { status, generatedImageUrl, generationTime, prompt } = useGenerationStore();

    const handleDownload = () => {
        if (generatedImageUrl) {
            const link = document.createElement('a');
            link.href = generatedImageUrl;
            link.download = `zpcs-${Date.now()}.png`;
            link.click();
        }
    };

    const handleCopyPrompt = () => {
        if (prompt) {
            navigator.clipboard.writeText(prompt);
        }
    };

    return (
        <Box
            className="studio-canvas"
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                p: 3,
                position: 'relative',
                overflow: 'auto',
            }}
        >
            <AnimatePresence mode="wait">
                {status === 'idle' && (
                    <motion.div
                        key="empty"
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        exit={{ opacity: 0 }}
                    >
                        <EmptyCanvas />
                    </motion.div>
                )}

                {status === 'loading' && (
                    <motion.div
                        key="loading"
                        initial={{ opacity: 0, scale: 0.95 }}
                        animate={{ opacity: 1, scale: 1 }}
                        exit={{ opacity: 0 }}
                    >
                        <ShimmerLoader />
                    </motion.div>
                )}

                {status === 'success' && generatedImageUrl && (
                    <motion.div
                        key="result"
                        initial={{ opacity: 0, scale: 0.95 }}
                        animate={{ opacity: 1, scale: 1 }}
                        transition={{ duration: 0.5, ease: 'easeOut' }}
                        style={{
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                            gap: 16,
                            width: '100%',
                            maxWidth: 700,
                        }}
                    >
                        <Box
                            sx={{
                                position: 'relative',
                                borderRadius: '16px',
                                overflow: 'hidden',
                                boxShadow: '0 16px 64px rgba(0,0,0,0.5)',
                                border: '1px solid rgba(255,255,255,0.08)',
                                width: '100%',
                            }}
                        >
                            <img
                                src={generatedImageUrl}
                                alt="Generated"
                                style={{
                                    width: '100%',
                                    height: 'auto',
                                    display: 'block',
                                }}
                            />
                            {/* Toolbar overlay */}
                            <Box
                                sx={{
                                    position: 'absolute',
                                    bottom: 0,
                                    left: 0,
                                    right: 0,
                                    p: 1.5,
                                    display: 'flex',
                                    justifyContent: 'flex-end',
                                    gap: 0.5,
                                    background: 'linear-gradient(transparent, rgba(0,0,0,0.6))',
                                }}
                            >
                                <Tooltip title="Download">
                                    <IconButton
                                        onClick={handleDownload}
                                        size="small"
                                        sx={{
                                            color: '#fff',
                                            background: 'rgba(255,255,255,0.1)',
                                            backdropFilter: 'blur(8px)',
                                            '&:hover': { background: 'rgba(255,255,255,0.2)' },
                                        }}
                                    >
                                        <DownloadIcon fontSize="small" />
                                    </IconButton>
                                </Tooltip>
                                <Tooltip title="Copy Prompt">
                                    <IconButton
                                        onClick={handleCopyPrompt}
                                        size="small"
                                        sx={{
                                            color: '#fff',
                                            background: 'rgba(255,255,255,0.1)',
                                            backdropFilter: 'blur(8px)',
                                            '&:hover': { background: 'rgba(255,255,255,0.2)' },
                                        }}
                                    >
                                        <ContentCopyIcon fontSize="small" />
                                    </IconButton>
                                </Tooltip>
                            </Box>
                        </Box>

                        {/* Metadata */}
                        <Box
                            sx={{
                                display: 'flex',
                                alignItems: 'center',
                                gap: 2,
                                flexWrap: 'wrap',
                                justifyContent: 'center',
                            }}
                        >
                            {generationTime && (
                                <Typography
                                    variant="caption"
                                    sx={{
                                        color: '#94A3B8',
                                        background: 'rgba(255,255,255,0.05)',
                                        px: 1.5,
                                        py: 0.5,
                                        borderRadius: '8px',
                                        border: '1px solid rgba(255,255,255,0.06)',
                                    }}
                                >
                                    ⏱️ {(generationTime / 1000).toFixed(1)}s
                                </Typography>
                            )}
                            {prompt && (
                                <Typography
                                    variant="caption"
                                    sx={{
                                        color: '#94A3B8',
                                        maxWidth: 400,
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap',
                                    }}
                                >
                                    💬 {prompt}
                                </Typography>
                            )}
                        </Box>
                    </motion.div>
                )}

                {status === 'error' && (
                    <motion.div
                        key="error"
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        exit={{ opacity: 0 }}
                    >
                        <Box
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center',
                                gap: 2,
                                p: 4,
                            }}
                        >
                            <Typography variant="h3" sx={{ color: '#EF4444' }}>
                                Generation Failed
                            </Typography>
                            <Typography variant="body2" sx={{ color: '#94A3B8', textAlign: 'center', maxWidth: 400 }}>
                                {useGenerationStore.getState().errorMessage || 'Something went wrong. Please try again.'}
                            </Typography>
                        </Box>
                    </motion.div>
                )}
            </AnimatePresence>
        </Box>
    );
}
