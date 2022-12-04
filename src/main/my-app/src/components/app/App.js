import React, { useState } from "react";
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import AllTripsPage from '../../pages/all-trips/AllTripsPage';
import AddTripPage from '../../pages/AddTripPage';
import ItineraryPage from '../../pages/itinerary/ItineraryPage';
import EditTripPage from '../../pages/EditTripPage';
import GlobalContext from "../../context/global"
import LogInPage from "../../pages/LogInPage"
import LandingPage from "../../pages/LandingPage"
import SignUpPage from "../../pages/SignUpPage";
import PrivateRoute from "../../pages/private-route/PrivateRoute";

export default function App() {
    const [tripList, setTripList] = useState([]);
    const [dayList, setDayList] = useState([]);
    const [activityList, setActivityList] = useState([]);
    const [businesses, setBusinesses] = useState([]);

    return (
        <GlobalContext.Provider value={{
            tripList,
            setTripList,
            dayList,
            setDayList,
            activityList,
            setActivityList,
            businesses,
            setBusinesses
        }}>
            <div className="App">
                <BrowserRouter>
                    <Routes>
                        <Route path='/' element={<LandingPage />}></Route>
                        <Route path='/all-trips' element={<PrivateRoute><AllTripsPage /></PrivateRoute>}></Route>
                        <Route path='/add-trip' element={<PrivateRoute><AddTripPage /></PrivateRoute>}></Route>
                        <Route path='/itinerary/:tripId' element={ <PrivateRoute><ItineraryPage /></PrivateRoute>}></Route>
                        <Route path='/edit' element={<PrivateRoute><EditTripPage /></PrivateRoute>}></Route>
                        <Route path='/login' element={<LogInPage />}></Route>
                        <Route path='/signup' element={<SignUpPage />}></Route>
                        <Route path='/landing' element={<LandingPage />}></Route>
                    </Routes>
                </BrowserRouter>
            </div>
        </GlobalContext.Provider>
    );
}