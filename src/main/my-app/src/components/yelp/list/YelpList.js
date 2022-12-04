import YelpItem from "../item/YelpItem";
import { useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import GlobalContext from '../../../context/global';
import classes from "./YelpList.module.css";

export default function YelpList() {
    const { businesses } = useContext(GlobalContext);

    return (
        <div className={`${classes.parent}`}>
            <div className={`${classes.child}`}>
                {businesses.map((business) => (
                    <YelpItem key={business.id} business={business}/>
                ))}
            </div>
        </div>
    );
}