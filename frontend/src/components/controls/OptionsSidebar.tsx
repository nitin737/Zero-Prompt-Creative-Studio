import {
    Box,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    Typography,
    Divider,
    Slider,
} from '@mui/material';
import { useStudioStore } from '../../store/useStudioStore';
import { useOperationMode } from '../../hooks/useOperationMode';
import type {
    AestheticStyle,
    AspectRatio,
    CameraComposition,
    ColorPalette,
    LensEffect,
    LightingSetup,
    OperationMode,
    ResolutionQuality,
    StyleIntensity,
} from '../../types/studio.types';

const OPERATION_MODES = [
    { value: 'TEXT_TO_IMAGE', label: '✨ Generate New' },
    { value: 'EDIT_EXISTING', label: '✏️ Edit Existing' },
    { value: 'STYLE_TRANSFER', label: '🎨 Style Transfer' },
];

const STYLES = [
    { value: 'PHOTOREALISTIC', label: '📸 Photorealistic' },
    { value: 'ISOMETRIC_3D', label: '🧊 Isometric 3D' },
    { value: 'FLAT_VECTOR', label: '📐 Flat Vector' },
    { value: 'CINEMATIC', label: '🎬 Cinematic' },
    { value: 'CYBERPUNK', label: '🌃 Cyberpunk' },
    { value: 'WATERCOLOR', label: '🎨 Watercolor' },
    { value: 'SKETCH', label: '✏️ Sketch' },
    { value: 'POP_ART', label: '🎯 Pop Art' },
];

const LIGHTING = [
    { value: 'GOLDEN_HOUR', label: '🌅 Golden Hour' },
    { value: 'STUDIO_SOFTBOX', label: '💡 Studio Softbox' },
    { value: 'NEON_VOLUMETRIC', label: '🔮 Neon Volumetric' },
    { value: 'HIGH_CONTRAST', label: '🌑 High Contrast' },
    { value: 'HARSH_SUNLIGHT', label: '☀️ Harsh Sunlight' },
];

const CAMERAS = [
    { value: 'MACRO', label: '🔍 Macro Close-up' },
    { value: 'WIDE_ANGLE', label: '🌄 Wide Angle' },
    { value: 'DRONE_AERIAL', label: '🚁 Drone/Aerial' },
    { value: 'EYE_LEVEL', label: '👁️ Eye-Level' },
    { value: 'ISOMETRIC', label: '📐 Isometric' },
];

const PALETTES = [
    { value: 'VIBRANT', label: '🌈 Vibrant' },
    { value: 'MUTED', label: '🎀 Muted/Pastel' },
    { value: 'MONOCHROMATIC', label: '⚫ Monochromatic' },
    { value: 'SEPIA', label: '📜 Sepia' },
    { value: 'HIGH_CONTRAST_BW', label: '◼️ Black & White' },
];

const LENS_EFFECTS = [
    { value: 'DEEP_FOCUS', label: '🎯 Deep Focus' },
    { value: 'SHALLOW_DOF', label: '🔵 Bokeh (Shallow DOF)' },
    { value: 'MOTION_BLUR', label: '💨 Motion Blur' },
    { value: 'FISHEYE', label: '🐟 Fisheye' },
];

const ASPECT_RATIOS = [
    { value: 'RATIO_1_1', label: '1:1 Square' },
    { value: 'RATIO_16_9', label: '16:9 Landscape' },
    { value: 'RATIO_9_16', label: '9:16 Vertical' },
    { value: 'RATIO_4_3', label: '4:3 Standard' },
    { value: 'RATIO_21_9', label: '21:9 Cinematic' },
];

const RESOLUTIONS = [
    { value: 'DRAFT', label: '⚡ Draft (Fast)' },
    { value: 'STANDARD', label: '📱 Standard' },
    { value: 'PRODUCTION', label: '🎯 Production' },
];

const INTENSITY_MARKS = [
    { value: 0, label: 'Subtle' },
    { value: 1, label: 'Balanced' },
    { value: 2, label: 'Aggressive' },
];

const INTENSITY_MAP: StyleIntensity[] = ['SUBTLE', 'BALANCED', 'AGGRESSIVE'];

const selectSx = {
    '& .MuiSelect-select': {
        py: 1.25,
        fontSize: '0.875rem',
    },
};

interface StudioSelectProps {
    label: string;
    value: string | undefined;
    options: { value: string; label: string }[];
    onChange: (val: string | undefined) => void;
    id: string;
    allowNone?: boolean;
}

