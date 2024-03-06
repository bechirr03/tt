package services;

import models.Evenement;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void ajouterEvenement(T t) throws SQLException;

    void modifierEvenement(T t) throws SQLException;

    void supprimerEvenement(int id) throws SQLException;

    List<T> afficherEvenement() throws SQLException;

    List<T> chercherEvenement(String ch) throws SQLException;

    default Evenement getbyidEvenement(int id) throws SQLException {
        return null;
    }

    List<String> getallnamesEvenement() throws SQLException;


}
