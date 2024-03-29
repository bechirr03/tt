package services;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public interface ITypeReclamation<T> {


    void ajouter_type_reclamation(T t) throws SQLException;
    List<T> afficher_type_reclamation() throws SQLException;
    void modifier_type_reclamation(T t) throws SQLException;
    void supprimer_type_reclamation(int id) throws SQLException;
    List<String> getallnames_type_reclamation() throws SQLException;

    void initialize(URL url, ResourceBundle rb);
}
