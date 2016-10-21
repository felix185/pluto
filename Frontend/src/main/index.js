// Dies ist der Einstiegspunkt für das gesamte Frontend

import ReactDOM from 'react-dom';
import React from 'react';

import { Router, Route, hashHistory } from 'react-router'

import {Provider} from 'react-redux'
import {createStore} from 'redux';
import {productApp} from './04_05/reducers';

import {ReduxApp} from './04_05/ReduxApp';
import {GreetingPage, Greet} from './04_05/GreetingPage';
import {MissingPage} from './04_05/MissingPage';
import {MainLayout} from './04_05/MainLayout';

//Store erstellen
const store = createStore(productApp);

ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>
            <Route path="/" component={MainLayout}>
                <Route path="/listing" component={ReduxApp} />
                <Route path="/greeting/:lastname/:firstname" component={GreetingPage} />
                <Route path="*" component={MissingPage} />
            </Route>
        </Router>
    </Provider>,
    document.getElementById('app'));