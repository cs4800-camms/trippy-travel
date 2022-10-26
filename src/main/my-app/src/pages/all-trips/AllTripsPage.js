import React, { Fragment, useState, useEffect, useContext } from 'react';
import TripList from '../../components/trips/list/TripList';
import "bootstrap/dist/css/bootstrap.min.css";
import './AllTripsPage.css'
import { useNavigate } from 'react-router-dom';
import GlobalContext from '../../context/global'
import axios from 'axios';

export default function AllTripsPage() {

    const navigate = useNavigate();

    const navigateToAdd = () => {
        navigate("/add-trip");
    }

    const [isLoading, setIsLoading] = useState(true);
    const { tripList, setTripList } = useContext(GlobalContext);

    useEffect(() => {
        setIsLoading(true);
        axios.get(`/trips/active`)
            .then((res) => {
                console.log(res);
                return res.data;
            })
            .then((trips) => {
                setIsLoading(false);
                console.log(trips);
                setTripList(trips);
            });
    }, [setTripList]);

    if(isLoading) {
        return (
            <section>
                <p>Loading...</p>
            </section>
        );
    }

    return (
        <Fragment>
            <>
                <div className="text-center">
                    <h1>Trippy Travel</h1>
                </div>
                <br></br>
                <br></br>
                <TripList tripList={tripList} setTripList={setTripList}></TripList>
                <br></br><br></br><br></br>
                <div className="text-center">
                    <button onClick={navigateToAdd} type="button" className="btn btn-primary btn-lg">Plan new trip</button>
                </div>
            </>
        </Fragment>
    );

}