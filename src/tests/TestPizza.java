package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pizzas.*;

class TestPizza {

    private Pizza pizza;

    @BeforeEach
    void setUp() {
        pizza = new Pizza("Margarita", TypePizza.Vegetarienne);
    }

    @Test
    void testAjoutIngredientVege() {
        Ingredients jambon = new Ingredients("jambon", 2.0);
        Ingredients tomate = new Ingredients("tomate", 1.0);

        assertFalse(pizza.ajouterIngredient(jambon)); // interdit
        assertTrue(pizza.ajouterIngredient(tomate)); // autorisé
        assertEquals(1, pizza.getIngredients().size());
    }

    @Test
    void testCalculPrixMin() {
        pizza.ajouterIngredient(new Ingredients("tomate", 2.0));
        pizza.ajouterIngredient(new Ingredients("fromage", 1.0));

        assertEquals(4.2, pizza.calculPrixMin(), 0.01); // (2+1)*1.4 = 4.2
        assertEquals(4.2, pizza.getPrixVente(), 0.01);
    }

    @Test
    void testEvaluation() {
        Evaluation e1 = new Evaluation(4);
        Evaluation e2 = new Evaluation(5);

        pizza.ajouterEvaluation(e1);
        pizza.ajouterEvaluation(e2);

        assertEquals(2, pizza.getEvaluations().size());
        assertEquals(4.5, pizza.getNoteMoyenne(), 0.01);
    }
}
