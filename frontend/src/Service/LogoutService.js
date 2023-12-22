class LogoutService extends React.Component {
     logout() {
        localStorage.removeItem("jwt");
        navigate("/login");
    }
}

export default new LogoutService();