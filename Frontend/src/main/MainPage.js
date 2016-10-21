import React from 'react';
import {Link} from 'react-router';

export const MainPage = props => (
    <div>
        <div>
            <h1>Hauptmenü</h1>
            <ul>
                <li><Link to="/laundry/alert" >Wäschealarm erstellen</Link></li>
            </ul>
        </div>
    </div>
);