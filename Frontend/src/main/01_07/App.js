import React from 'react';
import {Product} from './Product';

export class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [
                {title: 'Metal Chair', description: 'My favorite chair!'},
                {title: 'Wooden Chair', description: 'Super duper chair!'},
                {}
            ]
        };
    }

    _handleRemove() {
        this.setState({
           products: this.state.products.splice(0, this.state.products.length - 1)
        });
    }

    render() {
        return (
            <div style={{padding: '20px'}}>
                <h1>Product Listing</h1>
                <button onClick={this._handleRemove.bind(this)}>Remove last</button>
                {this.state.products.map((p,i) => <Product {...p} nr={i} />)}
            </div>
        )
    }

} 