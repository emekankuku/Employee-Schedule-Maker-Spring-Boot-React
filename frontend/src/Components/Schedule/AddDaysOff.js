import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import ScheduleService from '../../Service/ScheduleService';

export const AddDaysOff = ({ user, group }) => {

    const [submission, setSubmission] = useState({
        email: user.email,
        group: group,
        startDate: "",
        endDate: "",
        note: ""
    })
    const [errors, setErrors] = useState({})
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        if ({ user })
            setLoading(false);
        setSubmission({ ...submission, email: user.email });
    }, [user, group]);

    const handleSubmit = e => {
        e.preventDefault();

        ScheduleService.createDayOff(submission)
            .then((response) => {
                alert(submission.email + " has successfully created their day-off schedule");
                navigate("/groups/" + group)
            })
            .catch((error) => {
                if (error.response.data.fieldErrors) {
                    var fieldErrors = error.response.data.fieldErrors;
                    var newErrors = {};
                    fieldErrors.forEach(fieldError => {
                        newErrors[fieldError.field] = fieldError.message
                    })
                    setErrors(newErrors);
                }
            });
    }

    const handleDateChange = (e) => {
        const value = e.target.value;
        const name = e.target.name;
        var date = new Date(value);
        var dateStr = ('0' + (date.getMonth() + 1)).slice(-2) + '/'
            + ('0' + date.getDate()).slice(-2) + '/'
            + date.getFullYear();

        setSubmission({
            ...submission,
            [name]: dateStr
        });
        console.log(submission);
    };

    const handleNoteChange = (e) => {
        setSubmission({
            ...submission,
            ["note"]: e.target.value
        });

        console.log()
        console.log(submission);
    }

    if (loading) {
        return <p>Loading...</p>;
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
                {errorList}
                <form onSubmit={handleSubmit}>
                    <div class="form-group row margin-bottom">
                        <label class="col-sm-3 col-form-label">Start Date: </label>
                        <div class="col-sm-7">
                            <input
                                type="date"
                                name="startDate"
                                onChange={handleDateChange}
                            />
                        </div>
                        <label class="col-sm-3 col-form-label">End Date: </label>
                        <div class="col-sm-7">

                            <input
                                type="date"
                                name="endDate"
                                onChange={handleDateChange}
                            />
                        </div>
                        <label class="col-sm-3 col-form-label">Note (Required)</label>
                        <div class="col-sm-7">

                            <input
                                type="name"
                                value={submission.note}
                                onChange={handleNoteChange}
                            />
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Set Days Off</button><br></br>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
