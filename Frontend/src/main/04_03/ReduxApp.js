import {connect} from 'react-redux';
import {App} from './App'
import * as ACTIONS from './actions'

const mapStateToProps = (state) => {
    return {
        products: state.products
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        onProductRemove: (index) => {
            dispatch(ACTIONS.removeProduct(index));
        }
    }
};

const ReduxApp = connect(mapStateToProps, mapDispatchToProps)(App);
export {ReduxApp};