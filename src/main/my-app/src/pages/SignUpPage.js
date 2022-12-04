import React  from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import Signup from '../components/user/Signup';
import NavBarLanding from '../components/ui/NavBarLanding';

export default function SignUpPage(){
    return(
        <body>
            <NavBarLanding></NavBarLanding>
            <br></br><br></br>
            <h1>Join TrippyTravel</h1>
           <Signup></Signup>
        </body>
    )
}
