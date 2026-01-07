package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

	@FXML
	void actionBoutonAfficherListeTrieePizzas(ActionEvent event) {

	}

	@FXML
	void actionBoutonAfficherTousIngredients(ActionEvent event) {

	}

	@FXML
	void actionBoutonAfficherToutesPizzas(ActionEvent event) {

	}

	@FXML
	void actionBoutonAjouterIngredientPizza(ActionEvent event) {
		Ingredients ingredientSelectionne = listeIngredients.getSelectionModel()
				.getSelectedItem();
		int index = listePizzas.getSelectionModel().getSelectedIndex();
		
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
		//convert en str type choixbox
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
		// appel func ajtIngre

		listePizzas.getItems().add(pizz);
	}

	@FXML
	void actionBoutonInterdireIngredient(ActionEvent event) {

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
		Pizza pizzaSelectionne = listePizzas.getSelectionModel()
				.getSelectedItem();
		if (pizzaSelectionne == null) {
			System.out.println("select une Pizza stp");
			return;
		}
		entreeNomPizza.setText(pizzaSelectionne.getNom());
		choiceBoxTypePizza.setValue(pizzaSelectionne.getType().name());
		entreePrixMinimalPizza
				.setText(String.format("%.2f", pizzaSelectionne.getPrixMin()));
		listePizzas.refresh();
	}

	@FXML
	void actionBoutonParcourirPhotoPizza(ActionEvent event) {

	}

	@FXML
	void actionBoutonSupprimerIngredientPizza(ActionEvent event) {

	}

	@FXML
	void actionBoutonVerifierValiditeIngredientsPizza(ActionEvent event) {

	}

	@FXML
	void actionListeSelectionCommande(MouseEvent event) {

	}

	@FXML
	void actionListeSelectionIngredient(MouseEvent event) {

	}

	@FXML
	void actionListeSelectionPizza(MouseEvent event) {

	}

	@FXML
	void actionMenuApropos(ActionEvent event) {

	}

	@FXML
	void actionMenuCharger(ActionEvent event) {

	}

	@FXML
	void actionMenuQuitter(ActionEvent event) {

	}

	@FXML
	void actionMenuSauvegarder(ActionEvent event) {

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
