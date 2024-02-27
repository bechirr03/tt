package services;

import models.Categorie;

import java.sql.SQLException;
import java.util.List;

public interface ICategorie<T> {
    void ajouterCategorie(T t) throws SQLException;

    void modifierCategorie(T t) throws SQLException;

    void supprimerCategorie(int id) throws SQLException;

    List<T> afficherCategorie() throws SQLException;

    List<String> getallnamesCategorie() throws SQLException;

    Categorie getbyidCategorie(T t) throws SQLException;


    Categorie getbyid(int id);
}
