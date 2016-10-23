import React, {Component} from 'react';
import {Link} from 'react-router';

export default class MainPage extends Component {

  constructor(props) {
    super(props);
    this.state = {activities: []};
  }



  componentDidMount() {
    var componentPointer =  this;
    axios.get("http://localhost:8080/Pluto/meta/activities").
    then(function (response) {
      componentPointer.setState({activities: response.data});
    }).catch(function (error) {
      console.log(error);
    })
  }

  render() {

    return (
      <div>
          <div className="ui grid container">
          <div className="sixteen wide column">
            <div className="row">
                <h1>Hauptmenü</h1>
                <ul>
                    <li><Link to="/laundry/alert" >Wäschealarm erstellen</Link></li>
                    <li><Link to="/laundry/icons" >Wäscheinfo anzeigen</Link></li>
                  	<li><Link to="/recipes">Rezepte anzeigen</Link></li>
                  	<li><Link to="/recipes/create">Rezept erstellen</Link></li>
                    <li><Link to="/recipes/search">Rezept suchen</Link></li>
              	</ul>
            </div>
            <div className="row">
              <h1>Aktivitäten</h1>
            </div>
              {this.state.activities.map(function(activity) {
                return (
                  <div className="ui text container segment">
                    <b>{activity.eMail}</b> {activity.activity}
                  </div>
                );
              })}
              </div>
          </div>
      </div>

    );
  }
}
