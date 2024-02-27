
package models;

import models.User;
import java.sql.Timestamp;
import java.text.DateFormat;


public class reclamation {
     private int id;
    private User id_user_id;
    private type_reclamation id_tr_id;
    private Timestamp date;
    private String email;
    private int telephone;
    private String cmnt;
    private String etat;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(User id_user_id) {
        this.id_user_id = id_user_id;
    }

    public type_reclamation getId_tr_id() {
        return id_tr_id;
    }

    public void setId_tr_id(type_reclamation id_tr_id) {
        this.id_tr_id = id_tr_id;
    }
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getCmnt() {
        return cmnt;
    }

    public void setCmnt(String cmnt) {
        this.cmnt = cmnt;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    public reclamation(int id, User id_user_id, type_reclamation id_tr_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.id = id;
        this.id_user_id = id_user_id;
        this.id_tr_id = id_tr_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }
        public reclamation(User id_user_id, type_reclamation id_tr_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.id_user_id = id_user_id;
        this.id_tr_id = id_tr_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }
    public reclamation(){
    }

    public reclamation(int id, User id_user_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.id = id;
        this.id_user_id = id_user_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }

    public reclamation( User id_user_id, type_reclamation id_tr_id, String email, int telephone, String cmnt) {
        this.id_user_id = id_user_id;
        this.id_tr_id = id_tr_id;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", id_user_id=" + id_user_id + ", id_tr_id=" + id_tr_id + ", date=" + date + ", email=" + email + ", telephone=" + telephone + ", cmnt=" + cmnt + ", etat=" + etat + "\n";
    }

public Timestamp getCurrentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
}

    
}
