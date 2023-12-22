import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';
import UserModule from './AddUser';

export const Group = ({ user, group }) => {

    const [show, setShow] = useState(false);

    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        if (!group)
            return;
        setLoading(true);
        GroupService.getUsers(group)
            .then(response => {
                setUsers(response.data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);

    return (
        <div>
            <div class="sticky-top">
                <nav class="navbar navbar-expand-md navbar-light bg-light">
                    <div class="container-fluid">
                        <Link class="navbar-brand abs" to={'/groups/' + group}>{group}</Link>
                        <div class="navbar-collapse collapse" id="collapseNavbar">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <Link class="nav-link" to={'/showSchedules/' + group}>Show Schedules</Link>
                                </li>
                                <li class="nav-item">
                                    <Link class="nav-link" to={'/showDaysOff/' + group}>Show Days Off</Link>
                                </li>
                            </ul>
                            <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                    <Link class="nav-link" to={'/groupReport/' + group}>Report</Link>
                                </li>
                                <li class="nav-item">
                                    <Link class="nav-link" to={'/checkIn/' + group}>Check Attendance</Link>
                                </li>
                                <li class="nav-item">
                                    {user.role == "Manager" ?
                                        <button class="btn nav-link" onClick={handleShow}>
                                            Add User
                                        </button>
                                        : <li></li>
                                    }
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <div className='text-center'>
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th>Members:</th>
                            </tr>

                        </thead>
                        <tbody>
                            {
                                users.map(
                                    user =>
                                        <tr key={user.firstName}>
                                            <td> {user.firstName} {user.lastName}</td>
                                        </tr>
                                )
                            }

                        </tbody>
                    </table>
                </div>
            </div>
            <UserModule user={user} group={group} show={show} onClose={handleClose} />
        </div>
    );
}