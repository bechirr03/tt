package services;


import models.reclamation;


import java.sql.SQLException;
import java.util.List;

public interface IReclamation<T> {
    void ajouter_reclamation (T t) throws SQLException;

    void modifier_reclamation(T t) throws SQLException;

    void supprimer_reclamation(int id) throws SQLException;

    List<T> afficher_reclamation() throws SQLException;

    default void TrRec(int id) throws SQLException {

    }

    reclamation GetById_reclamation(int id) throws SQLException ;


}
