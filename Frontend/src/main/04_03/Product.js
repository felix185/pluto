import React from 'react';

export class Product extends React.Component {

    constructor(props) {
        super(props);
    }

    static get defaultProps() {
        return {
            title: 'Untitled',
            description: 'This product has no description.',
            watched: false,
            category: 'Furniture'
        }
    }

    render() {
        const watched = this.props.watched;
        const toggleWatch = () => {
            this.props.onToggleWatched(this.props.nr);
        };
        return (
            <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}} className="prod">

                <h2>{this.props.nr + 1}. {this.props.title}</h2>
                <p>{this.props.description}</p>
                <p>Category: {this.props.category}</p>

                <p>
                    <button onClick={toggleWatch}>{watched ? 'Unwatch' : 'Watch!'}</button>
                    {watched ? ' You are watching the product' : ''}
                </p>

            </div>
        )
    }

}