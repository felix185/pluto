import React from 'react';
import {ReduxProduct} from './ReduxProduct';
import {ReduxAddProductForm} from './ReduxAddProductForm';

export const App = props => (
    <div style={{padding: '20px'}}>
        <h1>Product Listing</h1>
        
        <ReduxAddProductForm />
        
        <button onClick={() => props.onProductRemove(0)}>Remove first</button>

        {props.products.map((p,i) => <ReduxProduct {...p} key={p.title} nr={i} />)}
        
    </div>
); 