import { AppBar, Toolbar, Typography, Box, IconButton, Chip } from '@mui/material';
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import GitHubIcon from '@mui/icons-material/GitHub';

export default function Header() {
    return (
        <AppBar
            position="static"
            elevation={0}
            sx={{
                background: 'rgba(18, 18, 26, 0.8)',
                backdropFilter: 'blur(16px)',
                borderBottom: '1px solid rgba(255, 255, 255, 0.08)',
                gridArea: 'header',
            }}
        >
            <Toolbar sx={{ justifyContent: 'space-between', minHeight: '64px !important' }}>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1.5 }}>
                    <Box
                        sx={{
                            width: 36,
                            height: 36,
                            borderRadius: '10px',
                            background: 'linear-gradient(135deg, #7C3AED, #06B6D4)',
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center',
                            boxShadow: '0 4px 12px rgba(124, 58, 237, 0.3)',
                        }}
                    >
                        <AutoAwesomeIcon sx={{ fontSize: 20, color: '#fff' }} />
                    </Box>
                    <Typography
                        variant="h3"
                        sx={{
                            fontFamily: '"Space Grotesk", sans-serif',
                            fontWeight: 700,
                            fontSize: '1.2rem',
                            background: 'linear-gradient(135deg, #F1F5F9, #94A3B8)',
                            WebkitBackgroundClip: 'text',
                            WebkitTextFillColor: 'transparent',
                        }}
                    >
                        Zero-Prompt Creative Studio
                    </Typography>
                    <Chip
                        label="Beta"
                        size="small"
                        sx={{
                            background: 'rgba(124, 58, 237, 0.15)',
                            color: '#A78BFA',
                            fontSize: '0.7rem',
                            height: 22,
                            fontWeight: 600,
                        }}
                    />
                </Box>

                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                    <Chip
                        label="Gemini 2.0 Flash"
                        size="small"
                        sx={{
                            background: 'rgba(6, 182, 212, 0.1)',
                            color: '#06B6D4',
                            fontSize: '0.75rem',
                            fontWeight: 500,
                            border: '1px solid rgba(6, 182, 212, 0.2)',
                        }}
                    />
                    <IconButton
                        size="small"
                        sx={{ color: '#94A3B8', '&:hover': { color: '#F1F5F9' } }}
                        href="https://github.com/nitin737/Zero-Prompt-Creative-Studio"
                        target="_blank"
                    >
                        <GitHubIcon fontSize="small" />
                    </IconButton>
                </Box>
            </Toolbar>
        </AppBar>
    );
}
