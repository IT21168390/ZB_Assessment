import React from 'react'

export default function NavBar() {

    function logOut(){
        localStorage.removeItem("TOKEN");
        window.location.href = '/';
    }

    return (
        <div>
            <nav className="navbar navbar-expand-lg bg-primary" data-bs-theme="dark" style={{paddingLeft: '30px', paddingRight:'30px'}}>
                <div className="container-fluid">
                    <a className="navbar-brand" href="/signin">OrderSystem</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <a className="nav-link" style={{color: 'white'}} aria-current="page" href="/"><b>Home</b></a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" style={{color: 'white'}} href="/orders"><b>Orders</b></a>
                            </li>
                        </ul>
                    </div>
                    <label className='navbar-nav nav-item nav-link' onClick={()=>logOut()}><b>Log Out</b></label>
                </div>
                
            </nav>
        </div>
    )
}
