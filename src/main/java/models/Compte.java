package models;

import java.util.Objects;

public class Compte {
    private  int id=0;
    private String email;
    private String motDePasse;
    private int CIN;
    private float score;
    private float money;

    public Compte(int CIN ,String email, String motDePasse ) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.CIN = CIN;
    }

    public Compte() {
    }

    public Compte( String email, String motDePasse, int CIN, float score, float money) {
        this.CIN = CIN;
        this.email = email;
        this.motDePasse = motDePasse;
        this.score = score;
        this.money = money;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte compte)) return false;
        return getId() == compte.getId() && Float.compare(getScore(), compte.getScore()) == 0 && Float.compare(getMoney(), compte.getMoney()) == 0 && Objects.equals(getEmail(), compte.getEmail()) && Objects.equals(getMotDePasse(), compte.getMotDePasse()) && getCIN()==compte.getCIN();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getMotDePasse(), getCIN(), getScore(), getMoney());
    }


}
