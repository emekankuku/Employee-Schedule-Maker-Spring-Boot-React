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
import { ShowGroups } from './Components/Group/ShowGroups';
import { ShowSchedules } from './Components/Schedule/ShowSchedules';
import { ShowDaysOff } from './Components/Schedule/ShowDaysOff';
import { CheckIn } from './Components/Check In/CheckIn';
import PrivateRoute from './Components/PrivateRoute';
import GroupReport from './Components/GroupReport';
import TableTest from './Tests/TableTest';

function App() {

  return (
    <>
      <Router>
        <Routes>
          <Route path="/login" element={< Signin />} />
          <Route path="/signup" element={< Signup />} />
          <Route path="/" element={<PrivateRoute child={<ShowGroups />} role="" hideNav="false" />} />
          <Route path="/groups/:groupName" element={<PrivateRoute child={<Group />} role="" hideNav="false" />} />
          <Route path="/showSchedules/:groupName" element={<PrivateRoute child={<ShowSchedules />} role="" hideNav="false" />} />
          <Route path="/showDaysOff/:groupName" element={<PrivateRoute child={<ShowDaysOff />} role="" hideNav="false" />} />
          <Route path="/groupReport/:groupName" element={<PrivateRoute child={<GroupReport />} role="" hideNav="false" />} />
          <Route path="/checkIn/:groupName" element={<PrivateRoute child={<CheckIn />} role="" hideNav="false" />} />
          <Route path="/TableTest"  element={<PrivateRoute child={<TableTest />} role="" hideNav="false" />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
