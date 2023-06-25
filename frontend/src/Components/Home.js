import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export const Home = ({ user }) => {

    const [currUser, setCurrUser] = useState(user);
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        if ({ user })
            setLoading(false);
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }


    return (
        <div className="container">
            <div className='text-center'>
                <h1>You have officially signed in, {user.role}: {user.firstName}.</h1>
            </div>
        </div>
    );
}