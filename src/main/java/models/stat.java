
package models;


public class stat {
    int nbr;
    String nom;

    public stat() {
    }

    public stat(int nbr, String nom) {
        this.nbr = nbr;
        this.nom = nom;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
