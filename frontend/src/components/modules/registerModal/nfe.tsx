import { Box, Button, Modal, TextField, Typography } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react';
import styled from 'styled-components';

const ModalBox = styled(Box)`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  background-color: white;
  border: 2px solid #000;
  box-shadow: 24px;
  padding: 16px;
`;

interface RegisterPurchaseModalProps {
  open: boolean;
  onClose: () => void;
  userId: string;
}

const RegisterPurchaseModal: React.FC<RegisterPurchaseModalProps> = ({ open, onClose, userId }) => {
  const [uuid, setUuid] = useState('');
  const [feedbackMessage, setFeedbackMessage] = useState<string | null>(null); // Estado para armazenar a mensagem de sucesso/falha

  const handleSubmit = async () => {
    console.log('UUID:', uuid);
    try {
      const response = await axios.post(`http://localhost:8080/nfe/process/${userId}/${uuid}`);
      console.log('Response:', response);
      setFeedbackMessage('Código cadastrado com sucesso!');
    } catch (e) {
      console.error(e);
      setFeedbackMessage('NFE não cadastrada. Tente novamente.'); // Mensagem de erro
    }
  };

  const handleClose = () => {
    setFeedbackMessage(null); // Limpa a mensagem ao fechar
    setUuid(''); // Limpa o UUID para a próxima operação
    onClose();
  };

  return (
    <Modal open={open} onClose={handleClose}>
      <ModalBox>
        <h2>Registrar Compra</h2>
        {!feedbackMessage ? (
          <>
            <TextField
              label="UUID"
              value={uuid}
              onChange={(e) => setUuid(e.target.value)}
              fullWidth
              margin="normal"
            />
            <Button variant="contained" sx={{ backgroundColor: '#46207C' }} onClick={handleSubmit}>
              Enviar
            </Button>
          </>
        ) : (
          <Typography variant="h6" color={feedbackMessage.includes('sucesso') ? 'green' : 'red'}>
            {feedbackMessage}
          </Typography>
        )}
        {feedbackMessage && (
          <Button variant="contained" sx={{ backgroundColor: '#46207C' }} onClick={handleClose}>
            Fechar
          </Button>
        )}
      </ModalBox>
    </Modal>
  );
};

export default RegisterPurchaseModal;
