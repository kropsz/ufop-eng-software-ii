import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Cadastro from './components/pages/Cadastro/cadastro.tsx';
import Login from './components/pages/Login/login.tsx';
import Main from './components/pages/main/main.tsx';
import PointsHistoryPage from './components/pages/points-history/points.tsx';

function App() {
  return (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/login" element={<Login />} />
      <Route path="/home" element={<Main />} />
      <Route path="/points-history/:id" element={<PointsHistoryPage />} />
    </Routes>
  </BrowserRouter>
  );
}

export default App;