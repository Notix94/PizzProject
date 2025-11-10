package testMain;

import java.io.IOException;
import pizzas.Commande;
import pizzas.Pizza;

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
    
    Commande com = new Commande();
    Pizza piz = new Pizza();
    com.addPizza(piz);
    com.removePizza(piz);
    System.out.println(com.getEtat());
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
