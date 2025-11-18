package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pizzas.NonConnecteException;

/**
 * Test de la classe {@link pizzas.NonConnecteException}.
 */
public class TestNonConnecteException {
  
  /**
   * Vérifie que le message d'erreur est correctement défini.
   */
  @Test
  void testMessageException() {
    NonConnecteException ex = new NonConnecteException();
    assertEquals("Aucun Utilisateur connecté.", ex.getMessage());
  }
}
