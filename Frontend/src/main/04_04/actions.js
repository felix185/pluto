/* action types */

export const ADD_PRODUCT = 'ADD_PRODUCT';
export const REMOVE_PRODUCT = 'REMOVE_PRODUCT';
export const TOGGLE_WATCHED = 'TOGGLE_WATCHED';

export const CHANGE_TITLE_FORM = 'CHANGE_TITLE_FORM';
export const CHANGE_DESCRIPTION_FORM = 'CHANGE_DESCRIPTION_FORM';
export const CHANGE_CATEGORY_FORM = 'CHANGE_CATEGORY_FORM';
export const TOGGLE_WATCHED_FORM = 'TOGGLE_WATCHED_FORM';
export const CLEAR_FORM = 'CLEAR_FORM';

/* action creators */

export function addProduct(product) {
    return { type: ADD_PRODUCT, product: product }
}
export function removeProduct(index) {
    return { type: REMOVE_PRODUCT, index: index }
}
export function toggleWatched(index) {
    return { type: TOGGLE_WATCHED, index: index }
}

export function changeTitle(text) {
    return { type: CHANGE_TITLE_FORM, text: text }
}
export function changeDescription(text) {
    return { type: CHANGE_DESCRIPTION_FORM, text: text }
}
export function changeCategory(text) {
    return { type: CHANGE_CATEGORY_FORM, text: text }
}
export function toggleWatchedForm() {
    return { type: TOGGLE_WATCHED_FORM }
}
export function clearForm() {
    return { type: CLEAR_FORM }
}