package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pizzas.CommandeException;

/**
 * Test de la classe {@link pizzas.CommandeException}.
 */
public class TestCommandeException {
  
  /**
   * Vérifie que le message d'erreur est correctement défini.
   */
  @Test
  void testMessageException() {
    CommandeException ex = new CommandeException();
    assertEquals("Erreur liée à la commande.", ex.getMessage());
  }
}
