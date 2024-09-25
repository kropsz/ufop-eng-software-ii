import React, { useState } from 'react';
import { Box, Menu, MenuItem, IconButton } from '@mui/material';
import { AccountCircle } from '@mui/icons-material';
import { 
  StyledAppBar, 
  StyledToolbar, 
  StyledTitle, 
  StyledButton, 
} from './navBar.styles';

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

  const handleMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  return (
    <StyledAppBar position="static">
      <StyledToolbar>
        <StyledTitle variant="h5">
          Pontos Club
        </StyledTitle>
        <Box sx={{ display: 'flex', alignItems: 'center', marginLeft: 'auto' }}>
          <StyledButton color="inherit">
            Registrar Compra
          </StyledButton>
          <StyledButton color="inherit">
            Histórico de Pontos
          </StyledButton>
          <StyledButton color="inherit">
            Recompensas
          </StyledButton>
          <IconButton
            size="large"
            aria-label="account of current user"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            onClick={handleMenuOpen}
            color="inherit"
          >
            <AccountCircle />
          </IconButton>
          <Menu
            id="menu-appbar"
            anchorEl={anchorEl}
            anchorOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            keepMounted
            transformOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            open={Boolean(anchorEl)}
            onClose={handleMenuClose}
          >
            <MenuItem onClick={handleMenuClose}>Login</MenuItem>
          </Menu>
        </Box>
      </StyledToolbar>
    </StyledAppBar>
  );
};

export default Navbar;