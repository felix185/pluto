var ManageIngredients = {
  addIngredient(id) {
    var input =
    "<div class='required fields' name='ingredient'>"+
      "<div class='require eight wide field'>" +
        "<input name='ingredient_name' type='text' placeholder='Zutat'/>" +
      "</div>"+
      "<div class='require eight wide field'>" +
        "<input name='ingredient_amount'type='text' placeholder='Menge'/>" +
      "</div>" +
      "</div>";
      var div = document.createElement('div');
      div.innerHTML = input;
    document.getElementById(id).appendChild(div);
  },

  removeIngredient(id) {
    var numberIngredients = document.getElementsByName('ingredient').length;
    if(numberIngredients-1 == 0) {
      alert("Ein Rezept braucht mindestens eine Zutat!");
    } else {
      var elem = document.getElementsByName('ingredient')[numberIngredients-1];
      elem.parentNode.removeChild(elem);
      return false;
    }
  }
};
module.exports = ManageIngredients;
