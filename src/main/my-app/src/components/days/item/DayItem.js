import moment from 'moment';
import uniqid from 'uniqid';
import { useState, useEffect } from 'react';
import ActivityList from '../../activity/list/ActivityList';
import axios from 'axios';
import classes from "./DayItem.module.css"
import authHeader from '../../../services/auth-header';

export default function DayItem({ day, tripId }) {
    const [activityList, setActivityList] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const id = uniqid();

    //gets activities for the trip
    useEffect(() => {
        setIsLoading(true);
        axios.get(`/activities/${day._id}`, { headers: authHeader() })
            .then((res) => {
                return res?.data;
            })
            .then((activities) => {
                setIsLoading(false);
                setActivityList(activities);
            });
    }, [setActivityList, day._id]);

    if(isLoading) {
        return (
            <section>
                <p>Loading...</p>
            </section>
        );
    }

    return (
        <div key={day._id} >
            <br></br>
            <div className={`accordion-item ${classes.item}`}>
                <h2 className={`accordion-header ${classes.textHeader}`} id="panelsStayClose-headingThree">
                    <button className={`accordion-button collapsed ${classes.dayHeader}`} type="button" data-bs-toggle="collapse" data-bs-target={String("#" + id)} aria-expanded="false" aria-controls={id}>
                        {moment(day.date).format("dddd, MMMM Do")}</button>
                </h2>

                <div id={id} class="accordion-collapse collapse" aria-labelledby="headingOne">
                    <div class="accordion-body">
                        <ActivityList dayId={day._id} activityList={activityList} setActivityList={setActivityList}  tripId={tripId}></ActivityList>
                    </div>
                </div>
            </div>
        </div>
    );
}