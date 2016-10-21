import React from 'react';

export class Product extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            watched: false
        };
    }

    static get defaultProps() {
        return {
            title: 'Untitled',
            description: 'This product has no description.'
        }
    }

    _handleWatchClick() {
        this.setState({
            watched: !this.state.watched
        });
    }


    /* Lifecycle: initiales Rendern der Komponente */

    componentWillMount() {
        /* Keine Änderung von state oder props an dieser Stelle */
        /* Kein zusätzliches Triggern der render() Methode */

        // Ausgabe der aktuellen props
        console.log(this.props);
        console.log(document.getElementsByClassName('prod'))
    }

    componentDidMount() {
        /* Das DOM ist ab jetzt verfügbar */
        /* z.B. Daten laden und sonstige Operationen auf dem DOM durchführen */
        /* Erneutes aufrufen von render() über this.setState() ist erlaubt */

        // Ausgabe der Anzahl an Products
        console.log(document.getElementsByClassName('prod'))
    }


    /* Lifecycle: Update der Komponente, also ab dem 2. Rendern */

    componentWillReceiveProps(nextProps) {
        /* Aufgerufen, wenn die props sich ändern; erst ab dem 2. Rendern */
        /* Hier kann der state geändert werden, ohne erneutes Rendern auszulösen */


    }

    shouldComponentUpdate(nextProps, nextState) {
        /* Optimierung der Performance bei komplexer Update-Logik */
        /* Rückgabe eines boolean */

        return true;
    }

    componentWillUpdate(nextProps, nextState) {
        /* Vorbereitungen in Bezug auf ein bevorstehendes Update */
        /* Keine Änderungen am state per this.setState() erlaubt */

        alert(`Product ${nextProps.title} changes to watched: ${nextState.watched}`);
    }

    componentDidUpdate(prevProps, prevState) {
        /* analog zu componentDidMount() */

        alert(`Product ${this.props.title} changed to watched: ${this.state.watched}`);
    }

    componentWillUnmount() {
        /*  */

        alert(`Product ${this.props.title} will unmount`);
    }

    render() {
        const watched = this.state.watched;
        return (
            <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}} className="prod">

                <h2>{this.props.nr + 1}. {this.props.title}</h2>
                <p>{this.props.description}</p>

                <p>
                    <button onClick={this._handleWatchClick.bind(this)}>{watched ? 'Unwatch' : 'Watch!'}</button>
                    {watched ? ' You are watching the product' : ''}
                </p>

            </div>
        )
    }

}