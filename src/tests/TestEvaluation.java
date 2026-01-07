package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pizzas.Evaluation;

class TestEvaluation {

    private Evaluation eval;

    @BeforeEach
    void setUp() {
        eval = new Evaluation(4); // note initiale
    }

    @Test
    void testConstructeurValide() {
        assertEquals(4, eval.getNote());
        assertNull(eval.getCommentaire());
    }

    @Test
    void testConstructeurInvalide() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Evaluation(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Evaluation(6);
        });
    }

    @Test
    void testSetNoteValide() {
        eval.setNote(5);
        assertEquals(5, eval.getNote());

        eval.setNote(0);
        assertEquals(0, eval.getNote());
    }

    @Test
    void testSetNoteInvalide() {
        assertThrows(IllegalArgumentException.class, () -> eval.setNote(-1));
        assertThrows(IllegalArgumentException.class, () -> eval.setNote(10));
    }

    @Test
    void testAddCommentaire() {
        eval.addCommentaire("Très bonne pizza !");
        assertEquals("Très bonne pizza !", eval.getCommentaire());
    }

    @Test
    void testAddCommentaireDeuxFois() {
        eval.addCommentaire("Première note");
        assertThrows(IllegalArgumentException.class, () -> eval.addCommentaire("Deuxième note"));
    }

    @Test
    void testToString() {
        eval.addCommentaire("Top !");
        String str = eval.toString();
        assertTrue(str.contains("note=4"));
        assertTrue(str.contains("commentaire=Top !"));
    }
}
