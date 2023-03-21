import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

export default function Login({ props }) {

    const [user, setUser] = useState({
        email: '',
        password: ''
    });
    const [emailInvalid, setEmailInvalid] = useState(false);
    const [inputEmpty, setInputEmpty] = useState(false);
    const navigate = useNavigate();


    const handleChange = e => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setUser({
            ...user,
            [name]: value
        });
    }

    const handleSubmit = e => {
        e.preventDefault();
        if (emptyInput())
            setInputEmpty(true);

        else {
            // fetch('http://localhost:8080/registration/signin',
            //     {
            //         method: 'POST',
            //         headers: {
            //             'Content-Type': 'application/json',
            //         },
            //         body: JSON.stringify(user)
            //     }
            // )
            axios.post('http://localhost:8080/registration/signin',
                user,
                {
                    withCredentials: 'include', //Enables sending cookies from api to browser
                    headers: {
                        'Content-Type': 'application/json',
                        // 'Access-Control-Allow-Credentials': 'true',
                        // "Access-Control-Allow-Origin": "http://localhost:8080"
                    }
                })
                .then(res => {
                    // if (res.status != 401 && res.data.token) {
                    // localStorage.setItem("user", JSON.stringify(res.data.token));
                    console.log(res);
                    navigate('/home')
                    // } else {
                    // setEmailInvalid(true);
                    // console.log("User does not exist");
                    // }
                })
                .catch(error => {
                    console.log(error);
                })
            // .then(navigate('/home'));
        }
    }

    function emptyInput() {
        if (user.email === '' || user.password === '') {
            setInputEmpty(true);
            return true;
        }
        setInputEmpty(false);
        return false;
    }


    return (
        <div className="container">
            <h1>Sign In</h1>

            {inputEmpty ? <label style={{ color: 'red' }}>Please complete form</label> : null}
            {inputEmpty ? <br></br> : null}

            {emailInvalid ? <label style={{ color: 'red' }}>Invalid Email</label> : null}
            {emailInvalid ? <br></br> : null}

            <form onSubmit={handleSubmit}>

                <div class="form-group">
                    <label>
                        Email:
                        <input type="text" name="email" id="email" value={user.email} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        Password:
                        <input type="password" name="password" id="password" value={user.password} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Login</button><br></br>
                    <Link to="/register">Register here.</Link>
                </div>

            </form>
        </div>
    );
}

