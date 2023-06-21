import { Button } from 'bootstrap';
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useParams } from "react-router-dom";
import axios from 'axios';
import jwt_decode from "jwt-decode";
import GroupService from '../../Service/GroupService';

export const Group = ({ user, group }) => {

    const [users, setUsers] = useState([]);
    const [groupp, setGroup] = useState({
        name: group 
    });
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        if (!group)
            return;
        setLoading(true);
        GroupService.getUsers(groupp)
            .then(response => {
                setUsers(response.data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);

    return (
        <div className="container">
            <div className='text-center'>
                <h1>This is group, {group}</h1>
                <table className="table table-striped">
                <thead>
                    <tr>
                        <th> User</th>
                    </tr>

                </thead>
                <tbody>
                    {
                        users.map(
                            user =>
                                <tr key={user.firstName}>
                                    <td> {user.firstName}</td>
                                    <button value={user.firstName}>Button</button>
                                </tr>
                        )
                    }

                </tbody>
            </table>
            <Link class="navbar-brand" to={'/addUser/' + group}>Add User</Link><br></br>
            <Link class="navbar-brand" to={'/showSchedules/' + group}>Show Schedules</Link><br></br>
            <Link class="navbar-brand" to='/addSchedule/'>Add Schedule</Link>
            </div>
        </div>
    );
}