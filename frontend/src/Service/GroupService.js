import axios from 'axios'
import React, { useState, useEffect } from 'react';

const BASE_URL = 'http://localhost:8080/grouping/';

class GroupService extends React.Component {
    
    createGroup(dto) {
        return axios.post(BASE_URL + 'createGroup', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    getGroups(dto) {
        return axios.post(BASE_URL + 'getGroups', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    getUsers(dto) {
        return axios.post(BASE_URL + 'getUsers', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    addUser(dto) {
        return axios.post(BASE_URL + 'addUser', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    showSchedules(group) {
        return axios.post(BASE_URL + 'getSchedules', group,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }
    
}

export default new GroupService();