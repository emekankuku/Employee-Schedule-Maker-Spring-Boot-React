import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';

export const CreatGroup = ({ user }) => {

    const [group, setGroup] = useState({
        name: '',
        email: user.email
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
        console.log(group.email);
        GroupService.createGroup(group)
            .then((response) => {
                alert("Creation Successful");
                navigate("/")
            })
            .catch((error) => {
                if (error.response.data.fieldErrors) {
                    var fieldErrors = error.response.data.fieldErrors;
                    var newErrors = {
                        group: ''
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
        setGroup({
            ...group,
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
                        <label class="col-sm-3 col-form-label"> Name:</label>
                        <div class="col-sm-7">
                            <input type="name" class="form-control" name="name" id="email" placeholder="Enter Group Name" value={group.name} onChange={handleChange} />
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Create Group</button><br></br>
                    </div>

                </form>
            </div>
        </div>
    );
}