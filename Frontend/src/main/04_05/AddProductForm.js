import React from 'react';

export const AddProductForm = props => {
    
        const addProduct = () => {
            const {title, description, category, watched} = props;
            props.onProductAdd({title, description, category, watched});
        };
        return (
            <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}}>
                <h2>Add a Product</h2>
                
                <p>Title:</p>
                <input value={props.title} onChange={event => props.onTitleChange(event.target.value)} />

                <p>Description:</p>
                <input value={props.description} onChange={event => props.onDescriptionChange(event.target.value)} />

                <p>Category:</p>
                <select value={props.category} onChange={event => props.onCategoryChange(event.target.value)}>
                    <option value="Furniture">Furniture</option>
                    <option value="Electronics">Electronics</option>
                    <option value="Food">Food</option>
                </select>

                <p></p>
                <input type="checkbox" value={props.watched}
                       onChange={event => props.onToggleWatched(event.target.checked)} />
                Watched?

                <p></p>
                <button onClick={addProduct}>Add Product</button>

            </div>
        );
};