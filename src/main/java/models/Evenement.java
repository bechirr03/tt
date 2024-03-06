package models;

public class Evenement {

    private int id;
    private String nom,description,image ;
    private Categorie categorie;
    private float nbr;



    public Evenement() {
    }

    public Evenement(String nom, Categorie categorie, String description, String image, float nbr) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.nbr = nbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getnbr() {
        return nbr;
    }

    public void setnbr(float nbr) {
        this.nbr = nbr;
    }

    public float getNbr() {
        return nbr;
    }

    public void setNbr(float nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", description=" + description + ", image=" + image + ", nbr=" + nbr + '}';
    }


}
