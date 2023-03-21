import { Button } from 'bootstrap';
import React, { useState, useRef, useEffect } from 'react'
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import DatePicker from 'react-datepicker';

export default function AddTask() {

    const [task, setTask] = useState({
        name: '',
        date: '',
        time: ''
    });
    const dateInputRef = useRef(null);
    const navigate = useNavigate();

    const handleChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setTask({
            ...task,
            [name]: value
        });
    };

    return (
        <div className="container">
            <form>
                <div class="form-group">
                    <label>
                        Task:
                        <input type="text" name="name" onChange={handleChange} ref={dateInputRef} />
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        Date Deadline:
                        <input type="date" name="date" onChange={handleChange} ref={dateInputRef} />
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        Time:
                        <input type="time" name="time" onChange={handleChange} ref={dateInputRef} />
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Register</button><br></br>
                </div>
            </form>
            <p>{task.name}</p>
            <p>{task.date}</p>
            <p>{task.time}</p>
        </div>

    );
}