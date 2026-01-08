package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import pizzas.Pizza;
import pizzas.Ingredients;

/**
 * Implémentation simple d'InterSauvegarde basée sur la sérialisation Java.
 * ATTENTION : Pizza et Ingredients doivent implémenter Serializable.
 */
public class SauvegardeFichier implements InterSauvegarde {

    private List<Pizza> pizzas;
    private List<Ingredients> ingredients;

    /**
     * Construit un sauvegardeur à partir des listes à sauvegarder.
     * On copie les listes (ArrayList est Serializable) pour découpler des ObservableList.
     */
    public SauvegardeFichier(List<Pizza> pizzas, List<Ingredients> ingredients) {
        this.pizzas = pizzas;
        this.ingredients = ingredients;
    }

    @Override
    public void sauvegarderDonnees(String nomFichier) throws IOException {
        DataStore ds = new DataStore(pizzas, ingredients);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(ds);
        }
    }

    @Override
    public void chargerDonnees(String nomFichier) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            Object obj = ois.readObject();
            if (!(obj instanceof DataStore)) {
                throw new IOException("Fichier de sauvegarde invalide");
            }
            DataStore ds = (DataStore) obj;
            // réinjecter les données dans l'objet
            this.pizzas = ds.getPizzas();
            this.ingredients = ds.getIngredients();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }


    // Getters pour récupérer les listes après chargement
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    /**
     * Data container sérialisable.
     * Toutes les classes contenues doivent être Serializable (Pizza, Ingredients).
     */
    public static class DataStore implements Serializable {
        private static final long serialVersionUID = 1L;

        private List<Pizza> pizzas;
        private List<Ingredients> ingredients;

        public DataStore(List<Pizza> pizzas, List<Ingredients> ingredients) {
            this.pizzas = pizzas;
            this.ingredients = ingredients;
        }

        public List<Pizza> getPizzas() {
            return pizzas;
        }

        public List<Ingredients> getIngredients() {
            return ingredients;
        }
    }
}
