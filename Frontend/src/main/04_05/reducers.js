import {combineReducers} from 'redux';
import * as ACTIONS from './actions';

const addProductForm = (state = {}, action) => {
    switch (action.type) {
        case ACTIONS.CHANGE_TITLE_FORM:
            return Object.assign({}, state, {title: action.text});
        case ACTIONS.CHANGE_DESCRIPTION_FORM:
            return Object.assign({}, state, {description: action.text});
        case ACTIONS.CHANGE_CATEGORY_FORM:
            return Object.assign({}, state, {category: action.text});
        case ACTIONS.TOGGLE_WATCHED_FORM:
            return Object.assign({}, state, {watched: !state.watched});
        case ACTIONS.CLEAR_FORM:
            return {};
        default:
            return state;
    }
};

const products = (state = [{title: 'prod1', description: 'prod2'}], action) => {
    switch (action.type) {
        case ACTIONS.ADD_PRODUCT:
            return state.concat(action.product);
        case ACTIONS.REMOVE_PRODUCT:
            return state.filter((p, i) => i !== action.index);
        case ACTIONS.TOGGLE_WATCHED:
            return state.map((p, i) => {
                if (action.index === i) {
                    return Object.assign({}, p, {watched: !p.watched})
                }
                return p;
            });
        default:
            return state;
    }
};

const productApp = combineReducers({
    addProductForm,
    products
});

export {productApp};