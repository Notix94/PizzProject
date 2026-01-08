package pizzas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Représente une pizza de la pizzeria.
 * 
 * <p>
 * Une pizza possède un nom, un type (Classique, Vegetarienne, Regionale), et
 * une liste d'ingrédients. Le prix de la pizza est calculé à partir du prix de
 * ses ingrédients avec un coefficient.
 * </p>
 * 
 * 
 * 
 * @author Ewan
 * @version 1.0
 */
public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;
	private String nom;
	private TypePizza type;
	private List<Ingredients> ingredients;
	private double prixMin;
	private double prixVente;
	private String photo;
	private List<Evaluation> eval;
	/**
	 * Constructeur principal.
	 * 
	 * @param nom
	 *            Nom de la pizza
	 * @param type
	 *            Type de la pizza
	 * @param ingredients
	 *            Liste initiale d'ingrédients
	 */
	public Pizza(String nom, TypePizza type) {
		this.nom = nom;
		this.type = type;
		this.ingredients = new ArrayList<>();
		this.eval = new ArrayList<>();
		this.prixMin = 0.0;
		this.prixVente = 0.0;
		this.photo = "";
	}

	public String getNom() {
		return nom;
	}
	public TypePizza getType() {
		return type;
	}
	public List<Ingredients> getIngredients() {
		return new ArrayList<>(ingredients);
	}
	
	public double getPrixVente() {
		return prixVente;
	}
	public double getPrixMin() {
		return prixMin;
	}
	// Récupérer toutes les évaluations
	public List<Evaluation> getEvaluations() {
	    return new ArrayList<>(eval); // retourne une copie pour ne pas exposer la liste interne
	}
	public double getNoteMoyenne() {
		if (eval.isEmpty())
			return 0.0;
		double somme = 0.0;
		for (Evaluation e : eval) {
			somme += e.getNote();
		}
		return somme / eval.size();
	}
	/**
	 * 
	 * <p>
	 * empeche de mettre prix infe a prix min
	 * </p>
	 * 
	 * 
	 **/
	public void setPrixVente(double prix) {
		if (prix >= prixMin) {
			prixVente = prix;
			
		}
		else {
			prixVente = prixMin;
		}
	}
	
	/**
	 * Ajoute un ingrédient à la pizza en respectant les règles. :
	 * <ul>
	 * <li>Pizza végétarienne : interdit les ingrédients carnés</li>
	 * <li>Pizza régionale : interdit les ingrédients non régionaux</li>
	 * </ul>
	 * 
	 * @param ingredient
	 *            L'ingrédient à ajouter
	 */
	public boolean ajouterIngredient(Ingredients ingre) {

		if (ingre == null) {
			System.out.println("Pas de nom valide");
			return false;
		}
		if (type == TypePizza.Vegetarienne) {
			String nomIng = ingre.getNom().toLowerCase();
			if (nomIng == "jambon" || nomIng == "boeuf") {
				System.out.println("Viande interdite dans vege");
				return false; 
			}
		}
		this.ingredients.add(ingre);
		calculPrixMin(); // recalcul automatique
		return true;
	}
	/**
	 * Supprime un ingrédient de la pizza.
	 * 
	 * @param ingre L'ingrédient à supprimer
	 * @return true si l'ingrédient a été supprimé, false sinon
	 */
	public boolean supprimerIngredient(Ingredients ingre) {
	    if (ingre == null) {
	        System.out.println("Pas d'ingrédient valide à supprimer");
	        return false;
	    }

	    // Vérifie si l'ingrédient est dans la liste
	    if (this.ingredients.contains(ingre)) {
	        this.ingredients.remove(ingre);
	        calculPrixMin(); // recalcul automatique du prix minimum
	        System.out.println(ingre.getNom() + " supprimé de la pizza");
	        return true;
	    } else {
	        System.out.println(ingre.getNom() + " n'est pas dans la pizza");
	        return false;
	    }
	}
	/**
	 * Calcule le prix de la pizza en fonction des prix des ingrédients.
	 * 
	 * <p>
	 * Chaque ingrédient est multiplié par un coefficient de 1.4 et le résultat
	 * est arrondi au dixième.
	 * </p>
	 * 
	 * @return prixMin
	 */
	public double calculPrixMin() {
		double prixIngr = 0.00;
		for (Ingredients ingr : ingredients) {

			prixIngr += ingr.getPrix() * 1.40;

		}
		// Arrondi à la dizaine de centimes sup
		prixMin = Math.ceil(prixIngr * 10) / 10.0;

		if (prixMin > prixVente) {
			prixVente = prixMin;
		}
		return prixMin;
	}

	public void ajouterEvaluation(Evaluation eval) {
		if (eval != null) {
			this.eval.add(eval);
		}
	}
    // getter et setter pour la photo
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
	@Override
	public String toString() {
	    return nom + " (" + type + ")"+ String.format("%.2f €", prixVente);
	}


}
