import React from 'react';
import {ReduxProduct} from './ReduxProduct';
import {ReduxAddProductForm} from './ReduxAddProductForm';

export class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div style={{padding: '20px'}}>
                <h1>Product Listing</h1>
                
                <ReduxAddProductForm />
                
                <button onClick={() => this.props.onProductRemove(0)}>Remove first</button>

                {this.props.products.map((p,i) => <ReduxProduct {...p} key={p.title} nr={i} />)}
                
            </div>
        )
    }

} 