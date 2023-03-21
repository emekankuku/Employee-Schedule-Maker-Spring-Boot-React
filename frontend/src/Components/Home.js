import { Button } from 'bootstrap';
import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import axios from 'axios';

export default function Home() {

    const [currUser, setCurrUser] = useState("");
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true);
        fetch("registration/currUser")
            .then(response => response.json())
            .then(currUser => {
                setCurrUser(currUser);
                setLoading(false);
                console.log(currUser);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }


    return (
        <section>
            <div>Hello {currUser.firstName}</div>
            </section>
    );
}