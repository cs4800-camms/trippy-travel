import { Fragment } from "react";
import EditTripForm from "../components/trips/edit/EditTripForm";
import { useNavigate } from "react-router-dom";

export default function EditTripPage() {
    const navigate = useNavigate();

    function editTrip(tripInfo) {
        navigate("/");
    }
    
    return (
        <Fragment>
            <h1> Edit Your Trip</h1>
            <EditTripForm onEditTrip={editTrip}> </EditTripForm>
        </Fragment>
    );
}