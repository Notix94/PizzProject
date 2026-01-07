package pizzas;

/**
 * Représente une évaluation d'un client ou d'une commande.
 * 
 * <p>
 * Une évaluation contient une note entre 0 et 5 et un commentaire optionnel.
 * </p>
 * 
 *
 * 
 * @author Ewan
 * @version 1.0
 */
public class Evaluation {
	private int note;
	private String commentaire;

	/**
	 * Constructeur.
	 * 
	 * @param note
	 *            La note de l'évaluation (doit être entre 0 et 5)
	 * @throws IllegalArgumentException
	 *             si la note est hors de la plage autorisée
	 */
	public Evaluation(int note) {

		setNote(note); // vérifie la note et l’affecte

	}

	/**
	 * Ajoute un commentaire à l'évaluation. On ne peut ajouter qu'un seul
	 * commentaire.
	 * 
	 * @param commentaire
	 *            Le commentaire à ajouter
	 * @throws IllegalArgumentException
	 *             si un commentaire existe déjà
	 */

	public void addCommentaire(String commentaire) {
		if (this.commentaire != null) {
			throw new IllegalArgumentException(
					"On ne peut mettre un 2eme commentaire dans une eval");
		}
		this.commentaire = commentaire;
	}

	public int getNote() {
		return note;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setNote(int note) {
		if (note < 0 || note > 5) {
			throw new IllegalArgumentException(
					"La note doit être entre 0 et 5.");
		}
		this.note = note;
	}
	@Override
	public String toString() {
		return "Evaluation[note=" + note + ", commentaire=" + commentaire + "]";
	}
}
