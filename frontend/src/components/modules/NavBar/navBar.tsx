import AccountCircle from '@mui/icons-material/AccountCircle';
import { Box } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import RegisterPurchaseModal from '../registerModal/nfe';
import { StyledAppBar, StyledButton, StyledTitle, StyledToolbar } from './navBar.styles.tsx';

interface User {
  id: string;
  name: string;
  points: number;
}

interface NavbarProps {
  user: User;
}

const Navbar: React.FC<NavbarProps> = ({ user }) => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const [currentUser, setCurrentUser] = useState(user); // Estado para gerenciar o usuário

  const handlePointsHistoryClick = () => {
    navigate(`/points-history/${currentUser.id}`);
  };

  const handleRegisterPurchaseClick = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  React.useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/client/${currentUser.id}`);
        console.log('Pontos resgatados com sucesso:', response.data);
        setCurrentUser(response.data); // Atualiza o estado do usuário com os novos dados
      } catch (error) {
        console.error('Erro ao resgatar pontos:', error);
      }
    };

    if (!modalOpen) {
      fetchUserData();
    }
  }, [modalOpen, currentUser.id]);

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
                <div>{currentUser.name}</div>
                <div>{`Pontos: ${currentUser.points}`}</div>
              </Box>
            </Box>
          </Box>
        </StyledToolbar>
      </StyledAppBar>
      <RegisterPurchaseModal open={modalOpen} onClose={handleCloseModal} userId={currentUser.id} />
    </>
  );
};

export default Navbar;