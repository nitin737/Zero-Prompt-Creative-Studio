import { createTheme } from '@mui/material/styles';

export const cosmicDarkTheme = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: '#7C3AED',
            light: '#A78BFA',
            dark: '#5B21B6',
        },
        secondary: {
            main: '#06B6D4',
            light: '#22D3EE',
            dark: '#0891B2',
        },
        background: {
            default: '#0A0A0F',
            paper: '#12121A',
        },
        text: {
            primary: '#F1F5F9',
            secondary: '#94A3B8',
            disabled: '#475569',
        },
        success: {
            main: '#22C55E',
        },
        warning: {
            main: '#F59E0B',
        },
        error: {
            main: '#EF4444',
        },
        divider: 'rgba(255, 255, 255, 0.08)',
    },
    typography: {
        fontFamily: '"Inter", system-ui, -apple-system, sans-serif',
        h1: {
            fontFamily: '"Space Grotesk", system-ui, sans-serif',
            fontWeight: 700,
            letterSpacing: '-0.02em',
            fontSize: '2.5rem',
        },
        h2: {
            fontFamily: '"Space Grotesk", system-ui, sans-serif',
            fontWeight: 600,
            letterSpacing: '-0.01em',
            fontSize: '1.75rem',
        },
        h3: {
            fontFamily: '"Space Grotesk", system-ui, sans-serif',
            fontWeight: 600,
            fontSize: '1.25rem',
        },
        h4: {
            fontFamily: '"Space Grotesk", system-ui, sans-serif',
            fontWeight: 600,
            fontSize: '1.125rem',
        },
        body1: {
            fontWeight: 400,
            fontSize: '1rem',
        },
        body2: {
            fontWeight: 400,
            fontSize: '0.875rem',
            letterSpacing: '0.02em',
        },
        caption: {
            fontWeight: 400,
            fontSize: '0.75rem',
            letterSpacing: '0.04em',
        },
        button: {
            textTransform: 'none',
            fontWeight: 600,
        },
    },
    shape: {
        borderRadius: 12,
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    borderRadius: 12,
                    padding: '10px 24px',
                    fontSize: '0.9375rem',
                    transition: 'all 200ms ease-out',
                    '&:hover': {
                        transform: 'scale(1.02)',
                    },
                },
                containedPrimary: {
                    background: 'linear-gradient(135deg, #7C3AED, #06B6D4)',
                    boxShadow: '0 4px 20px rgba(124, 58, 237, 0.3)',
                    '&:hover': {
                        background: 'linear-gradient(135deg, #6D28D9, #0891B2)',
                        boxShadow: '0 6px 24px rgba(124, 58, 237, 0.4)',
                    },
                },
            },
        },
        MuiPaper: {
            styleOverrides: {
                root: {
                    backgroundImage: 'none',
                    backgroundColor: '#12121A',
                },
            },
        },
        MuiSelect: {
            styleOverrides: {
                select: {
                    backgroundColor: 'rgba(255, 255, 255, 0.05)',
                    borderRadius: 12,
                },
            },
        },
        MuiOutlinedInput: {
            styleOverrides: {
                root: {
                    borderRadius: 12,
                    '& .MuiOutlinedInput-notchedOutline': {
                        borderColor: 'rgba(255, 255, 255, 0.08)',
                    },
                    '&:hover .MuiOutlinedInput-notchedOutline': {
                        borderColor: 'rgba(124, 58, 237, 0.4)',
                    },
                    '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                        borderColor: '#7C3AED',
                        boxShadow: '0 0 20px rgba(124, 58, 237, 0.2)',
                    },
                },
            },
        },
        MuiTooltip: {
            styleOverrides: {
                tooltip: {
                    backgroundColor: '#1A1A2E',
                    borderRadius: 8,
                    border: '1px solid rgba(255, 255, 255, 0.08)',
                    fontSize: '0.8125rem',
                },
            },
        },
        MuiDrawer: {
            styleOverrides: {
                paper: {
                    backgroundColor: '#12121A',
                    borderRight: '1px solid rgba(255, 255, 255, 0.08)',
                },
            },
        },
    },
});
