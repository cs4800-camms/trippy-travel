import TripItem from "../item/TripItem";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import authHeader from '../../../services/auth-header';

export default function TripList({ tripList, setTripList }) {

    function handleRemove(id) {
        const newTripList = tripList.filter((trip) => trip._id !== id);
        setTripList(newTripList)

        axios.delete(`/trips/${id}/delete`, { headers: authHeader() })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

        axios.delete(`/days/${id}/delete-by-trip`, { headers: authHeader() })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

        axios.delete(`/activities/${id}/delete-by-trip`, { headers: authHeader() })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <>
            <div className="text-center">
                <h2 style={{ fontSize: "40px" }}> Your Trips </h2>
            </div>
            <div className="container">
                {tripList.length === 0 ?
                    <h4 style={{ color: "#462b17", marginTop: "50px"}}>Add a trip to get started!</h4> :
                    <div className="row">
                        {tripList.map(trip => (
                            <div className="col-xs-12 col-sm-6 col-lg-4">
                                <TripItem
                                    key={trip._id}
                                    _id={trip._id}
                                    name={trip.name}
                                    destination={trip.destination}
                                    startDate={trip.startDate}
                                    endDate={trip.endDate}
                                    handleRemove={handleRemove}
                                />
                            </div>
                        ))}
                    </div>
                }
            </div>
        </>
    );
}