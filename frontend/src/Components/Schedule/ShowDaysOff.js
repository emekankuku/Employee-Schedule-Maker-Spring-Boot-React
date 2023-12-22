import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';
import VacationModule from './AddVacation';

export const ShowDaysOff = ({ user, group }) => {

    const [show, setShow] = useState(false);

    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);

    const [daysOff, setDaysOff] = useState([]); //Days Off List
    const [loading, setLoading] = useState(false);

    //Days Off Variable Sorter
    const sorter = {
        "name": 0,
        "start": 1,
        "end": 2
    }

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        GroupService.showDaysOff(group)
            .then(response => {
                var data = response.data;

                for (var i = 0; i < data.length; i++) {
                    var ordered = sortedObject(data[i]);
                    data[i] = ordered;
                    console.log(ordered);
                }

                setDaysOff(data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);



    if (loading)
        return <p>Loading...</p>;

    //Sorts properties of each Days Off Object
    function sortedObject(data) {
        if (typeof data != "object") return null;
        return Object.keys(data).sort(daySorter).reduce(
            (obj, key) => {
                obj[key] = data[key];
                return obj;
            },
            {}
        );
    }

    //Sorts object properties according to list
    function daySorter(a, b) {
        return sorter[a] - sorter[b];
    }

    const nullList = (
        <div>
            No one is taking a break.
        </div>
    )

    const scheduleTable = (
        <div>
            <h1 class="text-center"> Days Off:</h1>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        {Object.entries(daysOff[0] || {}).map(
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
                            daysOff.map(
                                dayOff =>
                                    <tr key={dayOff.id}>
                                        {
                                            Object.entries(dayOff || {}).map(
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
                                        Add Days Off
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                {Object.keys(daysOff).length == 0 ? nullList : scheduleTable}
                <VacationModule user={user} group={group} show={show} onClose={handleClose} />

            </div>
        </div>
    )

}