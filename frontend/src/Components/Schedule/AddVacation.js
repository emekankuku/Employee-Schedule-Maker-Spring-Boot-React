import React, { useState, useEffect } from 'react';
import Modal from 'react-bootstrap/Modal';
import ScheduleService from '../../Service/ScheduleService';

const AddVacation = ({ user, group, show, onClose }) => {

    const [submission, setSubmission] = useState({
        email: user.email,
        group: group,
        startDate: "",
        endDate: "",
        note: ""
    })
    const [errors, setErrors] = useState({})
    const [loading, setLoading] = useState(false);

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

    const handleDateChange = (e) => {
        const value = e.target.value;
        const name = e.target.name;
        var date = new Date(value+'T00:00');
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
        console.log(submission);
    }

    if (loading)
        return <p>Loading...</p>;

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
        <div>
            <Modal show={show} onHide={onClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Submit Days Off</Modal.Title>
                </Modal.Header>
                <form onSubmit={handleSubmit}>
                    <Modal.Body>
                        {errorList}
                        <label class="col-sm-3 col-form-label">Start Date: </label>
                        <div class="col-sm-7">
                            <input
                                type="date"
                                name="start"
                                onChange={handleDateChange}
                            />
                        </div>

                        <label class="col-sm-3 col-form-label">End Date: </label>
                        <div class="col-sm-7">

                            <input
                                type="date"
                                name="end"
                                onChange={handleDateChange}
                            />
                        </div>
                        <label class="col-sm-3 col-form-label">Note (Required)</label>
                        <div class="col-sm-7">
                            <textarea type="name" value={submission.note} class="form-control" id="exampleFormControlTextarea1" rows="3" onChange={handleNoteChange}></textarea>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
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

export default AddVacation;