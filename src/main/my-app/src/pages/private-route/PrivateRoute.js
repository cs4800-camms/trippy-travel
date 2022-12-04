import AuthService from '../../services/auth.service';
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
    const user = AuthService.getCurrentUser();
    return user ? children : <Navigate to="/login" />;
};

export default PrivateRoute;