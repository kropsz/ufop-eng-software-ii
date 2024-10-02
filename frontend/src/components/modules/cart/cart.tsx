import React, { useState } from 'react';
import axios from 'axios';
import {
  Container,
  ItemContainer,
  ItemImage,
  QuantityContainer,
  RemoveButton,
  SummaryContainer,
  SummaryItem,
  RedeemButton,
  Modal,
  ModalContent,
  CloseButton
} from './cart.styles';

interface Product {
  id: string;
  name: string;
  description: string;
  stock: number;
  priceInPoints: number;
  imageUrl: string;
}

interface CartItem extends Product {
  quantity: number;
}

const ShoppingCart: React.FC<{ cart: CartItem[], setCart: React.Dispatch<React.SetStateAction<CartItem[]>>, clientId: string }> = ({ cart, setCart, clientId }) => {
  const [showModal, setShowModal] = useState(false);

  const subtotal = cart.reduce((sum, item) => sum + item.priceInPoints * item.quantity, 0);
  const tax = subtotal * 0.1;
  const total = subtotal + tax;

  const redeemRewards = async (clientId: string, cart: CartItem[]) => {
    console.log('User id is:', clientId);
    try {
      const productIds = cart.map(item => item.id);
  
      const productDto = {
        products: productIds
      };
  
      const response = await axios.post(`http://localhost:8080/nfe/exchange/${clientId}`, productDto);
  
      if (response.data) {
        console.log('Recompensas resgatadas com sucesso');
        setCart([]); // Limpar o carrinho
        setShowModal(true); // Mostrar o modal de sucesso
      } else {
        console.error('Falha ao resgatar recompensas');
      }
    } catch (error) {
      console.error('Erro ao resgatar recompensas:', error);
    }
  };

  const handleRedeemRewards = () => {
    redeemRewards(clientId, cart);
  };

  const removeFromCart = (productId: string) => {
    setCart((prevCart) => prevCart.filter(item => item.id !== productId));
  };

  const closeModal = () => {
    setShowModal(false);
  };

  return (
    <Container>
      {cart.map((item) => (
        <ItemContainer key={item.id}>
          <ItemImage src={item.imageUrl} alt={item.name} />
          <div>
            <div>{item.name}</div>
            <div>{`PTS ${item.priceInPoints}`}</div>
          </div>
          <QuantityContainer>
            <span>{item.quantity}</span>
            <RemoveButton onClick={() => removeFromCart(item.id)}>X</RemoveButton>
          </QuantityContainer>
          <div>{`PTS ${item.priceInPoints * item.quantity}`}</div>
        </ItemContainer>
      ))}

      <SummaryContainer>
        <SummaryItem>
          <span>Subtotal</span>
          <span>{`PTS ${subtotal}`}</span>
        </SummaryItem>
        <SummaryItem>
          <span>Tax</span>
          <span>{`PTS ${tax}`}</span>
        </SummaryItem>
        <SummaryItem>
          <span>Total</span>
          <span>{`PTS ${total}`}</span>
        </SummaryItem>
      </SummaryContainer>

      <RedeemButton onClick={handleRedeemRewards}>Retirar Recompensa</RedeemButton>

      {showModal && (
        <Modal>
          <ModalContent>
            <h2>Sucesso!</h2>
            <p>Recompensa retirada com sucesso.</p>
            <CloseButton onClick={closeModal}>Fechar</CloseButton>
          </ModalContent>
        </Modal>
      )}
    </Container>
  );
};

export default ShoppingCart;