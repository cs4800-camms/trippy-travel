import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import classes from "./TripItem.module.css";
import 'jquery/dist/jquery.min.js';
import 'bootstrap/dist/js/bootstrap.min.js';
import paris from './paris.png'
import { useNavigate } from "react-router-dom";

export default function TripItem(props) {
    const navigate = useNavigate();

    const toEditForm = () => {
        navigate('/edit', { state: { _id: props._id, name: props.name, destination: props.destination, startDate: props.startDate, endDate: props.endDate } })
    }

    return (
        <div className={`text-center ${classes.align}`}>
            <img src={paris} className={`img-fluid ${classes.image}`} alt={props.name} />
            <Link className={classes.link} to="/itinerary">{props.name}</Link>
            <a data-toggle="collapse" data-parent="#accordionEx" href="#collapseOne1" aria-expanded="true"></a>
            <div className="btn-group" role="group">
                <i id="btnGroupDrop1" type="icon" className={`btn btn-sm dropdown-toggle ${classes.button}`} data-bs-toggle="dropdown" aria-expanded="false"></i>
                <ul className="dropdown-menu" aria-labelledby="btnGroupDrop1">
                  <li><a onClick={() => { toEditForm() }} className="dropdown-item">Edit trip info</a></li>
                  <li><a onClick={() => props.handleRemove(props._id)} className="dropdown-item" href="#">Delete trip</a></li>
                </ul>
            </div>
        </div>
    );
}