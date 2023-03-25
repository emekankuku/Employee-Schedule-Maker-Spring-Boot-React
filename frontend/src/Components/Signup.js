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
                    <label style={{ color: 'red' }}>{message}</label>
                    <br></br>
                </div>
            )
    })

    return (
        <div className="container">
            <h1>Sign Up</h1>

            <div>{errorList}</div>

            <form onSubmit={handleSubmit}>
                <div class="form-group">
                    <label>
                        First Name:
                        <input type="text" name="firstName" id="firstName" value={user.firstName} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        Last Name:
                        <input type="text" name="lastName" id="lastName" value={user.lastName} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        Email:
                        <input type="text" name="email" id="email" value={user.email} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        Password:
                        <input type="password" name="password" id="password" value={user.password} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Register</button><br></br>
                    <Link to="/login">Login here.</Link>
                </div>

            </form>
        </div>
    );
}