function StudioSelect({ label, value, options, onChange, id, allowNone = true }: StudioSelectProps) {
    return (
        <FormControl fullWidth size="small">
            <InputLabel id={`${id}-label`} sx={{ fontSize: '0.875rem' }}>
                {label}
            </InputLabel>
            <Select
                labelId={`${id}-label`}
                id={id}
                value={value || ''}
                label={label}
                onChange={(e) => onChange(e.target.value || undefined)}
                sx={selectSx}
            >
                {allowNone && (
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                )}
                {options.map((opt) => (
                    <MenuItem key={opt.value} value={opt.value}>
                        {opt.label}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
}

export default function OptionsSidebar() {
    const store = useStudioStore();
    const { showStyleIntensity } = useOperationMode();

    return (
        <Box
            className="studio-sidebar"
            sx={{ p: 2, display: 'flex', flexDirection: 'column', gap: 2 }}
        >
            <Typography
                variant="body2"
                sx={{
                    color: '#94A3B8',
                    fontWeight: 600,
                    textTransform: 'uppercase',
                    letterSpacing: '0.08em',
                    fontSize: '0.7rem',
                    mt: 0.5,
                }}
            >
                Generation Options
            </Typography>

            <StudioSelect
                label="Operation Mode"
                value={store.operationMode}
                options={OPERATION_MODES}
                onChange={(v) => store.setOperationMode((v || 'TEXT_TO_IMAGE') as OperationMode)}
                id="operation-mode-select"
                allowNone={false}
            />

            <Divider sx={{ borderColor: 'rgba(255,255,255,0.06)' }} />

            <Typography
                variant="body2"
                sx={{
                    color: '#94A3B8',
                    fontWeight: 600,
                    textTransform: 'uppercase',
                    letterSpacing: '0.08em',
                    fontSize: '0.7rem',
                }}
            >
                Visual Settings
            </Typography>

            <StudioSelect
                label="Aesthetic Style"
                value={store.aestheticStyle}
                options={STYLES}
                onChange={(v) => store.setAestheticStyle(v as AestheticStyle | undefined)}
                id="aesthetic-style-select"
            />
            <StudioSelect
                label="Lighting"
                value={store.lighting}
                options={LIGHTING}
                onChange={(v) => store.setLighting(v as LightingSetup | undefined)}
                id="lighting-select"
            />
            <StudioSelect
                label="Camera & Composition"
                value={store.cameraComposition}
                options={CAMERAS}
                onChange={(v) => store.setCameraComposition(v as CameraComposition | undefined)}
                id="camera-select"
            />
            <StudioSelect
                label="Color Palette"
                value={store.colorPalette}
                options={PALETTES}
                onChange={(v) => store.setColorPalette(v as ColorPalette | undefined)}
                id="color-palette-select"
            />
            <StudioSelect
                label="Lens Effect"
                value={store.lensEffect}
                options={LENS_EFFECTS}
                onChange={(v) => store.setLensEffect(v as LensEffect | undefined)}
                id="lens-effect-select"
            />

            <Divider sx={{ borderColor: 'rgba(255,255,255,0.06)' }} />

            <Typography
                variant="body2"
                sx={{
                    color: '#94A3B8',
                    fontWeight: 600,
                    textTransform: 'uppercase',
                    letterSpacing: '0.08em',
                    fontSize: '0.7rem',
                }}
            >
                Output Settings
            </Typography>

            <StudioSelect
                label="Aspect Ratio"
                value={store.aspectRatio}
                options={ASPECT_RATIOS}
                onChange={(v) => store.setAspectRatio((v || 'RATIO_16_9') as AspectRatio)}
                id="aspect-ratio-select"
                allowNone={false}
            />
            <StudioSelect
                label="Resolution"
                value={store.resolution}
                options={RESOLUTIONS}
                onChange={(v) => store.setResolution((v || 'STANDARD') as ResolutionQuality)}
                id="resolution-select"
                allowNone={false}
            />

            {showStyleIntensity && (
                <Box sx={{ px: 1 }}>
                    <Typography variant="body2" sx={{ color: '#94A3B8', mb: 1, fontSize: '0.8rem' }}>
                        Style Intensity
                    </Typography>
                    <Slider
                        id="intensity-slider"
                        value={INTENSITY_MAP.indexOf(store.styleIntensity || 'BALANCED')}
                        min={0}
                        max={2}
                        step={1}
                        marks={INTENSITY_MARKS}
                        onChange={(_, val) =>
                            store.setStyleIntensity(INTENSITY_MAP[val as number])
                        }
                        sx={{
                            color: '#7C3AED',
                            '& .MuiSlider-markLabel': {
                                fontSize: '0.7rem',
                                color: '#94A3B8',
                            },
                        }}
                    />
                </Box>
            )}
        </Box>
    );
}
