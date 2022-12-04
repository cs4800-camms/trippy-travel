import "bootstrap/dist/css/bootstrap.min.css";
import classes from "./NavBar.module.css";
import Logo from "./NavLogo.png"
import { useNavigate } from 'react-router-dom';
import AuthService from '../../services/auth.service';

export default function NavBarLanding() {
    const navigate = useNavigate();

    const navigateToHome = () => {
        navigate("/");
    }

    return (
        <nav className={`navbar navbar-expand-lg bg-light ${classes.nav}`}>
            <div class="container">
                <img  style={{width: "200px"}} className={`navbar-brand ${classes.logo}`} onClick={navigateToHome} src={Logo} alt="..."/>
                <a class={`btn btn-primary btn-m ${classes.btn}`}
                    href="https://docs.google.com/presentation/d/1YVrDXM3kp0hXVXqc3lHiWB9QspIHFQrm96jmxeIgpfE/edit?usp=sharing" target="_blank" type="Learn more"><i class="bi bi-info-circle"></i> Learn More</a>
            </div>
        </nav>
    );
}