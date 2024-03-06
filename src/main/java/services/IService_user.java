package services;

import java.sql.SQLException;
import java.util.List;

public interface IService_user<T>  {
    void ajouter(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int CIN) throws SQLException;
    List<T> recuperer() throws SQLException;
}
