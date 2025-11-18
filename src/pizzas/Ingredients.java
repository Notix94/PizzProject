package pizzas;

public class Ingredients {
  
  String nom;
  double prix;
  boolean isViande;
  boolean isRegional;

  
  
  public Ingredients(String nom, double prix, boolean isViande, boolean isRegional) {
    super();
    this.nom = nom;
    this.prix = prix;
    this.isViande = isViande;
    this.isRegional = isRegional;


  }
  
  public String getNom() {
    return nom;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  public double getPrix() {
    return prix;
  }
  
  public void setPrix(double prix) {
    this.prix = prix;
  }
  
  public boolean isViande() {
    return isViande;
  }
  
  public void setViande(boolean isViande) {
    this.isViande = isViande;
  }

  public boolean isRegional() {
    return isRegional;
  }

  public void setRegional(boolean isRegional) {
    this.isRegional = isRegional;
  }
  
  
  
}
