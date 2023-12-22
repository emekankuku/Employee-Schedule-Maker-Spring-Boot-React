import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import ScheduleService from '../../Service/ScheduleService';
import Modal from 'react-bootstrap/Modal';

const AddSchedule = ({ user, group, show, onClose }) => {

    const retrievalSubmission = { group: group, email: user.email };

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
    const temp = {
        sunday: {
            start: '',
            end: ''
        },
        monday: {
            start: '',
            end: ''
        },
        tuesday: {
            start: '',
            end: ''
        },
        wednesday: {
            start: '',
            end: ''
        },
        thursday: {
            start: '',
            end: ''
        },
        friday: {
            start: '',
            end: ''
        },
        saturday: {
            start: '',
            end: ''
        }
    };
    const [schedule, setSchedule] = useState(
        {
            sunday:  temp["sunday"],
            monday: temp["monday"],
            tuesday: temp["tuesday"],
            wednesday: temp["wednesday"],
            thursday: temp["thursday"],
            friday: temp["friday"],
            saturday: temp["saturday"]
        }
    )
    const [errors, setErrors] = useState({})
    const [loading, setLoading] = useState(false);
    const [timeRangeWarning, setTimeRangeWarning] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        if ({ user })
            setLoading(false);
        setSubmission({ ...submission, email: user.email });
        ScheduleService.getSchedule(retrievalSubmission)
            .then((response) => {
                var arr = response.data;
                Object.keys(arr || {}).map(
                    day => {
                        var range = temp[day];
                        if (arr[day] == "") {
                            range.start = "";
                            range.end = "";
                            // setTemp({
                            //     ...temp,
                            //     [day]: range
                            // });
                            temp[day] = range;
                        } else {
                            var splitted = arr[day].split('-');
                            range.start = splitted[0];
                            range.end = splitted[1];
                            // setTemp({
                            //     ...temp,
                            //     [day]: range
                            // });
                            temp[day] = range;
                        }
                    }
                )
                console.log(temp);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [user, group]);

    const handleSubmit = e => {
        e.preventDefault();
        if (timeRangeWarning)
            return;
        Object.keys(schedule || {}).map(
            day => {
                var timeRange = "";
                if (schedule[day].start != "" && schedule[day].end != "")
                    timeRange = schedule[day].start + "-" + schedule[day].end;
                submission[day] = timeRange;
            }
        )

        ScheduleService.createSchedule(submission)
            .then((response) => {
                alert(submission.email + " has been successfully created its schedule");
                window.location.reload(false);
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
        setTimeRangeWarning(false);
        const value = e.target.value;
        const name = e.target.name;
        const day = schedule[name];
        day.start = value;
        console.log(temp);
        if (day.start > day.end)
            setTimeRangeWarning(true);
        setSchedule({
            ...schedule,
            [name]: day
        });
    }

    const endTimeChange = e => {
        setTimeRangeWarning(false);
        const value = e.target.value;
        const name = e.target.name;
        const day = schedule[name];
        day.end = value;
        console.log(temp);
        if (day.start > day.end)
            setTimeRangeWarning(true);
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

    const rangeWarning = (
        <div class="alert alert-danger" role="alert">
            Incorrect Time Range
        </div>
    )

    function resetSchedule() {
        console.log(temp);
        setSchedule( {
            sunday:  temp["sunday"],
            monday: temp["monday"],
            tuesday: temp["tuesday"],
            wednesday: temp["wednesday"],
            thursday: temp["thursday"],
            friday: temp["friday"],
            saturday: temp["saturday"]
        });
    }

    return (
        <div>
            <Modal show={show} onHide={onClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Submit Schedule</Modal.Title>
                </Modal.Header>
                <form onSubmit={handleSubmit}>
                    <Modal.Body>
                        {timeRangeWarning ? rangeWarning : <div></div>}
                        {errorList}
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
                    </Modal.Body>
                    <Modal.Footer>
                        <button type="button" class="btn btn-secondary" onClick={resetSchedule}>
                            Reset Schedule
                        </button>
                        <button type="button" class="btn btn-secondary" onClick={onClose}>
                            Close
                        </button>
                        <button type="submit" class="btn btn-primary" onClick={handleSubmit}>
                            Submit
                        </button>
                    </Modal.Footer>
                </form>
            </Modal>
        </div>
    );
};

export default AddSchedule;