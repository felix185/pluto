import React from 'react';

export const GreetingPage = props => (
    <div>
        <h1>Greetings!</h1>
        Hello, {props.params.firstname} {props.params.lastname}!
    </div>
);