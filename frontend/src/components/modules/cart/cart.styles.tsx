import styled from 'styled-components';

export const Container = styled.div`
  background-color: #6a0dad; /* Purple background */
  color: white;
  padding: 20px;
  border-radius: 8px;
`;

export const ItemContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: white;
  color: black;
  padding: 10px;
  border-radius: 8px;
  margin-bottom: 20px;
`;

export const ItemImage = styled.img`
  width: 50px;
  height: 50px;
  margin-right: 10px;
`;

export const QuantityContainer = styled.div`
  display: flex;
  align-items: center;
`;

export const RemoveButton = styled.button`
  background-color: #FF0000; 
  border: none;
  border-radius: 4px;
  padding: 5px 10px;
  margin: 0 5px;
`;

export const SummaryContainer = styled.div`
  margin-top: 20px;
`;

export const SummaryItem = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
`;

export const RedeemButton = styled.button`
  background-color: #4caf50; /* Green background */
  color: white;
  padding: 10px;
  border: none;
  border-radius: 8px;
  width: 100%;
  cursor: pointer;
`;

export const Modal = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const ModalContent = styled.div`
  background: white;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
`;

export const ModalText = styled.p`
  margin: 10px 0;
  color: black;
`;

export const CloseButton = styled.button`
  margin-top: 10px;
  padding: 5px 10px;
  background: #46207C;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
`;