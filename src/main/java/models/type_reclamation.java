
package models;


public class type_reclamation {
    private int id;
    private String nom;

    public type_reclamation(int id) {
        this.id = id;
    }

    public type_reclamation() {
    }
    

    public type_reclamation(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "type_reclamation{" + "id=" + id + ", nom=" + nom + "\n";
    }
    
    
}
