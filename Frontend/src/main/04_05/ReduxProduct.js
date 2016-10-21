import {connect} from 'react-redux';
import {Product} from './Product'
import * as ACTIONS from './actions'

const mapStateToProps = (state, ownProps) => {
    return {
        title: state.products[ownProps.nr].title,
        description: state.products[ownProps.nr].description,
        watched: state.products[ownProps.nr].watched,
        category: state.products[ownProps.nr].category
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        onToggleWatched: (index) => {
            dispatch(ACTIONS.toggleWatched(index));
        }
    }
};

const ReduxProduct = connect(mapStateToProps, mapDispatchToProps)(Product);
export {ReduxProduct};