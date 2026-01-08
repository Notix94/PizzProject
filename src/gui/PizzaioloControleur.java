package gui;






import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import io.SauvegardeFichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pizzas.Ingredients;
import pizzas.Pizza;
import pizzas.TypePizza;

/**
 * Controleur JavaFX de la fenêtre du pizzaïolo.
 *
 * @author Eric Cariou
 */
public class PizzaioloControleur {

	@FXML
	private ChoiceBox<String> choiceBoxTypeIngredient;

	@FXML
	private ChoiceBox<String> choiceBoxTypePizza;

	@FXML
	private ComboBox<String> comboBoxClients;

	@FXML
	private TextField entreeBeneficeClient;

	@FXML
	private TextField entreeBeneficeCommande;

	@FXML
	private TextField entreeBeneficeTotalCommandes;

	@FXML
	private TextField entreeBeneficeTotalPizza;

	@FXML
	private TextField entreeBeneficeUnitairePizza;

	@FXML
	private TextField entreeNbCommandesPizza;

	@FXML
	private TextField entreeNbPizzasClient;

	@FXML
	private TextField entreeNomIngredient;

	@FXML
	private TextField entreeNomPizza;

	@FXML
	private TextField entreeNombreTotalCommandes;

	@FXML
	private TextField entreePhotoPizza;

	@FXML
	private TextField entreePrixIngredient;

	@FXML
	private TextField entreePrixMinimalPizza;

	@FXML
	private TextField entreePrixVentePizza;

	@FXML
	private Label labelListeCommandes;

	@FXML
	private Label labelListeIngredients;

	@FXML
	private Label labelListePizzas;

	@FXML
	private ListView<String> listeCommandes;

	@FXML
	private ListView<Ingredients> listeIngredients;

	@FXML
	private ListView<Pizza> listePizzas;
	//@FXML
	//private ImageView imageViewPizza;

	@FXML
	void actionBoutonAfficherListeTrieePizzas(ActionEvent event) {
	    // Récupère la liste de pizzas (ObservableList ou List)
	    ObservableList<Pizza> pizzas = listePizzas.getItems();

	    // Trie par nom
	    FXCollections.sort(pizzas, Comparator.comparing(Pizza::getNom, String.CASE_INSENSITIVE_ORDER));

	    // Rafraîchit la ListView
	    listePizzas.setItems(pizzas);
	    listePizzas.refresh();
	}

	@FXML
	void actionBoutonAfficherTousIngredients(ActionEvent event) {
		   // Si tu as une liste globale d'ingrédients
	    ObservableList<Ingredients> tousIngredients = FXCollections.observableArrayList(listeIngredients.getItems());

	    // Tri alphabétique
	    FXCollections.sort(tousIngredients, Comparator.comparing(Ingredients::getNom, String.CASE_INSENSITIVE_ORDER));

	    // Met à jour la ListView
	    listeIngredients.setItems(tousIngredients);
	    listeIngredients.refresh();
	}

	@FXML
	void actionBoutonAfficherToutesPizzas(ActionEvent event) {
		 // Si tu as une liste globale de pizzas
	    ObservableList<Pizza> toutesPizzas = FXCollections.observableArrayList(listePizzas.getItems());

	    // Met à jour la ListView
	    listePizzas.setItems(toutesPizzas);
	    listePizzas.refresh();
	}

	@FXML
	void actionBoutonAjouterIngredientPizza(ActionEvent event) {
		Ingredients ingredientSelectionne = listeIngredients.getSelectionModel()
				.getSelectedItem();

		Pizza pizzaSelectionne = listePizzas.getSelectionModel()
				.getSelectedItem();

		if (ingredientSelectionne != null && pizzaSelectionne != null) {
			// Ajouter l'ingrédient à la pizza
			System.out.println(
					"Ajout de l'ingrédient : " + ingredientSelectionne);
			pizzaSelectionne.ajouterIngredient(ingredientSelectionne);
			pizzaSelectionne.calculPrixMin();
			entreePrixMinimalPizza.setText(
					String.format("%.2f", pizzaSelectionne.getPrixMin()));

		} else {
			System.out.println("Aucun ingrédient sélectionné !");
		}
		System.out.println(pizzaSelectionne.getIngredients());
	}

	@FXML
	void actionBoutonCommandesDejaTraitees(ActionEvent event) {

	}

	@FXML
	void actionBoutonCommandesNonTraitees(ActionEvent event) {

	}

