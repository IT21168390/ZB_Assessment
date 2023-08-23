import React, { useState } from "react";
import axios from "axios";

function Signup() {
    const [firstname, setFirstname] = useState("");
    const [lastName, setLastname] = useState("");
    const [address, setAddress] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");


    const handleSubmit = async (event) => {
        event.preventDefault();

        if (password !== confirmPassword) {
            alert("Passwords do not match.");
            return;
        }
        if (password.length < 6) {
            alert("Password should be at least 8 characters long.");
            return;
        }

        await axios
            .post("http://localhost:8080/signup/newUser", {
                firstName: firstname,
                lastName: lastName,
                address: address,
                email: email,
                password: password,
            })
            .then((res) => {
                if (res.status === 201) {
                    alert("Successfully Registered.");
                    window.location.href = '/signin';
                } else {
                    alert("Error! Try again...");
                }
            })
            .catch((err) => {
                console.log(err);
                alert("Error! Try again...");
            })
    };

    return (
        <>
            <br /><br />
            <div className="container row col shadow p-3 mb-5 bg-white rounded" style={{ margin: 'auto', width: '500px' }}>

                <form onSubmit={handleSubmit}><br />
                    <center><h3>Register</h3></center><br />
                    <div className="form-floating mb-3">
                        <input type="text" className="form-control" id="firstName" onChange={(e) => setFirstname(e.target.value)} placeholder="First Name" />
                        <label htmlFor="floatingInput">First Name</label>
                    </div><br />
                    <div className="form-floating mb-3">
                        <input type="text" className="form-control" id="lastName" onChange={(e) => setLastname(e.target.value)} placeholder="Last Name" />
                        <label htmlFor="floatingInput">Last Name</label>
                    </div><br />
                    <div className="form-floating mb-3">
                        <input type="text" className="form-control" id="address" onChange={(e) => setAddress(e.target.value)} placeholder="Your Address" />
                        <label htmlFor="floatingInput">Address</label>
                    </div><br />
                    <div className="form-floating mb-3">
                        <input type="email" className="form-control" id="email" onChange={(e) => setEmail(e.target.value)} placeholder="name@example.com" />
                        <label htmlFor="floatingInput">Email address</label>
                    </div><br />
                    <div className="form-floating">
                        <input type="password" className="form-control" id="password" onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
                        <label htmlFor="floatingPassword">Password</label>
                    </div><br />
                    <div className="form-floating">
                        <input type="password" className="form-control" id="confirmPassword" onChange={(e) => setConfirmPassword(e.target.value)} placeholder="Confirm Password" />
                        <label htmlFor="floatingPassword">Confirm Password</label>
                    </div>
                    <br /><center>

                        <button type="submit" className="btn btn-primary btn-block mb-4">Sign up</button>

                        <div className="text-center">
                            <p>Already registered? <a href="signin">Log In</a></p>
                        </div>
                    </center>
                </form>

            </div>
        </>
    );
}

export default Signup;
