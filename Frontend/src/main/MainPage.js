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
          <div>
              <h1>Hauptmenü</h1>
              <ul>
                  <li><Link to="/laundry/alert" >Wäschealarm erstellen</Link></li>
              </ul>
          </div>
          <div>
            <h1>Aktivitaeten</h1>
            {this.state.activities.map(function(activity) {
              return (
                <div>
                  {activity.eMail} {activity.activity}
                </div>
              );
            })}
          </div>
      </div>

    );
  }
}
