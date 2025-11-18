package pizzas;

import java.util.List;

public class Pizza {
  
  private String nom;
  private TypePizza type;
  private List<Ingredients> ingredients;
  
  public Pizza(String nom, TypePizza type, List<Ingredients> ingredients) {
    super();
    this.nom = nom;
    this.type = type;
    this.ingredients = ingredients;
  }
  
  public String getNom() {
    return nom;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  public TypePizza getType() {
    return type;
  }
  
  public void setType(TypePizza type) {
    this.type = type;
  }
  
  public List<Ingredients> getIngredients() {
    return ingredients;
  }
  
  public void setIngredients(List<Ingredients> ingredients) {
    this.ingredients = ingredients;
  }
  
  /**
   * .
   *
   **/
  
  public void ajouterIngredient(Ingredients ingredient) {
    
    // Règle 1 : Pizza végétarienne -> interdit les ingrédients carnés
    if (this.type == TypePizza.Vegetarienne && ingredient.isViande()) {
      throw new IllegalArgumentException(
          "Impossible d’ajouter " + ingredient.getNom()
              + " : ingrédient carné interdit sur une pizza végétarienne !");
    }
    
    // Règle 2 : Pizza régionale -> ingrédients doivent être régionaux
    if (this.type == TypePizza.Regionale && !ingredient.isRegional()) {
      throw new IllegalArgumentException("Impossible d’ajouter "
          + ingredient.getNom()
          + " : ingrédient non régional interdit sur une pizza régionale !");
    }
    
    this.ingredients.add(ingredient);
  }
  
  public double prixPizza() {
    double prixIngr = 0.00;
    for (Ingredients ingr : ingredients) {
      
      prixIngr += ingr.getPrix() * 1.40;
      
    }
    
    return prixIngr = Math.round(prixIngr * 10.0) / 10.0;
  }
  
  
  
}

