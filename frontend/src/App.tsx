import { Route, BrowserRouter, Routes } from 'react-router-dom';
import Navbar from './components/modules/NavBar/navBar.tsx';
import './App.css';
import Cadastro from './components/pages/Cadastro/cadastro.tsx';
import Login from './components/pages/Login/login.tsx';

function App() {
  return (
   <BrowserRouter>
    <Navbar />
    <Routes>
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/login" element={<Login />} />
    </Routes>
    </BrowserRouter>
  );
}

export default App;