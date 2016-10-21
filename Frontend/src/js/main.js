import React, {Component} from 'react';

export default class Main extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <ul class="nav nav-tabs">
          <li role="presentation" class="active"><a href="/">Home</a></li>
          <li role="presentation"><a href="/laundryAlert">Wäschealarm</a></li>
          <li role="presentation"><a href="/receptCollection">Rezepte anzeigen</a></li>
          <li role="presentation"><a href="/receptCreation">Rezept erstellen</a></li>
        </ul>
        {this.props.children}
      </div>
    );
  }
};
