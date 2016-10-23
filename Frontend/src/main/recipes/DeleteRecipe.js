var DeleteRecipe = {
  deleteRecipe(identity) {
    axios.delete("http://localhost:8080/Pluto/meta/recipe/" + identity)
    .then (function() {
      console.log("Recipe deleted successfully");
      window.location.reload();
    }).catch(function (error) {
      console.log(error);
    })
  }
};

module.exports = DeleteRecipe;
