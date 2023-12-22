import React, { useState, useEffect } from 'react';
import CheckInService from '../../Service/CheckInService';
import Modal from 'react-bootstrap/Modal';

const DoCheckIn = ({ user, group, checking }) => {

    const today = new Date();
    const date = today.getMonth() + "/" + today.getDate() + "/" + today.getFullYear();
    const time = today.getHours() + ":" + today.getMinutes();

    const submission = { group: group, email: user.email };

    const [show, setShow] = useState(false);
    const [errors, setErrors] = useState({});

    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    const handleSubmit = e => {

        CheckInService.checkIn(submission)
            .then(response => {
                alert("submitted");
                window.location.reload(false);
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
            <button type="button" class="btn btn-primary" onClick={handleShow}>
                {checking}
            </button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Check In</Modal.Title>
                </Modal.Header>
                <form onSubmit={handleSubmit}>
                    <Modal.Body>
                    {errorList}
                        {checking} &nbsp;
                        at {date} {time}
                    </Modal.Body>
                    <Modal.Footer>
                        <button type="button" class="btn btn-secondary" onClick={handleClose}>
                            Close
                        </button>
                        <button type="submit" class="btn btn-primary">
                            Confirm {checking}
                        </button>
                    </Modal.Footer>
                </form>
            </Modal>
        </div>
    );
};

export default DoCheckIn;