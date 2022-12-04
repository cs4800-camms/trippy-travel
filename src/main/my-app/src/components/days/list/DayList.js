import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import moment from 'moment';
import DayItem from "../item/DayItem";
import classes from "./DayList.module.css";
import authHeader from '../../../services/auth-header';

export default function DayList({ dayList, setDayList, tripId, trip }) {

    //to calculate total days
    var startDate = moment(new Date(trip.startDate)).add(1, 'days')
    var endDate = moment(new Date(trip.endDate)).add(1, 'days')
    const totalDays = endDate.diff(startDate, 'days') + 1;

    //adds a day to frontend
    const addDay = (newDay) => {
        setDayList((oldDays) => [...oldDays, newDay]);
    }

    //adds a day to backend
    const handleDayAdd = async () => {
        const dayInfo = dayList.length === 0 ? {
            trip_id: tripId,
            date: new Date(moment(new Date(trip.startDate)).add(1, 'days'))
        } : {
            trip_id: tripId,
            date: new Date(moment(new Date(dayList[dayList.length - 1].date)).add(1, 'days'))
        }
        console.log(dayInfo);

        await axios.post(`/days/create`, dayInfo, { headers: authHeader() })
        .then(function (response) {
            console.log(response);
            addDay(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    }

    //checks whether to render the button or not
    let button;
    if (dayList.length !== totalDays) {
        button = <button onClick={handleDayAdd} type="button" className="btn btn-primary" style={{marginBottom: "30px"}}><i class="bi bi-plus"></i> Add a day</button>;
    } else {
        button = <button hidden onClick={handleDayAdd} type="button" className="btn btn-primary" style={{marginBottom: "30px"}}><i class="bi bi-plus"></i> Add a day</button>;
    }

    return (
        <div>
            {dayList.length === 0 ?
                <h5 style={{ color: "#462b17", marginTop: "40px"}}>Add a day to your trip!</h5> :
                <div className={`accordion ${classes.dayHeader}`} id="accordionPanelsStayOpenExample">
                    {dayList.map((day, index) => (
                        <DayItem day={day} tripId={tripId} />
                    ))}
                </div>
            }
            <br></br>
            {button}
        </div>
    );
}