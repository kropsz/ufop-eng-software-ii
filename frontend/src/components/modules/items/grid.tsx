import axios from 'axios';
import React, { useEffect, useState } from 'react';
import ShoppingCart from '../cart/cart.tsx';
import {
  AddToCartButton,
  GridContainer,
  MainContainer,
  ProductCard,
  ProductPrice,
  ProductStock,
  ProductTitle,
} from './grid.styles.tsx';

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

interface User {
  id: string;
  name: string;
  email: string;
  points: number;
}

const ProductGrid: React.FC<{ user: User }> = ({ user }) => {
  const [products, setProducts] = useState<Product[]>([]);
  const [cart, setCart] = useState<CartItem[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get<Product[]>('http://localhost:8080/products'); 
        console.log('API response:', response.data); // Log da resposta da API
        if (Array.isArray(response.data)) {
          setProducts(response.data);
        } else {
          console.warn('API response is not an array:', response.data); // Log de aviso
          setProducts([]);
        }
      } catch (error) {
        console.error('Error fetching products:', error);
        setProducts([]);
      }
    };
    fetchProducts();
  }, []);

  const addToCart = (product: Product) => {
    setCart((prevCart) => [...prevCart, { ...product, quantity: 1 }]);
  };

  const isInCart = (productId: string) => {
    return cart.some((item) => item.id === productId);
  };

  return (
    <MainContainer>
      <GridContainer>
        {products.map((product) => (
          <ProductCard key={product.id}>
            <img src={product.imageUrl} alt={product.name} width={200} />
            <ProductTitle>{product.name}</ProductTitle>
            <ProductPrice>{`Pts. ${product.priceInPoints}`}</ProductPrice>
            <ProductStock>{`Stock: ${product.stock}`}</ProductStock>
            <AddToCartButton
              onClick={() => addToCart(product)}
              disabled={isInCart(product.id)}
              inCart={isInCart(product.id)}
            >
              {isInCart(product.id) ? 'In Cart' : 'Add to Cart'}
            </AddToCartButton>
          </ProductCard>
        ))}
      </GridContainer>
      <ShoppingCart cart={cart} setCart={setCart} clientId={user.id} />
    </MainContainer>
  );
};

export default ProductGrid;