	@FXML
	void actionBoutonCommandesTraiteesClient(ActionEvent event) {

	}

	@FXML
	void actionBoutonCreerIngredient(ActionEvent event) {
		String nomIngre = entreeNomIngredient.getText();
		double prix;
		try {
			prix = Double.parseDouble(entreePrixIngredient.getText());
		} catch (NumberFormatException e) {
			System.out.println("Prix invalide");
			return;
		}

		Ingredients ing = new Ingredients(nomIngre, prix);

		for (Ingredients item : listeIngredients.getItems()) {
			if (item.getNom().equals(nomIngre)) {
				return;
			}
		}
		String choixTyp = choiceBoxTypeIngredient.getValue();
		if (choixTyp == null) {
			listeIngredients.getItems().add(ing);
			System.out.println("aucun type");
			return;
		}
		// convert en str type choixbox
		TypePizza type = TypePizza.valueOf(choixTyp.toString());
		ing.interdirePour(type);
		listeIngredients.getItems().add(ing);

	}

	@FXML
	void actionBoutonCreerPizza(ActionEvent event) {
		String nomPizz = entreeNomPizza.getText();
		double prixVenteP;
		String textePrix = entreePrixVentePizza.getText();

		if (textePrix != null && !textePrix.isBlank()) {
			prixVenteP = Double.parseDouble(textePrix);
		} else {
			prixVenteP = 0;
		}

		String choixTyp = choiceBoxTypePizza.getValue();
		if (choixTyp == null)
			return;
		TypePizza type = TypePizza.valueOf(choixTyp);
		Pizza pizz = new Pizza(nomPizz, type);
		for (Pizza item : listePizzas.getItems()) {
			if (item.getNom().equals(nomPizz)) {
				return;
			}
		}
		// appel func ajtIngre

		listePizzas.getItems().add(pizz);
	}

	@FXML
	void actionBoutonInterdireIngredient(ActionEvent event) {
	    // Récupère l'ingrédient sélectionné
	    Ingredients ingre = listeIngredients.getSelectionModel().getSelectedItem();
	    if (ingre == null) {
	        System.out.println("Aucun ingrédient sélectionné !");
	        return;
	    }

	    // Récupère le type choisi dans la ChoiceBox
	    String choixTyp = choiceBoxTypeIngredient.getValue();
	    if (choixTyp == null) {
	        System.out.println("Aucun type sélectionné !");
	        return;
	    }

	    // Convertit en TypePizza directement
	    TypePizza type = TypePizza.valueOf(choixTyp);  // OK si le nom est exactement le même
	    ingre.interdirePour(type);

	    // Rafraîchit la ListView pour mettre à jour l'affichage
	    listeIngredients.refresh();

	    System.out.println(ingre.getNom() + " interdit pour " + type);
	}

	@FXML
	void actionBoutonModifierPrixIngredient(ActionEvent event) {

		String nomIngre = entreeNomIngredient.getText();
		double prix;
		try {
			prix = Double.parseDouble(entreePrixIngredient.getText());
		} catch (NumberFormatException e) {
			System.out.println("Prix invalide");
			return;
		}

		// Cherche l'ingrédient existant dans la ListView
		for (int i = 0; i < listeIngredients.getItems().size(); i++) {
			Ingredients item = listeIngredients.getItems().get(i);
			if (item.getNom().equals(nomIngre)) {
				item.setPrix(prix);
				listeIngredients.refresh();
			}
		}
	}

