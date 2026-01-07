package pizzas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente une commande passée par un client à la pizzeria.
 * 
 * <p>Une commande contient une liste de pizzas, un identifiant, un nombre de pizzas,
 * et un état indiquant si elle est créée, validée ou traitée.
 * </p>
 * 
 * @author Ewan
 * @version 1.0
 */
public class Commande {
  private int id;

  private List<Pizza> listPizza = new ArrayList<>();
  
  public enum EtatCommande {
	  CREEE, VALIDEE, TRAITEE
  }
  
  private EtatCommande etat;
  
  /**
   *
   * Constructeur par défaut.
   * Initialise l'état de la commande à CREE.
   * 
   **/
  
  public Commande() {
	this.listPizza = new ArrayList<>();
    this.etat = EtatCommande.CREEE;
  }
  
  
  /**
   * Valide la commande si elle est encore en cours de création.
   * Change l'état de la commande à VALIDEE.
   */
  public void validerCommande() {
    if (this.etat == EtatCommande.CREEE) {
        // valider la commande
        this.etat = EtatCommande.VALIDEE;
    }


  }
  
  
  /**
   * Ajoute une pizza à la commande si elle est encore en cours de création.
   * 
   * @param pizza La pizza à ajouter
   */
  
  public void addPizza(Pizza pizza) {
    if (this.etat == EtatCommande.CREEE) {
      listPizza.add(pizza);
    
    }
    
  }
  
  /**
   * Retire une pizza de la commande si elle est encore en cours de création.
   * @param pizza La pizza à retirer
   */
  
  public void removePizza(Pizza pizza) {
    if (this.etat == EtatCommande.CREEE) {
      listPizza.remove(pizza);
  
    } 
  }
  
  public EtatCommande getEtat() {
    return etat;
  }
  public List<Pizza> getPizzas() {
	    return Collections.unmodifiableList(listPizza);
	}
  public double calculerBenefice() {
	    double benefice = 0.0;
	    for (Pizza pizza : listPizza) {
	        benefice += pizza.getPrixVente() - pizza.getPrixMin();
	    }
	    return benefice;
	}
  @Override
  public String toString() {
    return "Commande [id=" + id + ", nbrPizza=" + listPizza.size() + ", listPizza="
        + listPizza + "]";
    
  }
  
}
