import React, {Component} from 'react';
import {Link} from 'react-router';
import ManageIngredients from './ManageIngredients';
import CreateJSON from './CreateJSON';

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
              <h1>Hauptmenü</h1>
              <ul>
                  <li><Link to="/">Startseite</Link></li>
                  <li><Link to="/laundry/alert" >Wäschealarm erstellen</Link></li>
                  <li><Link to="/laundry/icons" >Wäscheinfo anzeigen</Link></li>
                  <li><Link to="/recipes">Rezepte anzeigen</Link></li>
                  <li><Link to="/recipes/create">Rezept erstellen</Link></li>
                  <li><Link to="/recipes/search">Rezept suchen</Link></li>
              </ul>
          </div>
            <div className="row">
              <h1>Rezept anlegen</h1>
              <div className="ui form">
                <div className="required field">
                  <label>Titel</label>
                  <input id="title" type="text" placeholder="Titel"/>
                </div>
                <div className="required field">
                  <label>E-Mail</label>
                  <input id="mail" type="text" placeholder="E-Mail"/>
                </div>
                <div className="required field">
                  <label>Zubereitung</label>
                  <textarea id="text" placeholder="Zubereitung"></textarea>
                </div>
                <div id="ingredients" className="required field">
                  <label>Zutaten (Mengenangaben nur als Zahlen)</label>
                  <div className="required fields" name="ingredient">
                    <div className="require eight wide field">
                      <input name='ingredient_name' type="text" placeholder="Zutat"/>
                    </div>
                    <div className="require eight wide field">
                      <input name='ingredient_amount'type="text" placeholder="Menge"/>
                    </div>
                  </div>
                </div>
                <button id="addIngredient" className="ui grey button" onClick={() => {ManageIngredients.addIngredient('ingredients')}}>Zutat hinzufügen</button>
                <button id="removeIngredient" className="ui orange button" onClick={() => {ManageIngredients.removeIngredient('ingredients')}}>Zutat entfernen</button>
                <br/>
                <br/>
                <button className="ui green button" onClick={() => {CreateJSON.createJSON()}} type="submit">Rezept anlegen</button>
              </div>
            </div>
          </div>
        </div>
      </div>

    )
  }
}
