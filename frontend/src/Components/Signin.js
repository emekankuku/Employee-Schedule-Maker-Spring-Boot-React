import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

export default function Login({ props }) {

    const [user, setUser] = useState({
        email: '',
        password: ''
    });

    const [errors, setErrors] = useState({})

    const navigate = useNavigate();

    const handleSubmit = e => {
        e.preventDefault();

        axios.post('http://localhost:8080/registration/signin', user,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                }
            })
            .then(res => {
                // localStorage.setItem("jwt", res.data.token);
                console.log(res);
                // console.log(localStorage.getItem("jwt"));
                navigate('/home')
            })
            .catch((error) => {
                if (error.response.data.fieldErrors) {
                    var fieldErrors = error.response.data.fieldErrors;
                    var newErrors = {
                        email: '',
                        password: '',
                        unauthorized: ''
                    };
                    fieldErrors.forEach(fieldError => {
                        newErrors[fieldError.field] = fieldError.message;
                    })
                    setErrors(newErrors);
                    console.log(newErrors);
                }
            });
    }

    const handleChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setUser({
            ...user,
            [name]: value
        });
    }

    const errorList = Object.entries(errors).map(([error, message]) => {
        if (message)
            return (
                <div>
                    <label className="errors">{message}</label>
                    <br></br>
                </div>
            )
    })

    return (
        <div className="container margin-bottom">
            <div class="text-center">
                <h1 class="form-title">Sign In</h1>

                <div>{errorList}</div>

                <form onSubmit={handleSubmit}>
                <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Email:</label>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" name="email" id="email" placeholder="Enter email" value={user.email} onChange={handleChange} />
                        </div>
                    </div>

                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label">Password:</label>
                        <div class="col-sm-7">
                            <input type="password" className="form-control" name="password" id="password" value={user.password} onChange={handleChange} />
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Login</button><br></br>
                        <Link to="/signup">Don't have an account? Register here.</Link>
                    </div>

                </form>
            </div>
        </div>
    );
}

