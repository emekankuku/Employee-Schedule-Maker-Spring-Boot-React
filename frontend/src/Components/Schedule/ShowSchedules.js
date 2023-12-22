import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';
import ScheduleModule from './AddSchedule';

export const ShowSchedules = ({ user, group }) => {

    const [show, setShow] = useState(false);

    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    const [schedules, setSchedules] = useState([]);
    const [loading, setLoading] = useState(false);

    const sorter = {
        "name": 0,
        "sun": 1,
        "mon": 2,
        "tue": 3,
        "wed": 4,
        "thur": 5,
        "fri": 6,
        "sat": 7
    }

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        GroupService.showSchedules(group)
            .then(response => {
                var data = response.data;

                for (var i = 0; i < data.length; i++) {
                    var ordered = sortedObject(data[i]);
                    data[i] = ordered;
                }

                setSchedules(data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));



    }, [user]);

    if (loading)
        return <p>Loading...</p>;

    function sortedObject(data) {
        if (typeof data != "object") return null;
        return Object.keys(data).sort(daySorter).reduce(
            (obj, key) => {
                obj[key] = key != "name" ? formatDate(data[key]) : data[key];
                return obj;
            },
            {}
        );
    }

    function daySorter(a, b) {
        return sorter[a] - sorter[b];
    }

    function formatDate(dateRange) {
        if (typeof dateRange != "string" || dateRange == "") return null;
        if (dateRange == "Day Off") return "Day Off";
        var array = dateRange.split('-');
        for (var i = 0; i < array.length; i++) {
            if (array[i].charAt(0) == '0')
                array[i] = array[i].substring(1);
            var time = array[i].split(":");
            var hr = Number(time[0]) % 12;
            array[i] = hr.toString() + ":" + time[1] + (Number(time[0]) < 12 ? "AM" : "PM");

        }

        return array[0] + " - " + array[1];

    }

    const nullList = (
        <div>
            No Schedules Made.
        </div>
    )

    const scheduleTable = (
        <div>
            <h1 class="text-center"> This Week's Schedule:</h1>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        {Object.entries(schedules[0] || {}).map(
                            ([key, value]) =>
                                <th>{key}</th>
                        )
                        }
                    </tr>

                </thead>
            </table>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <tbody>
                        {
                            schedules.map(
                                schedule =>
                                    <tr key={schedule.id}>
                                        {
                                            Object.entries(schedule || {}).map(
                                                ([key, value]) =>
                                                    <th>{value}</th>
                                            )
                                        }

                                    </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        </div>
    )


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
                                    <button class="btn nav-link" onClick={handleShow}>
                                        Add Schedule
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                {Object.keys(schedules).length == 0 ? nullList : scheduleTable}
                <ScheduleModule user={user} group={group} show={show} onClose={handleClose} />
            </div>
        </div>
    )
}