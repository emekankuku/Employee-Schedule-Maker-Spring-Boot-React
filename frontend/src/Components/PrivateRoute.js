import Navbar from "./Navbar";
import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import UserService from '../Service/UserService';
import { useParams } from "react-router-dom";

export default function PrivateRoute({ child, role, hideNav }) {
    const [currUser, setCurrUser] = useState("");
    const navigate = useNavigate();
    let { groupName } = useParams(); 

    useEffect(() => {
        const token = localStorage.getItem("jwt");
        UserService.getCurrUser(token)
            .then(response => {
                console.log(response);
                console.log("This is group: " + groupName)
                if (response.data == '')
                    navigate('/login');
                setCurrUser(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    if (role != "" && currUser.role != role) {
        return (
            <div>
                Unauthorized
            </div>
        )
    }

    return (
        <div>
            {hideNav == "false" ?
                <Navbar role={currUser.role} /> :
                <div></div>
            }
            {React.cloneElement(child, { user: currUser,  group: groupName})}
        </div>

    );
};
