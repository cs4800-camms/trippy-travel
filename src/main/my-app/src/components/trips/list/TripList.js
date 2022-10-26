import TripItem from "../item/TripItem";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';

export default function TripList({ tripList, setTripList }) {
    function handleRemove(id) {
        const newTripList = tripList.filter((trip) => trip._id !== id);
        setTripList(newTripList)

        axios.delete(`/trips/${id}/delete`)
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
                <h2> Your trips </h2>
            </div>
            <div className="container">
                        <div className="row">
                            {tripList.map(trip => (
                                <div className="col-sm-4">
                                    <TripItem
                                        key={trip._id}
                                        _id={trip._id}
            //                            image={trip.image}
                                        name={trip.name}
                                        destination={trip.destination}
                                        startDate={trip.startDate}
                                        endDate={trip.endDate}
                                        handleRemove={handleRemove}
                                    />
                                </div>
                            ))}
                        </div>
                        </div>
        </>
    );
}