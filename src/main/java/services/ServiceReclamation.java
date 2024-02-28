package services;


import models.reclamation;
import models.type_reclamation;
import models.Evenement;
import utils.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceReclamation implements IReclamation<reclamation> {
    Connection cnx = MyDatabase.getInstance().getConnection();
    List<type_reclamation> type_reclamation = new ArrayList();
    public final String SELECT_type_BY_ID = "SELECT * FROM type_reclamation WHERE id = ? LIMIT 1";
    public final String SELECT_evenement_BY_ID = "SELECT * FROM evenement WHERE id = ? LIMIT 1";

    public ServiceReclamation() {
    }

    public void ajouter_reclamation(reclamation r) {
        try {
            String qry = "INSERT INTO `reclamation`( `id_evenement_id`,`id_tr_id`,`date`,`email`,`telephone`,`cmnt`,`etat`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = this.cnx.prepareStatement(qry);
            ps.setInt(1, r.getId_evenement_id().getId());
            ps.setInt(2, r.getId_tr_id().getId());
            ps.setTimestamp(3, r.getCurrentTimestamp());
            ps.setString(4, r.getEmail());
            ps.setInt(5, r.getTelephone());
            ps.setString(6, r.getCmnt());
            ps.setString(7, r.getEtat());
            ps.executeUpdate();
            System.out.println("succès ");
        } catch (SQLException var4) {
            System.out.println("erreur ");
            System.out.println(var4.getMessage());
        }

    }

    public List<reclamation> afficher_reclamation() {
        List<reclamation> reclamation = new ArrayList();

        try {
            String req = "SELECT * FROM `reclamation` ";
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                reclamation r = new reclamation();
                r.setId(rs.getInt(1));
                r.setId_evenement_id(this.getevenementById(rs.getInt(2)));
                r.setId_tr_id(this.gettypeById(rs.getInt(3)));
                r.setDate(rs.getTimestamp(4));
                r.setEmail(rs.getString(5));
                r.setTelephone(rs.getInt(6));
                r.setCmnt(rs.getString(7));
                r.setEtat(rs.getString(8));
                reclamation.add(r);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return reclamation;
    }

    public type_reclamation gettypeById(int id) {
        type_reclamation type = new type_reclamation();

        try {
            PreparedStatement st = this.cnx.prepareStatement("SELECT * FROM type_reclamation WHERE id = ? LIMIT 1");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                type.setId(rs.getInt("id"));
                type.setNom(rs.getString("nom"));
            }
        } catch (SQLException | NullPointerException | IllegalArgumentException var5) {
            System.out.println(var5.getMessage());
        }

        return type;
    }

    public Evenement getevenementById(int id) {
        Evenement ur = new Evenement();

        try {
            PreparedStatement st = this.cnx.prepareStatement("SELECT * FROM evenement WHERE id = ? LIMIT 1");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                ur.setId(rs.getInt("id"));
                ur.setNom(rs.getString("nom"));
            }
        } catch (SQLException | IllegalArgumentException | NullPointerException var5) {
            System.out.println(var5.getMessage());
        }

        return ur;
    }

    public void modifier_reclamation(reclamation r) {
        try {
            String req = " UPDATE `reclamation` SET `id` = '" + r.getId() + "',  `Id_tr_id` = '" + r.getId_tr_id() + "', `Date` = '" + r.getDate() + "', `Email` = '" + r.getEmail() + "', `Telephone` = '" + r.getTelephone() + "', `cmnt` = '" + r.getCmnt() + "', `etat` = '" + r.getEtat() + "' WHERE `reclamation`.`id` = " + r.getId();
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" reclamation updated!");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public void supprimer_reclamation(int id) {
        try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation supprimée !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public reclamation GetById_reclamation(int id) {
        reclamation r = new reclamation();

        try {
            String req = "SELECT * FROM `reclamation` WHERE id = " + id;
            Statement st = this.cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                r.setId(rs.getInt(1));
                r.setId_evenement_id(this.getevenementById(rs.getInt(2)));
                r.setId_tr_id(this.gettypeById(rs.getInt(3)));
                r.setDate(rs.getTimestamp(4));
                r.setEmail(rs.getString(5));
                r.setTelephone(rs.getInt(6));
                r.setCmnt(rs.getString(7));
                r.setEtat(rs.getString(8));
            }
        } catch (SQLException var6) {
            System.out.println("ahla");
        }

        return r;
    }

    public void TrRec(int id) {
        String req = "UPDATE `reclamation` SET `etat` = 'Reclamation Traité' WHERE `reclamation`.`id` =" + id;

        try {
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" reclamation traite!");
        } catch (SQLException var4) {
            System.out.println("ya");
        }

    }
}
