import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import CheckInService from '../../Service/CheckInService';
import { useNavigate } from "react-router-dom";
import CheckInModal from './DoCheckIn';

// rsc
export const CheckIn = ({ user, group }) => {

    const submission = { group: group, email: user.email };
    const [loading, setLoading] = useState(false);
    const [recentCheckIn, setRecentCheckIn] = useState("");

    useEffect(() => {
        if (!user)
            return;
        setLoading(true);
        if ({ user })
            setLoading(false);
        CheckInService.getRecentCheckIn(submission)
            .then(response => {
                console.log(response.data);
                setRecentCheckIn(response.data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);

    const notCheckedIn = (
        <p>You have not checked in today.</p>
    )

    const checking = (
        recentCheckIn.inOrOut == "Check In" ? <label>Check Out</label> : <label>Check In</label>
    )

    return (
        <div>
            {/* <!-- SubNavbar -->*/}
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
            <section>
                <div class="container text-center">
                    <div>

                        {/* <!-- Recent CheckIn/Out -->*/}
                        <div class="checkIn">
                            {recentCheckIn.inOrOut != null ?
                                <p>Last {recentCheckIn.inOrOut} : {recentCheckIn.date}, {recentCheckIn.time}</p>
                                : <p>{notCheckedIn}</p>
                            }
                        </div>

                        {/* <!-- Modal --> */}
                        <CheckInModal user={user} group={group} checking={checking} />
                    </div>
                </div>
            </section>
        </div>
    );
};
