import {connect} from 'react-redux';
import {AddProductForm} from './AddProductForm'
import * as ACTIONS from './actions'

const mapStateToProps = (state) => {
    return {
        title: state.addProductForm.title,
        description: state.addProductForm.description,
        watched: state.addProductForm.watched,
        category: state.addProductForm.category
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        onTitleChange: (text) => {
            dispatch(ACTIONS.changeTitle(text));
        },
        onDescriptionChange: (text) => {
            dispatch(ACTIONS.changeDescription(text));
        },
        onCategoryChange: (text) => {
            dispatch(ACTIONS.changeCategory(text));
        },
        onToggleWatched: () => {
            dispatch(ACTIONS.toggleWatchedForm());
        },
        onProductAdd: (product) => {
            dispatch(ACTIONS.addProduct(product));
            dispatch(ACTIONS.clearForm());
        }
    }
};

const ReduxAddProductForm = connect(mapStateToProps, mapDispatchToProps)(AddProductForm);
export {ReduxAddProductForm};