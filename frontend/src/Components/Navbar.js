import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Navbar({ role }) {

    const navigate = useNavigate();

    const logout = e => {
        localStorage.removeItem("jwt");
        navigate("/login");
    }

    return (
        <nav class="navbar navbar-inverse navbar-fixed-top navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <Link class="navbar-brand" to="/">Home</Link>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 pagination">
                            <li class="nav-item">
                                {role == "Manager" ?
                                <Link class="navbar-brand" to="/createGroup">Create Group</Link>
                                : <li></li>
                        }
                                <Link class="navbar-brand" to="/showGroups">Show Groups</Link>
                            </li> 
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <button type="button" class="navbar-brand btn-primary-outline" onClick={logout}>Logout</button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}