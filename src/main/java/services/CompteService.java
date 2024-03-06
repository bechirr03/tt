package services;

import models.Compte;
import utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteService implements IService_user<Compte>{

        private Connection connection;
        public CompteService(){
            connection = MyDatabase.getInstance().getConnection();
        }
        @Override
        public void ajouter(Compte compte) throws SQLException {
            String sql = "insert into Compte (id , email , motDePasse , CIN , score , money) " +
                    "values('" + compte.getId() + "' , '" + compte.getEmail() + "','" + compte.getMotDePasse() + "',"
                    +  compte.getCIN() + ",'" + compte.getScore() + "'," + compte.getMoney() + ")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }

        @Override
        public void modifier(Compte compte ) throws SQLException {
            String sql = "update compte set id =? ,email = ?, motDePasse = ?, CIN = ?, score = ?, money = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, compte.getId());
            preparedStatement.setString(2, compte.getEmail());
            preparedStatement.setString(3, compte.getMotDePasse());
            preparedStatement.setInt(4,compte.getCIN());
            preparedStatement.setFloat(5,compte.getScore());
            preparedStatement.setFloat(6,compte.getMoney());


            preparedStatement.executeUpdate();

        }

        @Override
        public void supprimer(int CIN) throws SQLException {
            String req = "DELETE FROM `compte` WHERE CIN=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,CIN);
            preparedStatement.executeUpdate();
        }

        @Override
        public List<Compte> recuperer() throws SQLException {
            String sql = "select * from compte";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            List<Compte> list = new ArrayList<>();
            while (rs.next()){
                Compte c = new Compte();
                c.setId(rs.getInt("Id"));
                c.setEmail(rs.getString("Email"));
                c.setMotDePasse(rs.getString("Mot de passe"));
                c.setCIN(rs.getInt("CIN"));
                c.setScore(rs.getFloat("Score"));
                c.setMoney(rs.getFloat("Money"));

                list.add(c);
            }
            return list;
        }

    public int exist (String email, String password) throws SQLException {
        String query = "SELECT COUNT(*) FROM compte WHERE email = ? AND motDePasse = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count;
    }

    public Compte getCompte(String email, String password) throws SQLException {
        String query = "SELECT * FROM compte WHERE email = ? AND motDePasse = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int cin = resultSet.getInt("CIN");
            String emailCompte = resultSet.getString("email");
            String motDePasse = resultSet.getString("motDePasse");
         return new Compte(cin,emailCompte,motDePasse);
        }
        return null;
    }


    }
