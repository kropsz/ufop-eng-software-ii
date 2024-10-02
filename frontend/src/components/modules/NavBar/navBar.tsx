import React, { useState } from 'react';
import { Box } from '@mui/material';
import AccountCircle from '@mui/icons-material/AccountCircle';
import { StyledAppBar, StyledToolbar, StyledTitle, StyledButton } from './navBar.styles';
import { useNavigate } from 'react-router-dom';
import RegisterPurchaseModal from '../registerModal/nfe';

interface NavbarProps {
  user: {
    id: string;
    name: string;
    points: number;
  };
}

const Navbar: React.FC<NavbarProps> = ({ user }) => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);

  const handlePointsHistoryClick = () => {
    navigate(`/points-history/${user.id}`);
  };

  const handleRegisterPurchaseClick = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  return (
    <>
      <StyledAppBar position="static">
        <StyledToolbar>
          <StyledTitle variant="h5">
            Pontos Club
          </StyledTitle>
          <Box sx={{ display: 'flex', alignItems: 'center', marginLeft: 'auto' }}>
            <StyledButton color="inherit" onClick={handleRegisterPurchaseClick}>
              Registrar Compra
            </StyledButton>
            <StyledButton color="inherit" onClick={handlePointsHistoryClick}>
              Histórico de Pontos
            </StyledButton>
            <Box sx={{ display: 'flex', alignItems: 'center', marginLeft: '16px' }}>
              <AccountCircle />
              <Box sx={{ marginLeft: '8px' }}>
                <div>{user.name}</div>
                <div>{`Pontos: ${user.points}`}</div>
              </Box>
            </Box>
          </Box>
        </StyledToolbar>
      </StyledAppBar>
      <RegisterPurchaseModal open={modalOpen} onClose={handleCloseModal} userId={user.id} /> {/* Pass userId here */}
    </>
  );
};

export default Navbar;