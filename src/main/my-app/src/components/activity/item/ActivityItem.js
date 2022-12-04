import { useState } from "react";
import axios from "axios";
import authHeader from '../../../services/auth-header';
import classes from "./ActivityItem.module.css";

export default function ActivityItem({ activity, remove, setActivityList}) {

    const [completed, setCompleted] = useState(activity.checked);

    const handleChange = async (event) => {
        let updatedActivity;
        let activityInfo;

        if(completed) {
            activityInfo = {day_id: activity.day_id,
                            trip_id: activity.trip_id,
                            name: activity.name,
                            checked: false}

            await axios.put(`/activities/${activity._id}/update`, activityInfo, { headers: authHeader() })
                .then(function (response) {
                    updatedActivity = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                })
        } else {
            activityInfo = {day_id: activity.day_id,
                            trip_id: activity.trip_id,
                            name: activity.name,
                            checked: true}

            await axios.put(`/activities/${activity._id}/update`, activityInfo, { headers: authHeader() })
                .then(function (response) {
                    updatedActivity = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        setCompleted(!completed);
        setActivityList(oldActivities => oldActivities.map(oldActivity =>
            oldActivity._id === updatedActivity._id ? updatedActivity : oldActivity));
    }

    return (
        <div key={activity._id}>
            <li
                class={`list-group-item d-flex ${classes.activity}`}>
                <div class="form-check d-flex align-items-center">
                    <div class="row">
                        <a href="#!" data-mdb-toggle="tooltip" title="Remove item">
                            <i class="fas fa-times text-primary"></i>
                        </a>
                        <div class={`form-check active ${classes.todo}`}>
                            <input class={`form-check-input ${classes.checkbox}`} name="checked" type="checkbox" checked={completed} onChange={handleChange} />
                            <label class={`form-check-label ${classes.label}`} for="flexCheckDefault">
                                <p class={`${classes.name}`}>{activity.name}</p>
                            </label>
                        </div>
                    </div>
                </div>
                <i onClick={() => remove(activity._id)} className={`bi bi-x ${classes.icon}`}></i>
            </li>
        </div>
    );
}