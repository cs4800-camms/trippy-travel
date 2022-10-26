import "bootstrap/dist/css/bootstrap.min.css";
import classes from '../add/AddTripForm.module.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import Form from 'react-bootstrap/Form';
import { useState, useContext } from "react";
import GlobalContext from '../../../context/global';
import axios from 'axios';
import { useLocation } from "react-router-dom";

export default function EditTripForm({ onEditTrip }) {
    const location = useLocation();

    const [tripInfo, setTripInfo] = useState({
        userId: 1,
        name: location.state.name,
        destination: location.state.destination,
        startDate: location.state.startDate.substring(0,10),
        endDate: location.state.endDate.substring(0,10),
    });

    const { setTripList } = useContext(GlobalContext);

    const updateTrip = (updatedTrip) => {
       setTripList(oldTrips => oldTrips.map(oldTrip => oldTrip._id === updatedTrip._id ? updatedTrip : oldTrip));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios.post(`/trips/${location.state._id}/update`, tripInfo)
            .then(function (response) {
                console.log(response);
                updateTrip(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });

        onEditTrip(tripInfo);
    };

    return (
        <>
            <Form class="row gy-2 gx-3 align-items-center " className={classes.form} onSubmit={handleSubmit}>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInput" placeholder="e.g My trip #1" onChange={(e) => setTripInfo({ ...tripInfo, name: e.target.value })} value={tripInfo.name} required />
                    <label className={classes.label} htmlFor="floatingInput">Trip name</label>
                </div>

                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInput" placeholder="e.g Paris, France" onChange={(e) => setTripInfo({ ...tripInfo, destination: e.target.value })} value={tripInfo.destination} required />
                    <label className={classes.label} htmlFor="floatingInput">Where to?</label>
                </div>

                <div class="col-auto">
                    <div class="form-floating mb-3" id="datepicker">
                        <input type="date" class="form-control form-control-lg" onChange={(e) => setTripInfo({ ...tripInfo, startDate: e.target.value })} value={tripInfo.startDate} required />
                        <label htmlFor="datepicker" className={classes.label}>Start Date</label>
                    </div>
                </div>

                <div class="col-auto">
                    <div class="form-floating mb-3" id="datepicker">
                        <input type="date" min={tripInfo.startDate} class="form-control form-control-lg" onChange={(e) => setTripInfo({ ...tripInfo, endDate: e.target.value })} value={tripInfo.endDate} required />
                        <label htmlFor="datepicker" className={classes.label}>End Date</label>
                    </div>
                </div>
                <br></br><br></br><br></br><br></br>
                <div class="col-auto">
                    <button class="btn btn-primary btn-lg">Start Planning</button>
                </div>
            </Form>
        </>
    );
}