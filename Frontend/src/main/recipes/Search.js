import React, {Component} from 'react';
import {Link} from 'react-router';
import ManageIngredients from './ManageIngredients';
import DeleteRecipe from './DeleteRecipe'

export default class RecipeSearch extends Component {

  constructor(props) {
    super(props);
    this.state = {
      searchResults: []};
  }

  createSearchJSON() {
    var ingredient_name = document.getElementsByName('ingredient_name');
    var ingredient_amount = document.getElementsByName('ingredient_amount');
    var isValidInput = false;
    var thisPointer = this;
    for(var i = 0; i < ingredient_name.length; i++) {
      if(isNaN(ingredient_amount[i].value)) {
        alert("Mengenangaben bitte nur als Zahl eintragen! Kommazahlen mit Punkt eintragen, z.B.: 2.5!");
        isValidInput = false;
      } else if (ingredient_amount[i].value == "" || ingredient_name[i].value == "") {
        alert("Alle Felder sind Pflichtfelder!");
        isValidInput = false;
      } else {
        isValidInput = true;
      }
    }

    if(isValidInput) {
      var result = "ingredients=[{\"name\": \"" + ingredient_name[0].value + "\", " + "\"amount\": \""+ ingredient_amount[0].value + "\"}";
      for(var i = 1; i < ingredient_name.length; i++){
        result += ", {\"name\": \""+ ingredient_name[i].value + "\", " + "\"amount\": \""+ ingredient_amount[i].value + "\"}";
      }
      result += "]}";
      axios.post("http://localhost:8080/Pluto/meta/search",
      result,
      {headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }}
    ).then (function(response) {
        thisPointer.setState({searchResults: response.data});
        console.log(response);
      }).catch(function(error) {
        console.log(error);
      });
    }
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
              <h1>Rezept suchen</h1>
              <div className="ui form">

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
                <button className="ui green button" onClick={() => this.createSearchJSON()} type="submit">Rezept suchen</button>
              </div>
            </div>
            <br/>
            <div className="row">
              <h1>Rezepte</h1>
              {this.state.searchResults.map(function(recipe) {
                return (
                  <div className="ui piled segment">
                    <h4 class="ui header">{recipe.title} ({recipe.id})</h4>
                    <p>Von <b>{recipe.eMail}</b></p>
                    <p><b>Zutaten:</b></p>
                    <ul>
                    {recipe.ingredients.map(function(ingredient) {
                      return (
                          <li>{ingredient.name}: {ingredient.amount}</li>
                      )
                    })}
                    </ul>
                    <p><b>Zubereitung:</b></p>
                    <p>{recipe.text}</p>
                    <button onClick={() => {DeleteRecipe.deleteRecipe(recipe.id)}} className="negative ui button">
                      Löschen
                    </button>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>

    );
  }
}
