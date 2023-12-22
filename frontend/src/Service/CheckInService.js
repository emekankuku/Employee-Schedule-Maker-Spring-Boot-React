import axios from 'axios'
import React from 'react';

const BASE_URL = 'http://localhost:8080/checkIn/';

class CheckInService extends React.Component {
    
    getRecentCheckIn(dto) {
        return axios.post(BASE_URL + 'recentCheckIn', dto,
            {
                withCredentials: 'include', //Enables sending cookies from api to browser
                headers: {
                    'Content-Type': 'application/json',
                    Accept: "application/json"
                }
            }
        )
    }

    checkIn(dto) {
        return axios.post(BASE_URL + 'checkIn', dto,
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

export default new CheckInService();