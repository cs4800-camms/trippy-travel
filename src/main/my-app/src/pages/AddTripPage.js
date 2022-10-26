import { useNavigate } from "react-router-dom";
import AddTripForm from "../components/trips/add/AddTripForm";

export default function AddTripPage() {
    const navigate = useNavigate();

    function addTrip(tripInfo) {
        navigate("/");
    }

    return (
        <section>
            <h1> Plan a New Trip</h1>
            <AddTripForm onAddTrip={addTrip}></AddTripForm>
        </section>
    );
}