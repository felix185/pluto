import React from 'react';
import {Product} from './Product';
import {AddProductForm} from './AddProductForm';

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
        this._handleProductAdd = this._handleProductAdd.bind(this);
    }

    _handleRemove() {
        this.setState({
           products: this.state.products.splice(0, this.state.products.length - 1)
        });
    }

    _handleProductAdd(newProduct) {
        this.setState({
            products: this.state.products.concat(newProduct)
        });
    }

    render() {
        return (
            <div style={{padding: '20px'}}>
                <h1>Product Listing</h1>
                
                <AddProductForm onProductAdd={this._handleProductAdd} />
                
                <button onClick={this._handleRemove.bind(this)}>Remove last</button>
                {this.state.products.map((p,i) => <Product {...p} nr={i} />)}
            </div>
        )
    }

} 