package pizzas;

// A compléter
public class Evaluation {
int note;
String commentaire;
	public Evaluation(int note) {
		if(this.note >5 || this.note <0) {
			throw new IllegalArgumentException("La note doit être entre 0 et 5.");
		}
		this.note = note;
		
	}
	public void addCommentaire(String commentaire) {
		if(this.commentaire != null) {
			throw new IllegalArgumentException("On ne peut mettre un 2eme commentaire dans une eval");
		}
		this.commentaire = commentaire;
	}
}
