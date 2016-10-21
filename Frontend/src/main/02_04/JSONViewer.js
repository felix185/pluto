import React from 'react';

/* global JSONFormatter */
export class JSONViewer extends React.Component {

    constructor(props) {
        super(props);
    }

    static get defaultProps() {
        return {
            data: {}
        }
    }

    componentDidMount() {
        const formatter = new JSONFormatter(this.props.data);
        this._child = formatter.render();
        this.refs.viewer.appendChild(this._child);
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.props.data !== prevProps.data) {
            const formatter = new JSONFormatter(this.props.data);
            const newChild = formatter.render();
            this.refs.viewer.replaceChild(newChild, this._child);
            this._child = newChild;
        }
    }

    render() {
        return (
            <div style={{border: '1px solid grey',  padding: '20px', margin: '20px 0 20px 0'}} className="prod">
                <div ref="viewer"></div>
            </div>
        )
    }

}