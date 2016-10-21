// Dies ist der Einstiegspunkt für das gesamte Frontend

import ReactDOM from 'react-dom';
import React from 'react';

import { Router, Route, hashHistory } from 'react-router'
import {MainPage} from './MainPage'
import {LaundryAlert} from './laundry/LaundryAlert'


ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={MainPage}/>
        <Route path="/laundry/alert" component={LaundryAlert} />
    </Router>,
    document.getElementById('app'));
