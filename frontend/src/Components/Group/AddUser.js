import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';
import Modal from 'react-bootstrap/Modal';

const AddUser = ({ user, group, show, onClose }) => {

    const [submission, setSubmission] = useState({
        groupName: group,
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
        <div>
            <Modal show={show} onHide={onClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add User to {submission.groupName}</Modal.Title>
                </Modal.Header>
                <form onSubmit={handleSubmit}>
                    <Modal.Body>
                        {errorList}
                        <div class="form-group row margin-bottom">
                            <label class="col-sm-3 col-form-label"> Employee Email:</label>
                            <div class="col-sm-7">
                                <input type="name" class="form-control" name="employeeEmail" id="employeeEmail" placeholder="Enter Employee Email" value={submission.employeeEmail} onChange={handleChange} />
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <button type="button" class="btn btn-secondary" onClick={onClose}>
                            Close
                        </button>
                        <button type="submit" class="btn btn-primary" onClick={handleSubmit}>
                            Add
                        </button>
                    </Modal.Footer>
                </form>
            </Modal>
        </div>
    );
};

export default AddUser;