	@FXML
	void actionBoutonModifierPrixPizza(ActionEvent event) {
		String nomPizz = entreeNomPizza.getText();
		double prix;
		try {
			prix = Double.parseDouble(entreePrixVentePizza.getText());
		} catch (NumberFormatException e) {
			System.out.println("Prix invalide");
			return;
		}
		// Cherche pizz existant dans la ListView
		for (int i = 0; i < listePizzas.getItems().size(); i++) {
			Pizza item = listePizzas.getItems().get(i);
			if (item.getNom().equals(nomPizz)) {
				item.setPrixVente(prix);
				listePizzas.refresh();
			}
		}

	}

//	@FXML
//	void actionBoutonParcourirPhotoPizza(ActionEvent event) {
//	    // Ouvre un FileChooser pour sélectionner une image
//	    FileChooser fileChooser = new FileChooser();
//	    fileChooser.setTitle("Choisir une photo de pizza");
//	    fileChooser.getExtensionFilters().add(
//	        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
//	    );
//
//	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	    File file = fileChooser.showOpenDialog(stage);
//
//	    if (file != null) {
//	        // Récupère la pizza sélectionnée dans la ListView
//	        Pizza pizzaSelectionnee = listePizzas.getSelectionModel().getSelectedItem();
//	        if (pizzaSelectionnee != null) {
//	            // Met à jour le chemin de l'image dans l'objet Pizza
//	            pizzaSelectionnee.setPhoto(file.getAbsolutePath());
//
//	            // Affiche l'image dans l'ImageView
//	            Image image = new Image(file.toURI().toString());
//	            imageViewPizza.setImage(image);
//	        } else {
//	            // Aucune pizza sélectionnée
//	            Alert alert = new Alert(Alert.AlertType.WARNING);
//	            alert.setTitle("Aucune pizza sélectionnée");
//	            alert.setHeaderText(null);
//	            alert.setContentText("Veuillez sélectionner une pizza avant de choisir une photo.");
//	            alert.showAndWait();
//	        }
//	    }
//	}
	@FXML
	void actionBoutonSupprimerIngredientPizza(ActionEvent event) {
		Ingredients ingredientSelectionne = listeIngredients.getSelectionModel()
				.getSelectedItem();
		

		Pizza pizzaSelectionne = listePizzas.getSelectionModel()
				.getSelectedItem();

		if (ingredientSelectionne != null && pizzaSelectionne != null) {
			// Ajouter l'ingrédient à la pizza
			System.out.println(
					"Sup de l'ingrédient : " + ingredientSelectionne);
			pizzaSelectionne.supprimerIngredient(ingredientSelectionne);
			pizzaSelectionne.calculPrixMin();
			entreePrixMinimalPizza.setText(
					String.format("%.2f", pizzaSelectionne.getPrixMin()));

		} else {
			System.out.println("Aucun ingrédient sélectionné !");
		}
		System.out.println(pizzaSelectionne.getIngredients());
	}
	

	@FXML
	void actionBoutonVerifierValiditeIngredientsPizza(ActionEvent event) {
		 // Récupère la pizza sélectionnée dans la ListView
	    Pizza pizza = listePizzas.getSelectionModel().getSelectedItem();
	    if (pizza == null) {
	        System.out.println("Aucune pizza sélectionnée !");
	        return;
	    }

	    boolean valide = true;

	    // Parcours tous les ingrédients
	    for (Ingredients ing : pizza.getIngredients()) {
	        if (ing.estInterditPour(pizza.getType())) {
	            System.out.println("Ingrédient interdit détecté : " + ing.getNom() 
	                               + " pour la pizza " + pizza.getNom() 
	                               + " (" + pizza.getType() + ")");
	            valide = false;
	        }
	    }

	    if (valide) {
	        System.out.println("Tous les ingrédients sont valides pour " + pizza.getNom());
	    } else {
	        System.out.println("Attention : certains ingrédients sont interdits !");
	    }
	}

	@FXML
	void actionListeSelectionCommande(MouseEvent event) {

	}

	@FXML
	void actionListeSelectionIngredient(MouseEvent event) {
		 Ingredients ing = listeIngredients.getSelectionModel().getSelectedItem();
		    if (ing == null) return;

		    // Remplit les champs
		    entreeNomIngredient.setText(ing.getNom());
		    entreePrixIngredient.setText(String.format("%.2f", ing.getPrix()));

		    // Cherche un type interdit
		    String typeInterdit = null;
		    for (TypePizza t : TypePizza.values()) {
		        if (ing.estInterditPour(t)) {
		            typeInterdit = t.name(); // DOIT correspondre aux String de la ChoiceBox
		            break;
		        }
		    }

		    choiceBoxTypeIngredient.setValue(typeInterdit); // null si aucun interdit
	}

	@FXML
	void actionListeSelectionPizza(MouseEvent event) {
		 Pizza pizza = listePizzas.getSelectionModel().getSelectedItem();
		    if (pizza == null) return;

		    // Champs texte
		    entreeNomPizza.setText(pizza.getNom());
		    entreePrixVentePizza.setText(String.format("%.2f", pizza.getPrixVente()));

		    pizza.calculPrixMin();
		    entreePrixMinimalPizza.setText(String.format("%.2f", pizza.getPrixMin()));

		    // Type de pizza (String)
		    choiceBoxTypePizza.setValue(pizza.getType().name());

		    // Sélection des ingrédients de la pizza
		    listeIngredients.getSelectionModel().clearSelection();
		    for (Ingredients ing : pizza.getIngredients()) {
		        for (int i = 0; i < listeIngredients.getItems().size(); i++) {
		            Ingredients candidate = listeIngredients.getItems().get(i);
		            if (candidate.getNom().equalsIgnoreCase(ing.getNom())) {
		                listeIngredients.getSelectionModel().select(i);
		                break;
		            }
		        }
		    }
	}

