import Navbar from "./Navbar";
import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import UserService from '../Service/UserService';

export default function GroupRoute({ child, role, hideNav, groupName }) {
    const [currUser, setCurrUser] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("jwt");
        UserService.getCurrUser(token)
            .then(response => {
                console.log(response);
                if (response.data == '')
                    navigate('/login');
                setCurrUser(response.data);
            })
            .catch(error => {
                console.log(error);
            });


    }, []);

    return (
        <div>
            {hideNav == "false" ?
                <Navbar role={currUser.role} /> :
                <div></div>
            }
            {React.cloneElement(child, { user: currUser, group: groupName })}
        </div>

    );
};
