import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  font-family: sans-serif;
`;

export const Titulo = styled.h2`
  font-size: 2rem;
  font-weight: bold;
  color: #46207C; 
  margin-bottom: 20px;
`;

export const Formulario = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 330px;
`;

export const Campo = styled.div`
  margin-bottom: 15px;
`;

export const Label = styled.label`
  display: block;
  margin-bottom: 5px;
`;

export const Input = styled.input`
  width: 314px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
`;

export const Erro = styled.span`
  color: red;
  font-size: 12px;
  margin-top: 5px;
`;

export const Botao = styled.button`
  background-color: #46207C; 
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  width: 100%;
  cursor: pointer;

  &:hover {
    background-color: #311558;
  }
`;

export const TextoLink = styled.p`
  font-size: 14px;
  color: #666;
  text-align: center;
  margin-top: 16px;
`;

export const LinkStyled = styled.span`
  color: #46207C;
  cursor: pointer;
  text-decoration: underline;

  &:hover {
    color: #311558;
  }
`;