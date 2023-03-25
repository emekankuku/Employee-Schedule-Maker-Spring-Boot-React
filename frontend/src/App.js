import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Signup from './Components/Signup';
import Signin from './Components/Signin';
import Home from './Components/Home';
import Navbar from './Components/Navbar';
import AddTask from './Components/AddTask';

function App() {
  return (
    <>
      <Router>
        <Navbar/>
        <Routes>
          <Route path="/login" element={< Signin />} />
          <Route path="/signup" element={< Signup />} />
          <Route path="/home" element={< Home />} />
          <Route path="/addTask" element={< AddTask />} />
          <Route path="*" element={<Signin />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
