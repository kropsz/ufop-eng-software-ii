import styled from 'styled-components';

export const MainContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 20px;
`;

export const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
  padding: 20px;
`;

export const ProductCard = styled.div`
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  text-align: center;
  border-color: var(--muted-foreground);
  transition: transform 0.2s;
  &:hover {
    transform: scale(1.05);
  }
`;

export const ProductTitle = styled.h3`
  font-size: 1.2rem;
  margin: 10px 0;
`;

export const ProductStock = styled.p`
  font-size: 1rem;
  color: var(--muted-foreground);
`;

export const ProductPrice = styled.p`
  font-size: 1rem;
  color: var(--muted-foreground);
`;

export const AddToCartButton = styled.button<{ inCart: boolean }>`
  background-color: ${({ inCart }) => (inCart ? 'gray' : '#46207C')};
  border-radius: 8px;
  color: white;
  padding: 10px;
  border: none;
  cursor: ${({ inCart }) => (inCart ? 'not-allowed' : 'pointer')};
  opacity: ${({ inCart }) => (inCart ? 0.6 : 1)};
  
  &:hover {
    background-color: ${({ inCart }) => (inCart ? 'gray' : 'darkblue')};
  }
`;