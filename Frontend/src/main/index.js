// Dies ist der Einstiegspunkt für das gesamte Frontend

import ReactDOM from 'react-dom';
import React from 'react';

import { Router, Route, hashHistory } from 'react-router'
import MainPage from './MainPage'
import {LaundryAlert} from './laundry/LaundryAlert'
import ShowRecipes from './recipes/ShowRecipes'
import CreateRecipe from './recipes/CreateRecipe'

ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={MainPage}/>
        <Route path="/laundry/alert" component={LaundryAlert} />
        <Route path="/recipes" component={ShowRecipes} />
        <Route path="/recipes/create" component={CreateRecipe} />
    </Router>,
    document.getElementById('app'));
