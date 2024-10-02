import ProductGrid from "../../modules/items/grid";
import Navbar from "../../modules/navBar/navBar";
import { useLocation } from 'react-router-dom';


const Main: React.FC = () => {
  const location = useLocation();
  const user = location.state?.user;
  
  return (
    <>
    <Navbar user={user}/>
    <ProductGrid user={user}/>
    </>
  );
};

export default Main;