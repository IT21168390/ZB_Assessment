
import React, { useState } from "react";
import axios from "axios";

function Signin() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        let token;

        try {
            const response = await axios.post("http://localhost:8080/user/authenticate", {
                email: email,
                password: password,
            });

            token = response.data.jwtToken;

            window.location.href = '/orders';
            localStorage.setItem("TOKEN", token);

        } catch (error) {
            alert("Invalid login!");
        }

    };

    return (
        <>
            <br /><br />
            <div className="container row col shadow p-3 mb-5 bg-white rounded" style={{ margin: 'auto', width: '500px' }}>

                <form onSubmit={handleSubmit}><br/>
                <center><h3>Login</h3></center><br/>
                    <div className="form-floating mb-3">
                        <input type="email" className="form-control" id="email" onChange={(e) => setEmail(e.target.value)} placeholder="name@example.com" />
                        <label htmlFor="floatingInput">Email address</label>
                    </div><br/>
                    <div className="form-floating">
                        <input type="password" className="form-control" id="password" onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
                        <label htmlFor="floatingPassword">Password</label>
                    </div>
                    <br /><center>

                        <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>

                        <div className="text-center">
                            <p>Not registered? <a href="/">Register</a></p>
                        </div>
                    </center>
                </form>

            </div>
        </>
    );
}

export default Signin;
