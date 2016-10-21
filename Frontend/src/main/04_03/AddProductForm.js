import React from 'react';

export class AddProductForm extends React.Component {
    
    constructor(props) {
        super(props);
    }

    render() {
        const addProduct = () => {
            const {title, description, category, watched} = this.props;
            this.props.onProductAdd({title, description, category, watched});
        };
        return (
            <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}}>
                <h2>Add a Product</h2>
                
                <p>Title:</p>
                <input value={this.props.title} onChange={event => this.props.onTitleChange(event.target.value)} />

                <p>Description:</p>
                <input value={this.props.description} onChange={event => this.props.onDescriptionChange(event.target.value)} />

                <p>Category:</p>
                <select value={this.props.category} onChange={event => this.props.onCategoryChange(event.target.value)}>
                    <option value="Furniture">Furniture</option>
                    <option value="Electronics">Electronics</option>
                    <option value="Food">Food</option>
                </select>

                <p></p>
                <input type="checkbox" value={this.props.watched}
                       onChange={event => this.props.onToggleWatched(event.target.checked)} />
                Watched?

                <p></p>
                <button onClick={addProduct}>Add Product</button>

            </div>
        )
    }

} 