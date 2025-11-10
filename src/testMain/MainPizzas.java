package testMain;

import java.io.IOException;
import pizzas.Commande;
import pizzas.Pizza;
import pizzas.Evaluation;
/**
 * Classe d'essai de fonctionnement de l'application.
 *
 * @author Eric Cariou
 *
 */
public class MainPizzas {
  
  /**
   * Si le main() s'exécute, c'est que le projet est fonctionnel.
   *
   * @param args inutiles ici.
   */
  public static void main(String[] args) {
    
 //decla obj
    Pizza piz = new Pizza();
    Commande com = new Commande();
    
    //tester remove et add pizz
   
    com.addPizza(piz);
    com.removePizza(piz);
    
    //verif etat command
    System.out.println(com.getEtat());
    com.validerCommande();
    com.validerCommande();
    System.out.println(com);
    
    
    
    
    // System.out.println("\nAppuyez sur Entrée pour terminer le programme
    // ...");
    // try {
    // System.in.read();
    // } catch (IOException e) {
    // System.err.println("Vous avez réussi à casser le clavier : " + e);
    // }
    // System.out.println("... terminé");
    
  }
}
