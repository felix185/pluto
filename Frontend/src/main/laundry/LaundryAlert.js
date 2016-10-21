import React from 'react';
import {Link} from 'react-router';

export const LaundryAlert = props => (
    <form action="/pluto/laundry/createAlert" method="Post">
        E-Mail:<br/>
        <input type="text" name="email"/><br/>
        Erinnerungszeit:<br/>
        <input type="time" name="time"/><br/>
        <input type="submit" value="Alarm erstellen"/>
    </form>
);