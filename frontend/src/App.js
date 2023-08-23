import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Orders from "./components/Orders";
import SignIn from "./components/userAuthentication/SignIn";
import SignUp from "./components/userAuthentication/SignUp";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path='/signin' Component={SignIn}></Route>
          <Route path='/' Component={SignUp}></Route>
          <Route path='/orders' Component={Orders}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
