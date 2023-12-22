import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import ReportService from '../Service/ReportService';

const GroupReport = ({ user, group }) => {

    const [emails, setEmails] = useState([]);
    const [checkinHours, setCheckinHours] = useState({});
    const [actualHours, setActualHours] = useState({});

    useEffect(() => {
        if (!user)
            return;
        ReportService.getReport(group)
            .then(response => {
                console.log(response.data);
                setEmails(response.data.emails);
                setCheckinHours(response.data.checkinHours);
                setActualHours(response.data.actualHours);

            }

            ).catch(error =>
                console.log(error));
    }, [user, group]);

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
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                {/* {checkinHours["ravymiyazaki@gmail.com"]} */}
                {/* {Object.entries(checkinHours || {}).map(
                    ([key, value]) =>
                        <div>
                            <th>{value}</th>
                            <br></br>
                        </div>
                )
                } */}
                <table class="table">
                    <thead class="table-dark">
                        <tr>
                            <th>Email</th>
                            <th>Recorded Hours</th>
                            <th>Scheduled Hours</th>
                        </tr>

                    </thead>
                </table>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <tbody>
                            {emails.map(
                                email => 
                                <tr>
                                    <th>{email}</th>
                                    <th>{checkinHours[email]}</th>
                                    <th>{actualHours[email]}</th>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default GroupReport;