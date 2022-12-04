import { useState, useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import classes from "./SearchBar.module.css";
import axios from "axios";
import GlobalContext from '../../context/global';
import { Form } from "react-bootstrap";

export default function SearchBar() {
    const { setBusinesses } = useContext(GlobalContext);
    const [searchInfo, setSearchInfo] = useState({
        term: "",
        location: "",
    });
    const [errorMessage, setErrorMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios.post(`/yelp/get-results`, searchInfo)
            .then((response) => {
                console.log(response);
                setBusinesses(response.data.businesses);
                setErrorMessage("");
            })
            .catch(function (error) {
                setErrorMessage("No Results Found");
            });
    };

    return (
        <body className={classes.body}>
            <br></br>
            <Form onSubmit={handleSubmit}>
            <div class="row">
                <div class="col">
                    <input type="text" id="floatingInput" class="form-control" placeholder="Search categories" onChange={(e) => setSearchInfo({ ...searchInfo, term: e.target.value })} value={searchInfo.term} required></input>
                </div>
                <div class="col">
                    <input type="text" id="floatingInput" class="form-control" placeholder="Where?" onChange={(e) => setSearchInfo({ ...searchInfo, location: e.target.value })} value={searchInfo.location} required></input>
                </div>
            </div>
            <br></br>
            <div>
                <button className="btn btn-primary btn-m"><i class="bi bi-search"></i> Find activities</button>
            </div>
            </Form>
            {errorMessage ? (
                <div>
                    <br/>
                    <div className="alert alert-warning" role="alert">
                        {errorMessage}
                    </div>
                </div>
            ) : <div></div>}
        </body>
    )
}