import axios from 'axios'
import React, { useState, useEffect } from 'react';

const BASE_URL = 'http://localhost:8080/registration/';

class UserService extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: ""
        }
    }

    componentDidMount() {
        console.log("This is reached");
        const token = localStorage.getItem("jwt");
        axios.post(BASE_URL + 'currUser', null,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
            })
            .then(response => {
                this.setState({ firstName: response.data.firstName });
                console.log("This is name " + this.state);
            });
    }

    signin(user) {
        return axios.post(BASE_URL + 'signin', user,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                }
            })
    }

    signup(user) {
        return axios.post(BASE_URL + 'signup', user,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    getCurrUser(token) {
        return axios.post(BASE_URL + 'currUser', null,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                }
            })
    }



    async isLoggedIn() {
        const token = localStorage.getItem("jwt")
        var user = await this.getCurrUser(token).then(response => { return response.data });
        console.log(user);
        if (user) return true;
        return false;

    }

}

export default new UserService();