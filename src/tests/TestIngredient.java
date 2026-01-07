package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzas.Ingredients;

/**
 * Tests JUnit de la classe {@link pizzas.Ingredients Ingredients}.
 *
 * @author Eric Cariou
 * @see pizzas.Ingredients
 */
public class TestIngredient {
	private Ingredients fromage;


	@BeforeEach
	public void setUp() {
		fromage = new Ingredients("Fromage", 1.5);
		
	}
	
	
	@Test
	public void testConstructeurNomPrix() {
	    Ingredients i = new Ingredients("Olives", 1.0);
	    assertEquals("Olives", i.getNom());
	    assertEquals(1.0, i.getPrix(), 0.001);
	}
	
	@Test
	public void testConstructeurPrixNegatif() {
	    Ingredients i = new Ingredients("Jambon", -2.0);
	    assertEquals(0.0, i.getPrix(), 0.001);
	}
	
	@Test
	public void testSetPrixValide() {
	    fromage.setPrix(2.0);
	    assertEquals(2.0, fromage.getPrix(), 0.001);
	}
	
	@Test
	public void testSetPrixNegatif() {
	    double ancienPrix = fromage.getPrix();
	    fromage.setPrix(-5.0);
	    assertEquals(ancienPrix, fromage.getPrix(), 0.001);  // prix inchangé
	}
	@Test
	public void testNomNull() {
	    Ingredients i = new Ingredients(null, 1.0);
	    assertNotNull(i.getNom());
	    assertEquals("", i.getNom()); // selon notre constructeur
	}
}