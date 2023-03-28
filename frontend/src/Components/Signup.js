import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

export default function Signup({ props }) {

    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    });

    const [errors, setErrors] = useState({})

    const navigate = useNavigate();

    const handleSubmit = e => {
        e.preventDefault();

        axios.post('http://localhost:8080/registration/signup', user,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
            .then((response) => {
                alert("Registration Successful");
                navigate("/login");
            })
            .catch((error) => {
                if (error.response.data.fieldErrors) {
                    var fieldErrors = error.response.data.fieldErrors;
                    var newErrors = {
                        firstName: '',
                        lastName: '',
                        email: '',
                        password: ''
                    };
                    fieldErrors.forEach(fieldError => {
                        newErrors[fieldError.field] = fieldError.message;
                    })
                    setErrors(newErrors);
                    console.log(errors);
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
    };

    const errorList = Object.entries(errors).map(([error, message]) => {
        if (message)
            return (
                <div>
                    <label class="errors">{message}</label>
                    <br></br>
                </div>
            )
    })

    return (
        <div className="container margin-bottom">
            <div class="text-center">
                <h1 class="form-title">Sign Up</h1>

                <div>{errorList}</div>

                <form onSubmit={handleSubmit}>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> First Name:</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Enter first name" value={user.firstName} onChange={handleChange} />
                        </div>
                    </div>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Last Name:</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Enter last name" value={user.lastName} onChange={handleChange} />
                        </div>
                    </div>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Email:</label>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" name="email" id="email" placeholder="Enter email" value={user.email} onChange={handleChange} />
                        </div>
                    </div>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Password:</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" name="password" id="firstName" placeholder="Enter password" value={user.password} onChange={handleChange} />
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Register</button><br></br>
                        <Link to="/login">Already have an account? Login here.</Link>
                    </div>

                </form>
            </div>
        </div>
    );
}