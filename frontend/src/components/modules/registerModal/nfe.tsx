import React, { useState } from 'react';
import { Modal, Box, TextField, Button } from '@mui/material';
import styled from 'styled-components';
import axios from 'axios';

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

  const handleSubmit = async () => {
    console.log('UUID:', uuid);
    try {
      const response = await axios.post(`http://localhost:8080/nfe/process/${userId}/${uuid}`);
      console.log('Response:', response);
    } catch (e) {
      console.error(e);
    } finally {
      onClose();
    }
  };

  return (
    <Modal open={open} onClose={onClose}>
      <ModalBox>
        <h2>Registrar Compra</h2>
        <TextField
          label="UUID"
          value={uuid}
          onChange={(e) => setUuid(e.target.value)}
          fullWidth
          margin="normal"
        />
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          Enviar
        </Button>
      </ModalBox>
    </Modal>
  );
};

export default RegisterPurchaseModal;