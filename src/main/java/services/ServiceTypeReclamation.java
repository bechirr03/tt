package services;

import java.util.ArrayList;
import java.util.List;

import models.type_reclamation;
        import utils.MyDatabase;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class ServiceTypeReclamation implements ITypeReclamationn<type_reclamation> {
    Connection cnx = MyDatabase.getInstance().getConnection();

    public ServiceTypeReclamation() {
    }

    public void ajouter_type_reclamation(type_reclamation t) {
        try {
            String qry = "INSERT INTO `type_reclamation`( `nom`) VALUES (?)";
            PreparedStatement ps = this.cnx.prepareStatement(qry);
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("ajout type reclamation réussit ");
        } catch (SQLException var4) {
            System.out.println("erreur d'ajout type reclamation");
            System.out.println(var4.getMessage());
        }

    }

    public List<type_reclamation> afficher_type_reclamation() {
        List<type_reclamation> type_reclamation = new ArrayList();

        try {
            String req = "SELECT * FROM `type_reclamation` ";
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                type_reclamation t = new type_reclamation();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString("nom"));
                type_reclamation.add(t);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return type_reclamation;
    }

    public void modifier_type_reclamation(type_reclamation t) {
        try {
            String req = " UPDATE `type_reclamation` SET `id` = '" + t.getId() + "', `Nom` = '" + t.getNom() + "' WHERE `type_reclamation`.`id` = " + t.getId();
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("type reclamation updated!");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public void supprimer_type_reclamation(int id) {
        try {
            String req = "DELETE FROM `type_reclamation` WHERE id = " + id;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("type reclamation deleted !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<String> getallnames_type_reclamation() {
        List<String> l = new ArrayList();

        try {
            String req = "SELECT nom FROM `type_reclamation` ";
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                l.add(rs.getString("nom"));
            }
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

        return l;
    }
}
