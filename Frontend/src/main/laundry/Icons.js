import React, {Component} from 'react';
import {Link} from 'react-router';

export default class LaundryIcons extends Component {

  constructor(props) {
    super(props);
    this.state = {icons: [],
      search:"",
      searchResults: []};
  }

  componentDidMount() {
    var componentPointer =  this;
    axios.get("http://localhost:8080/Pluto/laundry/icons").
    then(function (response) {
      componentPointer.setState({icons: response.data, searchResults: response.data});
    }).catch(function (error) {
      console.log(error);
    })
  }

  handleSearchChange(event) {
    this.setState({search: event.target.value});
  }

  handleSearchClick() {
    var searchResultsTmp = [];
    this.state.icons.some(function(icon) {
      icon.tags.some(function(tag) {
        if(tag === this.state.search) {
          searchResultsTmp.push(icon);
          return true;
        }
        return false;
      }, this);
      return false;
    }, this);
    this.setState({searchResults: searchResultsTmp});
  }

  resetSearch() {
    this.setState({searchResults: this.state.icons, search: ""});
  }

  render() {

    return (
      <div>
          <div className="ui grid container">
            <div className="row">
              <center><h1>Icons</h1></center>
            </div>
            <div className="ui text container segment input">
              <input type="text" placeholder="Suche" value={this.state.search} onChange={(event) => this.handleSearchChange(event)}/>
              <button className="ui button" onClick={() => this.handleSearchClick()}>Suche</button>
              <button className="ui button" onClick={() => this.resetSearch()}>Zurücksetzen</button>
            </div>
              {this.state.searchResults.map(function(icon) {
                return (
                  <div className="ui text container segment">
                    <img src={icon.url} alt={icon.name} />
                    <b>{icon.name}:</b> {icon.description}
                  </div>
                );
              })}

          </div>
      </div>

    );
  }
}