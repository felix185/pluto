var CreateJSON = {
  createJSON() {
    var text = document.getElementById('text').value.replace(/\n/g, " ");
    var title = document.getElementById('title').value;
    var eMail = document.getElementById('mail').value;
    var ingredient_name = document.getElementsByName('ingredient_name');
    var ingredient_amount = document.getElementsByName('ingredient_amount');
    var isValidInput = false;

    for(var i = 0; i < ingredient_name.length; i++) {
      if(isNaN(ingredient_amount[i].value)) {
        alert("Mengenangaben bitte nur als Zahl eintragen! Kommazahlen mit Punkt eintragen, z.B.: 2.5!");
        isValidInput = false;
      } else if (ingredient_amount[i].value == "" || ingredient_name[i].value == "" || text == "" || title == "" || eMail == "") {
        alert("Alle Felder sind Pflichtfelder!");
        isValidInput = false;
      } else {
        isValidInput = true;
      }
    }

    if(isValidInput) {
      var result = "newRecipe={"
      + "\"text\": \"" + text + "\", "
      + "\"title\": \"" + title + "\", "
      + "\"eMail\": \"" + eMail + "\", "
      + "\"ingredients\": [{\"name\": \"" + ingredient_name[0].value + "\", " + "\"amount\": \""+ ingredient_amount[0].value + "\"}";
      for(var i = 1; i < ingredient_name.length; i++){
        result += ", {\"name\": \""+ ingredient_name[i].value + "\", " + "\"amount\": \""+ ingredient_amount[i].value + "\"}";
      }
      result += "]}";
      axios.post("http://localhost:8080/Pluto/meta/recipe",
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
  }
};
module.exports = CreateJSON;
