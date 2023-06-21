import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Signup from './Components/Authentication/Signup';
import Signin from './Components/Authentication/Signin';
import { Home } from './Components/Home';
import { Group } from './Components/Group/Group';
import { CreatGroup } from './Components/Group/CreateGroup';
import { ShowGroups } from './Components/Group/ShowGroups';
import { AddUser } from './Components/Group/AddUser';
import { ShowSchedules } from './Components/Schedule/ShowSchedules';
import { AddSchedule } from './Components/Schedule/AddSchedule';
import PrivateRoute from './Components/PrivateRoute';

function App() {

  return (
    <>
      <Router>
        <Routes>
          <Route path="/login" element={< Signin />} />
          <Route path="/signup" element={< Signup />} />
          <Route path="/" element={<PrivateRoute child={<Home />} role="" hideNav="false" />} />
          <Route path="/createGroup" element={<PrivateRoute child={<CreatGroup />} role="Manager" hideNav="false" />} />
          <Route path="/showGroups" element={<PrivateRoute child={<ShowGroups />} role="" hideNav="false" />} />
          <Route path="/groups/:groupName" element={<PrivateRoute child={<Group />} role="" hideNav="false" />} />
          <Route path="/addUser/:groupName" element={<PrivateRoute child={<AddUser />} role="" hideNav="false" />} />
          <Route path="/showSchedules/:groupName" element={<PrivateRoute child={<ShowSchedules />} role="" hideNav="false" />} />
          <Route path="/addSchedule/" element={<PrivateRoute child={<AddSchedule />} role="" hideNav="false" />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
