import React  from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import Login from '../components/user/Login';
import NavBarLanding from '../components/ui/NavBarLanding';

export default function LogIn() {
    return(
        <body>
            <NavBarLanding></NavBarLanding>
            <br></br><br></br>
            <h1>Sign In</h1>
            <Login></Login>
        </body>
    )
}
