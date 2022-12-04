import "bootstrap/dist/css/bootstrap.min.css";
import classes from "../trips/add/AddTripForm.module.css";
import axios from 'axios';
import { useState } from "react";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


export default function Signup() {
    const navigate = useNavigate();

    const [userInfo, setUserInfo] = useState({
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        password: ""
    });
    const [passwordShown, setPasswordShown] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const togglePassword = () => {
        setPasswordShown(!passwordShown);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios.post(`/auth/signup`, userInfo)
            .then(function (response) {
                console.log(response.data.message);
                navigate("/login");
            })
            .catch(function (error) {
                console.log(error.response.data);
                setErrorMessage(error.response.data.message);
            });
    }

    return (
        <>
            <div class="container mt-4 mb-4 d-flex justify-content-center">
                <div className={`card px-4 py-4 ${classes.card}`}  >
                    <div class="card-body">
                        <h5 className={`card-title mb-3 ${classes.title}`}>Time to start planning!</h5>
                        <Form className={classes.form} onSubmit={handleSubmit}>
                            <p>Please create your account</p>
                            <br></br>
                            <div class="row">
                                <div class="col">
                                    <div className={`form-floating mb-4 ${classes.control}`}>
                                        <input type="text" id="floatingInput" class="form-control" placeholder="Jane" onChange={(e) => setUserInfo({ ...userInfo, firstName: e.target.value })} value={userInfo.firstName} required />
                                        <label class="form-label" for="floatingInput">First name</label>
                                    </div>
                                </div>
                                <div class="col">
                                    <div className={`form-floating mb-4 ${classes.control}`}>
                                        <input type="text" id="floatingInput" class="form-control" placeholder="Doe" onChange={(e) => setUserInfo({ ...userInfo, lastName: e.target.value })} value={userInfo.lastName} required />
                                        <label class="form-label" for="floatingInput">Last name</label>
                                    </div>
                                </div>
                            </div>
                            <div className={`form-floating mb-4 ${classes.control}`}>
                                <input type="email" id="floatingInput" class="form-control" placeholder="jdoe@gmail.com" onChange={(e) => setUserInfo({ ...userInfo, email: e.target.value })} value={userInfo.email} required />
                                <label class="form-label" for="floatingInput">Email</label>
                            </div>
                            <div className={`form-floating mb-4 ${classes.control}`}>
                                <input type="text" id="floatingInput" class="form-control" placeholder="jane_doe" onChange={(e) => setUserInfo({ ...userInfo, username: e.target.value })} value={userInfo.username} required />
                                <label class="form-label" for="floatingInput">Username</label>
                            </div>
                            <div className={`form-floating mb-4 ${classes.control} ${classes.inputWithButton}`}>
                                <input type={passwordShown ? "text" : "password"} id="floatingInput" class="form-control"
                                    placeholder="Enter password" onChange={(e) => setUserInfo({ ...userInfo, password: e.target.value })} value={userInfo.password} required />
                                {passwordShown ? <i className="bi bi-eye-slash" onClick={togglePassword}></i> : <i className="bi bi-eye" onClick={togglePassword}></i>}
                                <label class="form-label" for="floatingInput">Password</label>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-primary btn-lg">Join</button>
                            </div>
                            <br></br>
                            {errorMessage ? (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        {errorMessage}
                                    </div>
                                </div>
                            ) : <div></div>}
                            <p> Already have an account? <a class href="/login">Sign in here </a></p>
                        </Form>
                    </div>
                </div>
            </div>
        </>
    );
}
