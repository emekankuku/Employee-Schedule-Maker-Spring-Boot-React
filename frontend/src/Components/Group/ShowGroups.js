import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';
import GroupModule from './CreateGroup';

export const ShowGroups = ({ user }) => {

    const [show, setShow] = useState(false);

    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    const [groups, setGroups] = useState([]);
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        GroupService.getGroups(user)
            .then(response => {
                setGroups(response.data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);

    if (loading)
        return <p>Loading...</p>;

    if (groups.length == 0)
        return <p>No groups can be show</p>

    return (
        <div>
            <div class="homepage-container">
                <div class="text-center">
                    <h1> {user.firstName}'s Groups:</h1>
                    {
                        groups.map(
                            group =>
                                <p class="list-item"> <Link class="navbar-brand" to={'/groups/' + group.name} state={group.name}>{group.name}</Link></p>
                        )
                    }

                    <button type="button" class="btn btn-primary" onClick={handleShow}>
                        Create Group
                    </button>
                </div>
                <GroupModule user={user} show={show} onClose={handleClose} />
            </div>
        </div>
    )
}