import "bootstrap/dist/css/bootstrap.min.css";
import classes from "../trips/add/AddTripForm.module.css";
import axios from 'axios';
import { useState } from "react";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const navigate = useNavigate();

    const [loginInfo, setLoginInfo] = useState({
        username: "",
        password: ""
    });
    const [passwordShown, setPasswordShown] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const togglePassword = () => {
        setPasswordShown(!passwordShown);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios.post(`/auth/signin`, loginInfo)
            .then(function (response) {
                if (response.data.accessToken) {
                  localStorage.setItem("user", JSON.stringify(response.data));
                }
                console.log(response.data);
                navigate("/all-trips");
            })
            .catch(function (error) {
                setErrorMessage("Unauthorized: Bad Credentials");
            });
    }

    return (
        <>
            <div class="container mt-4 mb-4 d-flex justify-content-center">
                <div className={`card px-5 py-5 ${classes.card}`} >
                    <div class="card-body">
                        <h5 className={`card-title mb-3 ${classes.title}`}>We are TrippyTravel</h5>
                        <Form className={classes.form} onSubmit={handleSubmit}>
                            <p>Please sign in to your account</p>
                            <br></br>
                            <div className={`form-floating mb-4 ${classes.control}`}>
                                <input type="text" id="floatingInput" class="form-control" placeholder="jane_doe" onChange={(e) => setLoginInfo({ ...loginInfo, username: e.target.value })} value={loginInfo.username} required />
                                <label class="form-label" for="floatingInput">Username</label>
                            </div>

                            <div className={`form-floating mb-4 ${classes.control} ${classes.inputWithButton}`}>
                                <input type={passwordShown ? "text" : "password"} id="floatingInput" class="form-control"
                                    placeholder="Enter password" onChange={(e) => setLoginInfo({ ...loginInfo, password: e.target.value })} value={loginInfo.password} required />
                                { passwordShown ? <i className="bi bi-eye-slash" onClick={togglePassword}></i> :<i className="bi bi-eye" onClick={togglePassword}></i>}
                                <label class="form-label" for="floatingInput">Password</label>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-primary btn-lg">Sign In</button>
                            </div>
                            <br></br>
                            {errorMessage ? (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        {errorMessage}
                                    </div>
                                </div>
                            ) : <div></div>}
                            <p> Don't have an account? <a class href="/signup">Create one here </a></p>
                        </Form>
                    </div>
                </div>
            </div>
        </>
    );
}
