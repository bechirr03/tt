package services;
import models.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService_user<User>{

    private Connection connection;
    public UserService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(User user) throws SQLException {
        String sql = "insert into user (CIN , nom , prenom , email , numbre) " +
                "values('" + user.getCIN() + "', '" + user.getNom() + "', '" + user.getPrenom() + "', '"
                + user.getEmail() + "', " + user.getNumbre() + ")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(User user) throws SQLException {
        String sql = "update user set CIN = ? ,nom = ?, prenom = ?, email = ?, numbre = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getCIN());
        preparedStatement.setString(2, user.getNom());
        preparedStatement.setString(3, user.getPrenom());
        preparedStatement.setString(4,user.getEmail());
        preparedStatement.setLong(5,user.getNumbre());

        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int CIN) throws SQLException {
        String req = "DELETE FROM `user` WHERE CIN=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1,CIN);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> recuperer() throws SQLException {
        String sql = "select * from user";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);
        List<User> list = new ArrayList<>();
        while (rs.next()){
            User p = new User();
            p.setCIN(rs.getInt("CIN"));
            p.setNom(rs.getString("Nom"));
            p.setPrenom(rs.getString("Prenom"));
            p.setEmail(rs.getString("Email"));
            p.setNumbre(rs.getInt("Numbre"));
            list.add(p);
        }
        return list;
    }

    public int chercher(String cin) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE CIN = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(cin));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count;
    }

    public User getUser(String email) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int cin = resultSet.getInt("CIN");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String emailUser = resultSet.getString("email");
            int numberUser = resultSet.getInt("numbre");
            return new User (cin,nom , prenom, emailUser , numberUser );
        }
        return null;
    }
}
