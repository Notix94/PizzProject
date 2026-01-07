package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pizzas.Commande;
import pizzas.Ingredients;
import pizzas.Pizza;
import pizzas.TypePizza; // si tu as l'enum TypePizza

class TestCommande {

    private Commande commande;
    private Pizza pizza1;
    private Pizza pizza2;

    @BeforeEach
    void setUp() {
        // Nouvelle commande avant chaque test
        commande = new Commande();

        // Création de pizzas avec deux paramètres : nom et type
        pizza1 = new Pizza("Margarita", TypePizza.Vegetarienne);
        pizza2 = new Pizza("4 Fromages", TypePizza.Vegetarienne);
       
        Ingredients sauce = new Ingredients("sauce tomate", 2.0);
        Ingredients fromage = new Ingredients("fromage", 1.0);
        Ingredients creme = new Ingredients("crème", 2.0);
        Ingredients fromage2 = new Ingredients("fromage", 2.0);

        pizza1.ajouterIngredient(sauce);
        pizza1.ajouterIngredient(fromage);
        pizza1.setPrixVente(10.0);

       

        pizza2.ajouterIngredient(creme);
        pizza2.ajouterIngredient(fromage2);
        pizza2.setPrixVente(12.0);
        
    }

    @Test
    void testAjoutPizza() {
        assertEquals(0, commande.getPizzas().size());

        commande.addPizza(pizza1);
        assertEquals(1, commande.getPizzas().size());
        assertTrue(commande.getPizzas().contains(pizza1));
    }

    @Test
    void testRetirerPizza() {
        commande.addPizza(pizza1);
        commande.addPizza(pizza2);

        commande.removePizza(pizza1);
        assertEquals(1, commande.getPizzas().size());
        assertFalse(commande.getPizzas().contains(pizza1));
    }

    @Test
    void testValiderCommande() {
        assertEquals(Commande.EtatCommande.CREEE, commande.getEtat());

        commande.validerCommande();
        assertEquals(Commande.EtatCommande.VALIDEE, commande.getEtat());

        // Tentative de revalidation : état ne change pas
        commande.validerCommande();
        assertEquals(Commande.EtatCommande.VALIDEE, commande.getEtat());
    }

    @Test
    void testCalculerBenefice() {
        commande.addPizza(pizza1); 
        commande.addPizza(pizza2); 

        double benefice = commande.calculerBenefice();
        assertEquals(12.2, benefice, 0.01); // tolérance pour double
    }
}
