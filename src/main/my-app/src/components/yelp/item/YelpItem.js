import "bootstrap/dist/css/bootstrap.min.css";
import classes from "./YelpItem.module.css";

export default function YelpItem({ business }) {
    return (
        <div className={`card ${classes.card}`}>
            <img src={business.image_url} className={`card-img-top ${classes.cardImg}`} alt={business.name} />
            <div class="card-body">
                <a href={business.url} target="_blank">
                    <h3 class="card-title">{business.name}</h3>
                </a>
                <p class="card-text"><i class="bi bi-geo-alt"></i> {business.location.display_address[0]} <br/> {business.location.display_address[1]}</p>
                <p class="card-text">Rating: {business.rating} <i class="bi bi-star-fill"></i> </p>
            </div>
        </div>
    )
}