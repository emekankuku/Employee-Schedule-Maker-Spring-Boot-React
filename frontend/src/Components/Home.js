import { Button } from 'bootstrap';
import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import axios from 'axios';

export default function Home() {

    const [currUser, setCurrUser] = useState("");
    const [loading, setLoading] = useState(false);
    const [unauthorized, setUnauthorized] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        fetch("registration/currUser")
            .then(response => response.json())
            .then(currUser => {
                setCurrUser(currUser);
                setLoading(false);
            }).catch(error => {
                console.log(error);
                if (error)
                    setUnauthorized(true);
            })
    }, []);

    if (unauthorized)
        return <div className="container">
            <div className='text-center'>
                <h1>Unauthorized</h1>
            </div>
        </div>

    if (loading) {
        return <p>Loading...</p>;
    }


    return (
        <div className="container">
            <div className='text-center'>
                <h1>You have officially signed in, {currUser.firstName}.</h1>
            </div>
        </div>
    );
}