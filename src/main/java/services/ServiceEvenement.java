package services;

import models.Categorie;
import models.Evenement;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvenement implements IService<Evenement> {
    Connection cnx = MyDatabase.getInstance().getConnection();
    ServiceCategorie sc = new ServiceCategorie();

    public ServiceEvenement() {Connection cnx =MyDatabase.getInstance().getConnection();
    }

  /*  public void ajouterEvenement(Evenement t) {
        try {

            String qry = "INSERT INTO `evenement`( nom, description, image, nbr, categorie) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.cnx.prepareStatement(qry);
            String img = "imgs\\imgs\\" + t.getImage();
            ps.setString(1, t.getNom());
            int id = t.getCategorie().getId();
            ps.setInt(2, id);
            ps.setString(3, t.getDescription());
            ps.setString(4, img);
            ps.setFloat(5, t.getnbr());
            System.out.println(qry);
            ps.executeUpdate();
            System.out.println("haw ytzed ");
        } catch (SQLException var6) {
            System.out.println("yrawa7 evement ");
            System.out.println(var6.getMessage());
        }

    }*/

    public void ajouterEvenement(Evenement t) {
        try {
            String qry = "INSERT INTO `evenement`( nom, description, image, nbr, categorie) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.cnx.prepareStatement(qry);
            String img = "imgs\\imgs\\" + t.getImage();
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDescription());
            ps.setString(3, img);
            ps.setFloat(4, t.getnbr());
            int id = t.getCategorie().getId();
            ps.setInt(5, id);
            System.out.println(qry);
            ps.executeUpdate();
            System.out.println("haw ytzed ");
        } catch (SQLException var6) {
            System.out.println("yrawa7 evement ");
            System.out.println(var6.getMessage());
        }
    }


  /*  public List<Evenement> afficherEvenement() throws SQLException {
        List<Evenement> evenements = new ArrayList();


            String req = "SELECT * FROM evenement ";
            Statement st;
            st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Evenement m = new Evenement();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(3));
                m.setDescription(rs.getString(4));
                int id = rs.getInt(2);
                Categorie c = this.sc.getbyid(id);
                m.setCategorie(c);
                m.setImage(rs.getString(5));
                m.setnbr(rs.getFloat(6));
                evenements.add(m);
            }


        return evenements;
    }
*/
  public List<Evenement> afficherEvenement() throws SQLException {
      String sql = "SELECT * FROM evenement";
      Statement statement = cnx.createStatement();
      ResultSet rs = statement.executeQuery(sql);
      List<Evenement> evenements = new ArrayList<>();
      while (rs.next()) {
          Evenement m = new Evenement();
          m.setId(rs.getInt("id"));
          m.setNom(rs.getString("nom"));
          m.setDescription(rs.getString("description"));
          int id = rs.getInt("categorie");
          Categorie c = sc.getbyid(id);
          m.setCategorie(c);
          m.setImage(rs.getString("image"));
          m.setnbr(rs.getFloat("nbr"));
          evenements.add(m);
      }
      return evenements;
  }

    public void modifierEvenement(Evenement t) {
        try {
            String req = " UPDATE evenement SET id = '" + t.getId() + "', Nom = '" + t.getNom() + " ', Categorie = ' " + t.getCategorie() + " ', Description = ' " + t.getDescription() + " ', nbr = ' " + t.getnbr() + "' WHERE evenement.`id` = " + t.getId();
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement updated!");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = " + id;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement deleted !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Evenement> chercherEvenement(String ch) {
        List<Evenement> evenements = new ArrayList();

        try {
            String req = "SELECT * FROM evenement where Nom LIKE '" + ch + "%' ";
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Evenement m = new Evenement();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(3));
                m.setDescription(rs.getString(4));
                int id = rs.getInt(2);
                Categorie c = this.sc.getbyid(id);
                m.setCategorie(c);
                m.setImage(rs.getString(5));
                m.setnbr(rs.getFloat(6));
                evenements.add(m);
            }
        } catch (SQLException var9) {
            System.out.println(var9.getMessage());
        }

        return evenements;
    }

    public Evenement getbyidEvenement(int id) {
        Evenement m = new Evenement();

        try {
            String req = "SELECT * FROM evenement where id = '" + id + "%' ";
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setDescription(rs.getString(3));
                int aaa = rs.getInt(6);
                Categorie c = this.sc.getbyid(aaa);
                m.setCategorie(c);
                m.setImage(rs.getString(4));
                m.setnbr(rs.getFloat(5));
            }
        } catch (SQLException var8) {
            System.out.println(var8.getMessage());
        }

        return m;
    }

    public List<String> getallnamesEvenement() {
        List<String> categories = new ArrayList<>();

        try {
            String req ="SELECT nom FROM evenement ";

            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                String n=(rs.getString("nom"));
                categories.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categories;
    }


}
