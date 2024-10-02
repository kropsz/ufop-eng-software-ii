import { styled } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';

export const StyledAppBar = styled(AppBar)({
  backgroundColor: '#46207C',
});

export const StyledToolbar = styled(Toolbar)({
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
});

export const StyledTitle = styled(Typography)({
  fontFamily: 'Roboto, sans-serif',
});

export const StyledButton = styled(Button)({
  fontFamily: 'Roboto, sans-serif',
  marginLeft: '16px',
});

export const Icon = styled(IconButton)({
  padding: '6px',
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
});
