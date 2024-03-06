package models;
import java.util.Objects;

public class Session {
    private User user;
    private Compte compte;

    public Session() {
    }

    public Session(User user, Compte compte) {
        this.user = user;
        this.compte = compte;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session session)) return false;
        return Objects.equals(getUser(), session.getUser()) && Objects.equals(getCompte(), session.getCompte());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCompte());
    }

    @Override
    public String toString() {
        return "Session{" +
                "user=" + user +
                ", compte=" + compte +
                '}';
    }
}
