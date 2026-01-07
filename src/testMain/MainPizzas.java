package testMain;

import pizzas.Commande;
import pizzas.Evaluation;
import pizzas.Ingredients;
import pizzas.Pizza;
import java.util.ArrayList;
import pizzas.TypePizza;

/**
 * Classe d'essai de fonctionnement de l'application Pizza.
 * 
 * <p>
 * Ce main reproduit tous les cas de test du plan de test PDF : -
 * Ajout/suppression d'ingrédients selon les règles (végétarienne, régionale) -
 * Calcul du prix des pizzas - Ajout/suppression de pizzas dans une commande -
 * Validation et annulation de commande -
 * </p>
 */
public class MainPizzas {
	/**
	 * methode main.
	 * 
	 **/
	public static void main(String[] args) {

		System.out.println(
				"=== TEST 1 : Ajout d'ingrédients sur pizza végétarienne ===");
		Pizza pizzaVeg = new Pizza("Veggie", TypePizza.Vegetarienne);
		Ingredients tomate = new Ingredients("Tomate", 1.0);
		Ingredients fromage = new Ingredients("Fromage", 1.5);
		Ingredients jambon = new Ingredients("Jambon", 2.0);

		pizzaVeg.ajouterIngredient(tomate);
		pizzaVeg.ajouterIngredient(fromage);

		try {
			pizzaVeg.ajouterIngredient(jambon); // doit échouer
		} catch (IllegalArgumentException e) {
			System.out.println("Test réussi : " + e.getMessage());
		}

		System.out.println("Prix pizza Veggie : " + pizzaVeg.getPrixVente()+ " €");

		System.out.println(
				"\n=== TEST 2 : Ajout d'ingrédients sur pizza régionale ===");
		Pizza pizzaReg = new Pizza("Regionale", TypePizza.Regionale);
		Ingredients chorizo = new Ingredients("Chorizo", 2.5);
		Ingredients champignon = new Ingredients("Champignon", 1.0);

		pizzaReg.ajouterIngredient(chorizo);
		try {
			pizzaReg.ajouterIngredient(champignon); // doit échouer
		} catch (IllegalArgumentException e) {
			System.out.println("Test réussi : " + e.getMessage());
		}

		System.out.println("Prix pizza Regionale : " + pizzaReg.getPrixVente() + " €");

		System.out.println(
				"\n=== TEST 3 : Ajout et suppression de pizzas dans une commande ===");
		Commande com = new Commande();
		com.addPizza(pizzaVeg);
		com.addPizza(pizzaReg);
		System.out.println("Commandes avant suppression : " + com);
		com.removePizza(pizzaVeg);
		System.out.println("Commandes après suppression de Veggie : " + com);

		System.out.println("\n=== TEST 4 : Validation de commande ===");
		System.out.println("État avant validation : " + com.getEtat());
		com.validerCommande();
		System.out.println("État après validation : " + com.getEtat());
		com.validerCommande(); // test répétition de validation

		System.out.println("\n=== FIN DES TESTS ===");
		
		
	}
}
