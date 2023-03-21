import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";

export default function Registration({ props }) {

    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    });

    const [passwordInvalid, setPasswordInvalid] = useState(false);
    const [emailInvalid, setEmailInvalid] = useState(false);
    const [passwordConfirm, confirmPassword] = useState("");
    const [passwordMatch, setPasswordMatch] = useState(false);
    const [inputEmpty, setInputEmpty] = useState(false);
    const navigate = useNavigate();


    const HandleSubmit = e => {
        e.preventDefault();
        if (emptyInput())
            setInputEmpty(true);
        else if (!(passwordConfirm === user.password)) {
            setPasswordMatch(true);
        } else {
            fetch('http://localhost:8080/registration/signup',
                {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Credentials': true
                    },
                    body: JSON.stringify(user)
                }
            )
                .then(res => {
                    if (res.status === 401) {
                        setEmailInvalid(true);
                        console.log("User already exists");
                    } else {
                        console.log(res);
                        navigate("/login");
                    }
                })
                // .then(alert("Registration successful"))
                // .then(navigate("/login"))
                ;
        }
    }


    const handleChange = e => {
        const target = e.target;
        const value = target.value;
        if (target.name === "passwordConfirm") {
            confirmPassword(value);
            setPasswordMatch(false);
        } else {
            const name = target.name;
            setUser({
                ...user,
                [name]: value
            });
        }
    };

    function emptyInput() {
        if (user.firstName === '' || user.lastName === '' || user.email === '' || user.password === '') {
            setInputEmpty(true);
            return true;
        }
        setInputEmpty(false);
        return false;
    }


    return (
        <div className="container">
            <h1>Sign Up</h1>

            {inputEmpty ? <label style={{ color: 'red' }}>Please complete form</label> : null}
            {inputEmpty ? <br></br> : null}

            {emailInvalid ? <label style={{ color: 'red' }}>Email taken</label> : null}
            {emailInvalid ? <br></br> : null}

            <form onSubmit={HandleSubmit}>

                <div class="form-group">
                    <label>
                        First Name:
                        <input type="text" name="firstName" id="firstName" value={user.firstName} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        Last Name:
                        <input type="text" name="lastName" id="lastName" value={user.lastName} onChange={handleChange} />
                    </label>
                </div>

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
                    <label>
                        Confirm Password:
                        <input type="password" name="passwordConfirm" id="passwordConfirm" value={passwordConfirm} onChange={handleChange} />
                    </label>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Register</button><br></br>
                    <Link to="/login">Login here.</Link>
                </div>

            </form>
        </div>
    );
}