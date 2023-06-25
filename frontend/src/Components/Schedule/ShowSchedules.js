import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';

export const ShowSchedules = ({ user, group }) => {

    const [schedules, setSchedules] = useState([]);
    const [loading, setLoading] = useState(false);

    const sorter = {
        "name": 0,
        "sunday": 1,
        "monday": 2,
        "tuesday": 3,
        "wednesday": 4,
        "thursday": 5,
        "friday": 6,
        "saturday": 7
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
                    console.log(ordered);
                }

                setSchedules(data);
                setLoading(false);
            })
            .catch(error =>
                console.log(error));
    }, [user]);

    if (loading)
        return <p>Loading...</p>;

    if(Object.keys(schedules).length == 0)
        return <p>No schedules posted</p>

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

    function daySorter(a, b){
        return sorter[a] - sorter[b];
    }

    const keys = Object.entries(schedules[0] || {}).map(([key, value]) => {
        return (
            <div>
                {key} : {value}
                <br></br>
            </div>
        )
    })


    return (
        <div className="container">
            {/* {keys} */}
            <h1 className="text-center"> Schedules:</h1>

            <table className="table table-striped">
                <thead>
                    <tr>
                        {Object.entries(schedules[0] || {}).map(
                            ([key, value]) =>
                                <th>{key}</th>
                        )
                        }
                    </tr>

                </thead>
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
    )
}