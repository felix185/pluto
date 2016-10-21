import React from 'react';
import {Link} from 'react-router';

const divStyle = {border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'};
const linkStyle = {activeStyle: {color: 'red'}};

export const MainLayout = props => (
    <div>
        <div style={divStyle}>
            <h1>This is the main page layout</h1>
            <p>...with page navigation...</p>
            <ul>
                <li><Link to="/greeting/Geroe/Andreas" {...linkStyle}>Welcome, Andreas</Link></li>
                <li><Link to="/listing" {...linkStyle}>Product Listing</Link></li>
            </ul>
        </div>
        <div style={divStyle}>
            {props.children || 'Please select a sub-page'}
        </div>
    </div>
);