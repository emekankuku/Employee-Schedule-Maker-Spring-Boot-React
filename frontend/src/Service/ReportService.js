import axios from 'axios'
import React from 'react';

const BASE_URL = 'http://localhost:8080/report/';

class ReportService extends React.Component {
    
    getReport(dto) {
        return axios.post(BASE_URL + 'getReport', dto,
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

export default new ReportService();