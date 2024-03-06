package models;

import java.util.Objects;

public class User {
    private int CIN;
    private int age ;
    private String nom;
    private String prenom;
    private int numbre;
    private String email;
    private String role;


    public User() {
    }

    public User(int CIN,  String nom, String prenom  , String email ,int numbre) {

        this.CIN = CIN;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.numbre = numbre;

    }

    public User(int i, String text, String text1) {
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }



    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumbre() {
        return numbre;
    }

    public void setNumbre(int numbre) {
        this.numbre = numbre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "CIN=" + CIN +
                ", age=" + age +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numbre=" + numbre +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getCIN() == user.getCIN()  && getNumbre() == user.getNumbre() && Objects.equals(getNom(), user.getNom()) && Objects.equals(getPrenom(), user.getPrenom()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCIN(),  getNom(), getPrenom(), getNumbre(), getEmail());
    }


}

