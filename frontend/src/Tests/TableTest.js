import React from 'react';
import { Link } from "react-router-dom";

const TableTest = () => {
    const table = (
        <div>
            <h1 class="text-center"> Table:</h1>
            <div>
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>Name</th>
                            <th>Hours Worked</th>
                            <th>Accuracy</th>
                        </tr>
    
                    </thead>
                    <tbody>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                        <tr>
                            <td>Peter Griffin</td>
                            <td>9</td>
                            <td>70%</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );

    return (
        <div>
           <div class="sticky-top">
                <nav class="navbar navbar-expand-md navbar-light bg-light">
                    <div class="container-fluid">
                        <Link class="navbar-brand abs" to={'/'}>Group</Link>
                        <div class="navbar-collapse collapse" id="collapseNavbar">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <Link class="nav-link" to={'/'}>Show Schedules</Link>
                                </li>
                                <li class="nav-item">
                                    <Link class="nav-link" to={'/'}>Show Days Off</Link>
                                </li>
                            </ul>
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item">
                                    <button class="btn nav-link">
                                        Add Schedule
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
           </div>
            <div class="container">{table}</div>
        </div>
    );
};

export default TableTest;