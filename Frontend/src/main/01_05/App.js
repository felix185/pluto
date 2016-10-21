import React from 'react';
import {Product} from './Product';

export class App extends React.Component {

    constructor(props) {
        super(props);
        this._products = [
            {title: 'Metal Chair', description: 'My favorite chair!'},
            {title: 'Wooden Chair', description: 'Super duper chair!'}
        ];
    }

    render() {
        return (
            <div style={{padding: '20px'}}>
                <h1>Product Listing</h1>
                {this._products.map((p,i) => <Product title={p.title} description={p.description} nr={i} />)}
            </div>
        )
    }

} 