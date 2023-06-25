import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';

export const ShowGroups = ({ user }) => {

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
    
    if(groups.length == 0)
        return <p>No groups can be show</p>

    return (
        <div className="container">
            <h1 className="text-center"> Group List:</h1>

            <table className="table table-striped">
                <thead>
                    <tr>
                        <th> Group Name</th>
                    </tr>

                </thead>
                <tbody>
                    {
                        groups.map(
                            group =>
                                <tr key={group.name}>
                                    <td> <Link to={'/groups/' + group.name} state={group.name}>{group.name}</Link></td>
                                    <button value={group.name}>Button</button>
                                </tr>
                        )
                    }

                </tbody>
            </table>
        </div>
    )
}