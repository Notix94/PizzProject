package pizzas;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * 
 * 
 * 
 **/
public class Commande {
  int id;
  int nbrPizza;
  List<Pizza> listPizza = new ArrayList<>();
  
  enum EtatCommande {
    VALIDEE, CREE, TRAITEE
  }
  
  EtatCommande etat;
  
  /**
   * Constructor create command.
   * 
   * 
   * 
   **/
  
  public Commande() {
    
    this.etat = EtatCommande.CREE;
  }
  
  public void annulerCommande() {
    if(this.etat != EtatCommande.CREE) {
    	System.out.println("Il faut que l'etat de la commande soit en cree alors que : "+this.etat);
    }
    //annuler commande
  }
  
  
  public void validerCommande() {
	  if(this.etat != EtatCommande.CREE) {
		  System.out.println("Il faut que l'etat de la commande soit en cree alorsq que :"+this.etat);
	  }
	  //valider la commande
	  this.etat = EtatCommande.VALIDEE;
	  System.out.println(this.etat);
  }


  /**
   * add Pizza to the command.
   * 
   * 
   * 
   **/
  
  public void addPizza(Pizza pizza) {
    listPizza.add(pizza);
    nbrPizza++;
  }
  
  /**
   * remove Pizza to the command.
   * 
   * 
   * 
   **/
  
  public void removePizza(Pizza pizza) {
    listPizza.remove(pizza);
    nbrPizza--;
  }
  
  @Override
  public String toString() {
    return "Commande [id=" + id + ", nbrPizza=" + nbrPizza + ", listPizza="
        + listPizza + "]";
    
  }
  
  public EtatCommande getEtat() {
    return etat;
  }


  public void setEtat(EtatCommande etat) {
    this.etat = etat;
  }

}
