import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import GroupService from '../../Service/GroupService';

export const ShowDaysOff = ({ user, group }) => {
    const [daysOff, setDaysOff] = useState([]);
    const [loading, setLoading] = useState(false);

    const sorter = {
        "name": 0,
        "startDate": 1,
        "endDate": 2
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

if(Object.keys(daysOff).length == 0)
    return <p>No one is taking a break!</p>

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

return (
    <div className="container">
        {/* {keys} */}
        <h1 className="text-center"> Days Off:</h1>

        <table className="table table-striped">
            <thead>
                <tr>
                    {Object.entries(daysOff[0] || {}).map(
                        ([key, value]) =>
                            <th>{key}</th>
                    )
                    }
                </tr>

            </thead>
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
)

}