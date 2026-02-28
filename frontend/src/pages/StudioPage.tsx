import { Box } from '@mui/material';
import Header from '../components/layout/Header';
import OptionsSidebar from '../components/controls/OptionsSidebar';
import CanvasArea from '../components/canvas/CanvasArea';
import BottomControls from '../components/controls/BottomControls';

export default function StudioPage() {
    return (
        <Box className="studio-layout">
            <Header />
            <OptionsSidebar />
            <CanvasArea />
            <BottomControls />
        </Box>
    );
}
