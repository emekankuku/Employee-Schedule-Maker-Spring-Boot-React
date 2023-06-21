import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";
import UserService from '../../Service/UserService';
import Form from 'react-bootstrap/Form'
import axios from 'axios';

export default function Signup({ props }) {

    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        role: ''
    });

    const [errors, setErrors] = useState({});

    const roles = ["Employee", "Manager"];

    const navigate = useNavigate();

    const handleSubmit = e => {
        e.preventDefault();
        UserService.signup(user)
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
                        password: '',
                        role: ''
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
        if(roles.includes(value)){
            setUser({
                ...user,
                ["role"]: value
            });
        }else{
        setUser({
            ...user,
            [name]: value
        });
    }
    };

    const yourChangeHandler = e => {
        alert(e.target.id + ": " + e.target.value)
        setUser({
            ...user,
            ["role"]: e.target.value
        });
    }

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
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Please choose role:</label>
                        <div class="col-sm-7">
                        <select onChange={handleChange}>
                        <option selected>Please choose a role:</option>
                        <option id="role" value="Employee">Employee</option>
                        <option id="role" value="Manager">Manager</option>
                    </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Register</button><br></br>
                        <Link to="/login">Already have an account? Login here.</Link>
                    </div>

                </form>
            </div>
            <label>Your role is {user.role}</label>
        </div>
    );
}