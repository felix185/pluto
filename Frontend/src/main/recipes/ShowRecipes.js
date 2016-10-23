import React, {Component} from 'react';
import {Link} from 'react-router';
import DeleteRecipe from './DeleteRecipe'

export default class ShowRecipes extends Component {

  constructor(props) {
    super(props);
    this.state = {recipes: []};
  }

  componentDidMount() {
    var componentPointer =  this;
    axios.get("http://localhost:8080/Pluto/meta/recipes").
    then(function (response) {
      componentPointer.setState({recipes: response.data});
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
              <h1>Rezepte</h1>
              {this.state.recipes.map(function(recipe) {
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
    )
  }
}