import React from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Container, Titulo, Formulario, Campo, Label, Input, Erro, Botao, TextoLink, LinkStyled } from './login.styles';

interface IFormInput {
  email: string;
  password: string;
}

const Login: React.FC = () => {
  const { register, handleSubmit, formState: { errors }, } = useForm<IFormInput>();
  const navigate = useNavigate();

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      const response = await axios.post('http://localhost:8080/client/login', data);
      console.log('Login realizado com sucesso:', response.data);
      navigate('/home', { state: { user: response.data } }); 
    } catch (error) {
      console.error('Erro ao realizar login:', error);
    }
  };

  const handleLoginClick = () => {
    window.location.href = '/cadastro'; 
  };

  return (
    <Container>
      <Titulo>Login</Titulo>
      <Formulario onSubmit={handleSubmit(onSubmit)}>
        <Campo>
          <Label htmlFor="email">Email:</Label>
          <Input
            type="text"
            id="email"
            {...register('email', {
              required: 'Email é obrigatório',
              pattern: { value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i, message: 'Email inválido' },
            })}
          />
          {errors.email && <Erro>{errors.email.message}</Erro>}
        </Campo>

        <Campo>
          <Label htmlFor="senha">Senha:</Label>
          <Input
            type="password"
            id="password"
            {...register('password', {
              required: 'Senha é obrigatória',
              minLength: { value: 6, message: 'A senha deve ter pelo menos 6 caracteres' },
            })}
          />
          {errors.password && <Erro>{errors.password.message}</Erro>}
        </Campo>

        <Botao type="submit">Entrar</Botao>
        <TextoLink>
          Não possui uma conta? <LinkStyled onClick={handleLoginClick}>Cadastre-se</LinkStyled>
        </TextoLink>
      </Formulario>
    </Container>
  );
};

export default Login;