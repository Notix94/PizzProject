package pizzas;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un ingrédient utilisé dans une pizza.
 * 
 * <p>Chaque ingrédient a un nom, un prix
 * </p>
 * 
 *
 * @author Ewan
 * @version 1.0
 */
public class Ingredients {
  
  private String nom;
  private double prix;
  private Set<TypePizza> typesInterdits = new HashSet<>();

  /**
   * Crée un ingrédient avec un nom et un prix.
   *
   * @param nom nom de l'ingrédient
   * @param prix prix de l'ingrédient
   */
  public Ingredients(String nom, double prix) {
	this.nom = (nom != null) ? nom : "Nouveau ingrédients";
    setPrix(prix);


  }
  
  
  
  public void interdirePour(TypePizza type) {
      typesInterdits.add(type);
  }

  public boolean estInterditPour(TypePizza type) {
      return typesInterdits.contains(type);
  }
  
  
  /**
   * Retourne le nom de l'ingrédient.
   *
   * @return nom de l'ingrédient
   */
  public String getNom() {
    return nom;
  }
  
  /**
   * Retourne le prix de l'ingrédient.
   *
   * @return prix de l'ingrédient
   */
  public double getPrix() {
    return prix;
  }
  
  /**
   * Modifie le prix de l'ingrédient.
   *
   * @param prix nouveau prix
   */
  public void setPrix(double prix) {
	  if(prix >=0) {
		  this.prix = prix;
	  }
    this.prix = prix;
  }
  @Override
  public String toString() {
      return nom + " - " + String.format("%.2f €", prix);
  }

  
  
  
}