	@FXML
	void actionMenuApropos(ActionEvent event) {
		  Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("À propos");
		    alert.setHeaderText("Application de gestion de pizzas");
		    alert.setContentText(
		        "Conception d’applications – L3 Informatique – UBO\n\n"
		      + "Application de création et commande de pizzas.\n"
		      + "Rôles : pizzaïolo et clients.\n\n"
		      + "Année universitaire 2025–2026"
		    );

		    alert.showAndWait();
	}

	@FXML
	void actionMenuCharger(ActionEvent event) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Charger les données");
	    fileChooser.getExtensionFilters().add(
	        new FileChooser.ExtensionFilter("Sauvegarde (.dat)", "*.dat")
	    );

	    // Récupération du Stage via une Node existante (ici listePizzas)
	    Stage stage = (Stage) listePizzas.getScene().getWindow();

	    File file = fileChooser.showOpenDialog(stage);
	    if (file == null) return;

	    try {
	        // Crée un SauvegardeFichier temporaire avec des listes vides
	        SauvegardeFichier sauvegarde = new SauvegardeFichier(new ArrayList<>(), new ArrayList<>());

	        // Charge les données dans l'objet
	        sauvegarde.chargerDonnees(file.getAbsolutePath());

	        // Met à jour les ListView avec les listes chargées
	        listePizzas.setItems(FXCollections.observableArrayList(sauvegarde.getPizzas()));
	        listeIngredients.setItems(FXCollections.observableArrayList(sauvegarde.getIngredients()));

	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Chargement");
	        alert.setHeaderText(null);
	        alert.setContentText("Chargement réussi !");
	        alert.showAndWait();

	    } catch (IOException e) {
	        e.printStackTrace();
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Erreur de chargement");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
	    }
	}

	@FXML
	void actionMenuQuitter(ActionEvent event) {
	    // Ferme la fenêtre principale
		   javafx.application.Platform.exit();  // quitte proprement l'application JavaFX
	}

	@FXML
	void actionMenuSauvegarder(ActionEvent event) {
	    javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
	    fileChooser.setTitle("Sauvegarder les données");
	    fileChooser.getExtensionFilters().add(
	        new javafx.stage.FileChooser.ExtensionFilter("Sauvegarde (.dat)", "*.dat")
	    );

	    // Récupération correcte du Stage
	    javafx.stage.Stage stage = (javafx.stage.Stage) listePizzas.getScene().getWindow();

	    java.io.File file = fileChooser.showSaveDialog(stage);
	    if (file == null) return;

	    java.util.List<Pizza> pizzasACopier = new java.util.ArrayList<>(listePizzas.getItems());
	    java.util.List<Ingredients> ingredientsACopier = new java.util.ArrayList<>(listeIngredients.getItems());

	    SauvegardeFichier sauvegarde = new SauvegardeFichier(pizzasACopier, ingredientsACopier);

	    try {
	        sauvegarde.sauvegarderDonnees(file.getAbsolutePath());

	        javafx.scene.control.Alert alert =
	                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
	        alert.setTitle("Sauvegarde");
	        alert.setHeaderText(null);
	        alert.setContentText("Sauvegarde réussie !");
	        alert.showAndWait();

	    } catch (java.io.IOException e) {
	        e.printStackTrace();

	        javafx.scene.control.Alert alert =
	                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Erreur de sauvegarde");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
	    }
	}


	@FXML
	void actionSelectionClient(ActionEvent event) {

	}

	@FXML
	void initialize() {

		choiceBoxTypeIngredient.getItems().addAll("Viande", "Vegetarienne",
				"Regionale");
		choiceBoxTypeIngredient.setValue(null);

		choiceBoxTypePizza.getItems().addAll(" ", "Viande", "Vegetarienne",
				"Regionale");

		choiceBoxTypePizza.setValue(" "); // valeur par défaut

	}

}
