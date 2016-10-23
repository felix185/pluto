import React, {Component} from 'react';

export default class CreateRecipe extends Component {
  constructor(props) {
    super(props);
    this.state = {recipes: []};
  }

  render() {
    return (
      <div>
        <div className="ui grid container">
          <div className="sixteen wide column">
            <div className="row">
              <h1>Wäschealarm erstellen</h1>
              <div className="ui form">
                <div className="required field">
                  <label>E-Mail-Adresse</label>
                  <input id="email" type="text" placeholder="acount@domain"/>
                </div>
                <div className="required field">
                  <label>Uhrzeit</label>
                  <input id="time" type="time"/>
                </div>
                <button className="ui green button" onClick={() => {this.postForm()}} type="submit">Alarm erstellen</button>
              </div>
            </div>
          </div>
        </div>
      </div>

    )
  }
  postForm() {
    var time = document.getElementById('time').value;
    var eMail = document.getElementById('email').value;
    var isValidInput = false;
    if (time == "" || eMail == "") {
      alert("Alle Felder sind Pflichtfelder!");
      isValidInput = false;
    } else {
      isValidInput = true;
    }

    if(isValidInput) {
      var result = 'time=' + time + '&email=' + eMail;
      axios.post("http://localhost:8080/Pluto/laundry/createAlert",
        result,
        {headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        }}
      ).then (function() {
        window.location.reload();
      }).catch(function(error) {
        console.log(error);
      });
    }
    alert("Alarm wurde erstellt");
  }
}