import React, { useState, useEffect } from 'react'
import UserService from './UserService';

function UserComponent() {

    const [users, setUsers] = useState([])
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true);
        fetch("api/users")
            .then(response => response.json())
            .then(users => {
                setUsers(users);
                setLoading(false);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    // const getUsers = () => {

    //     UserService.getUsers().then((response) => {
    //         setUsers(response.data)
    //         console.log(response.data);
    //     });
    // };

    return (
        <div className="container">

            <h1 className="text-center"> Users List</h1>

            <table className="table table-striped">
                <thead>
                    <tr>
                        <th> Employee Id</th>
                        <th> Employee First Name</th>
                        <th> Employee Last</th>
                        <th> Employee Email</th>
                    </tr>

                </thead>
                <tbody>
                    {
                        users.map(
                            user =>
                                <tr key={user.id}>
                                    <td> {user.id}</td>
                                    <td> {user.firstName}</td>
                                    <td> {user.lastName}</td>
                                    <td> {user.email}</td>

                                </tr>

                        )
                    }

                </tbody>


            </table>

        </div>
    )
}

export default UserComponent