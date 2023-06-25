import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import ScheduleService from '../../Service/ScheduleService';

export const AddSchedule = ({ user, group }) => {

    const [submission, setSubmission] = useState({
        email: user.email,
        group: group,
        sunday: '',
        monday: '',
        tuesday: '',
        wednesday: '',
        thursday: '',
        friday: '',
        saturday: ''

    });
    const [schedule, setSchedule] = useState({
        sunday: {
            start: '00:00',
            end: '00:00'
        },
        monday: {
            start: '00:00',
            end: '00:00'
        },
        tuesday: {
            start: '00:00',
            end: '00:00'
        },
        wednesday: {
            start: '00:00',
            end: '00:00'
        },
        thursday: {
            start: '00:00',
            end: '00:00'
        },
        friday: {
            start: '00:00',
            end: '00:00'
        },
        saturday: {
            start: '00:00',
            end: '00:00'
        }
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
        var submissionInput = submission;
        Object.keys(schedule || {}).map(
            day => {
                var timeRange = schedule[day].start + "-" + schedule[day].end;
                submission[day] = timeRange;
            }
        )

        setSubmission(submissionInput)
        // setSubmission({ ...submission, schedule: submissionSchedule });

        ScheduleService.createSchedule(submission)
            .then((response) => {
                alert(submission.email + " has been successfully created its schedule");
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

    const startTimeChange = e => {
        const value = e.target.value;
        const name = e.target.name;
        const day = schedule[name];
        day.start = value;
        setSchedule({
            ...schedule,
            [name]: day
        });
    }

    const endTimeChange = e => {
        const value = e.target.value;
        const name = e.target.name;
        const day = schedule[name];
        day.end = value;
        setSchedule({
            ...schedule,
            [name]: day
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }


    const errorList = Object.entries(errors).map(([error, message]) => {
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
                        {Object.keys(schedule || {}).map(
                            day =>
                                <div class="form-group row margin-bottom">
                                    <label class="col-sm-3 col-form-label">{day}:</label>
                                    <div class="col-sm-3">
                                        <input type="time" value={schedule[day].start} name={day} onChange={startTimeChange}></input>
                                    </div>
                                    -
                                    <div class="col-sm-3">
                                        <input type="time" value={schedule[day].end} name={day} onChange={endTimeChange}></input>
                                    </div>
                                </div>
                        )}
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Add Schedule</button><br></br>
                    </div>

                </form>
            </div>
            <div>
            </div>
        </div>
    );
}