import { Button } from 'bootstrap';
import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

export default function Navbar() {

    const navigate = useNavigate();
    
    const logout = e => {
        e.preventDefault();
        axios.get('http://localhost:8080/registration/logout',
        {withCredentials: 'include', //Enables sending cookies from api to browser
    }
        )
        .then(res => {
            console.log(res);
            navigate('/signin');
        })
        .catch(error => {
            console.log(error);
        })

    }
    return (
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <li><Link class="navbar-brand" to="/">Home</Link></li>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <Link class="nav-link" to="/addTask">Add Task</Link>
                    </li>
                    <li class="nav-item">
                        <Link class="nav-link" to="/register">Register</Link>
                    </li>
                    <li class="nav-item">
                        <button class="button" onClick={logout}>Logout</button>
                    </li>
                </ul>
            </div>
        </nav>
    );
}