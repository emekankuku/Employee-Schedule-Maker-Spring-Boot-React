import axios from 'axios'
import React from 'react';

const BASE_URL = 'http://localhost:8080/schedule/';

class ScheduleService extends React.Component {

    createSchedule(dto) {
        return axios.post(BASE_URL + 'createSchedule', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    getSchedule(dto) {
        return axios.post(BASE_URL + 'getSchedule', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    createDayOff(dto) {
        return axios.post(BASE_URL + 'createDaysOff', dto,
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

export default new ScheduleService();