import React from 'react';

const Product = props => {
    const watched = props.watched;
    const toggleWatch = () => {
        props.onToggleWatched(props.nr);
    };
    return (
        <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}} className="prod">

            <h2>{props.nr + 1}. {props.title}</h2>
            <p>{props.description}</p>
            <p>Category: {props.category}</p>

            <p>
                <button onClick={toggleWatch}>{watched ? 'Unwatch' : 'Watch!'}</button>
                {watched ? ' You are watching the product' : ''}
            </p>

        </div>
    )
};

Product.defaultProps = {
    title: 'Untitled',
    description: 'This product has no description.',
    watched: false,
    category: 'Furniture'
};
export {Product};