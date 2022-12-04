import React  from 'react';
import NavBar from "../components/ui/NavBar";
import Landing from '../components/landing/Landing';
import NavBarLanding from '../components/ui/NavBarLanding';

export default function LandingPage(){
    return(
        <body>
            <NavBarLanding></NavBarLanding>
            <br/>
            <br/>
            <Landing></Landing>
        </body>
    )
}