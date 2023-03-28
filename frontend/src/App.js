import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
  Navigate,
} from "react-router-dom";
import Signup from './Components/Signup';
import Signin from './Components/Signin';
import Home from './Components/Home';
import Navbar from './Components/Navbar';
import AddTask from './Components/AddTask';

function App() {

  const NavLayout = () => (
    <>
      <Navbar />
      <Outlet />
    </>
  );

  return (
    <>
      <Router>
        <Routes>
          <Route path="/home" element={<NavLayout />}>
            <Route index element={<Home />} />
          </Route>
          <Route path="/login" element={< Signin />} />
          <Route path="/signup" element={< Signup />} />
          <Route path="*" element={<Signin />} />
          {/* <Route path="/addTask" element={< AddTask />} /> */}
        </Routes>
      </Router>
    </>
  );
}

export default App;
