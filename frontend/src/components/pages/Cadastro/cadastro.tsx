import React from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import axios from 'axios';
import { 
  Container, 
  Titulo, 
  Formulario, 
  Campo, 
  Label, 
  Input, 
  Erro, 
  Botao,
  TextoLink,
  LinkStyled,
} from './cadastro.styles';

interface IFormInput {
  name: string;
  cpf: string;
  phone: string;
  email: string;
  password: string;
}

const Cadastro: React.FC = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormInput>();

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      const response = await axios.post('http://localhost:8080/client/register', data);
      console.log('Cadastro realizado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao realizar cadastro:', error);
    }
  };

  const handleLoginClick = () => {
    window.location.href = '/login'; 
  };

  return (
    <Container>
      <Titulo>Cadastro</Titulo>
      <Formulario onSubmit={handleSubmit(onSubmit)}>
        <Campo>
          <Label htmlFor="name">Nome Completo:</Label>
          <Input
            type="text"
            id="name"
            {...register('name', { required: 'Nome completo é obrigatório' })}
          />
          {errors.name && <Erro>{errors.name.message}</Erro>}
        </Campo>
        <Campo>
          <Label htmlFor="cpf">CPF:</Label>
          <Input
            type="text"
            id="cpf"
            {...register('cpf', {
              required: 'CPF é obrigatório',
              pattern: { value: /^\d{3}\.\d{3}\.\d{3}-\d{2}$/, message: 'CPF inválido' },
            })}
          />
          {errors.cpf && <Erro>{errors.cpf.message}</Erro>}
        </Campo>

        <Campo>
          <Label htmlFor="phone">Telefone:</Label>
          <Input
            type="tel"
            id="phone"
            {...register('phone', {
              required: 'Telefone é obrigatório',
            })}
          />
          {errors.phone && <Erro>{errors.phone.message}</Erro>}
        </Campo>

        <Campo>
          <Label htmlFor="email">Email:</Label>
          <Input
            type="email"
            id="email"
            {...register('email', {
              required: 'Email é obrigatório',
              pattern: { value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i, message: 'Email inválido' },
            })}
          />
          {errors.email && <Erro>{errors.email.message}</Erro>}
        </Campo>

        <Campo>
          <Label htmlFor="password">Senha:</Label>
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

        <Botao type="submit">Cadastrar-se</Botao>
        <TextoLink>
          Já possui uma conta? <LinkStyled onClick={handleLoginClick}>Faça login</LinkStyled>
        </TextoLink>
      </Formulario>
    </Container>
  );
};

export default Cadastro;