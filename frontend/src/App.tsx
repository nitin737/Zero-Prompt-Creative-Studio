import { ThemeProvider, CssBaseline } from '@mui/material';
import { Toaster } from 'react-hot-toast';
import { cosmicDarkTheme } from './styles/theme';
import StudioPage from './pages/StudioPage';
import './styles/index.css';

function App() {
  return (
    <ThemeProvider theme={cosmicDarkTheme}>
      <CssBaseline />
      <Toaster
        position="top-right"
        toastOptions={{
          style: {
            background: '#1A1A2E',
            color: '#F1F5F9',
            border: '1px solid rgba(255,255,255,0.08)',
            borderRadius: '12px',
          },
        }}
      />
      <StudioPage />
    </ThemeProvider>
  );
}

export default App;
