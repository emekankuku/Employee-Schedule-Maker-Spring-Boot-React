import { Button } from 'bootstrap';
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from '../Navbar';
import GroupService from '../../Service/GroupService';
import axios from 'axios';
import jwt_decode from "jwt-decode";

export const AddUser = ({ user, group }) => {

    const [submission, setSubmission] = useState({
        name: group,
        managerEmail: user.email,
        employeeEmail: ''
    });
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        if ({ user })
            setLoading(false);
    }, []);

    const handleSubmit = e => {
        e.preventDefault();
        GroupService.addUser(submission)
            .then((response) => {
                alert(submission.employeeEmail + " has been successfully added to " + group);
                navigate(-1)
            })
            .catch((error) => {
                if (error.response.data.fieldErrors) {
                    var fieldErrors = error.response.data.fieldErrors;
                    var newErrors = {
                        group: '',
                        email: ''
                    };
                    fieldErrors.forEach(fieldError => {
                        newErrors[fieldError.field] = fieldError.message;
                    })
                    setErrors(newErrors);
                    console.log(newErrors);
                }
            });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const handleChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setSubmission({
            ...submission,
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

                <div>{errorList}</div>

                <form onSubmit={handleSubmit}>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label"> Employee Email:</label>
                        <div class="col-sm-7">
                            <input type="name" class="form-control" name="employeeEmail" id="employeeEmail" placeholder="Enter Employee Email" value={submission.employeeEmail} onChange={handleChange} />
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Add User</button><br></br>
                    </div>

                </form>
            </div>
            <div>{group}</div>
        </div>
    );
}