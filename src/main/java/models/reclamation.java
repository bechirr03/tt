
package models;

import models.Evenement;
import java.sql.Timestamp;
import java.text.DateFormat;


public class reclamation {
     private int id;
    private Evenement Id_evenement_id;
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

    public Evenement getId_evenement_id() {
        return Id_evenement_id;
    }

    public void setId_evenement_id(Evenement id_evenement_id) {
        Id_evenement_id = id_evenement_id;
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


    public reclamation(int id, Evenement Id_evenement_id, type_reclamation id_tr_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.id = id;
        this.Id_evenement_id = Id_evenement_id;
        this.id_tr_id = id_tr_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }
        public reclamation(Evenement Id_evenement_id, type_reclamation id_tr_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.Id_evenement_id = Id_evenement_id;
        this.id_tr_id = id_tr_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }
    public reclamation(){
    }

    public reclamation(int id, Evenement Id_evenement_id, Timestamp date, String email, int telephone, String cmnt, String etat) {
        this.id = id;
        this.Id_evenement_id = Id_evenement_id;
        this.date = date;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }

    public reclamation( Evenement Id_evenement_id, type_reclamation id_tr_id, String email, int telephone, String cmnt) {
        this.Id_evenement_id = Id_evenement_id;
        this.id_tr_id = id_tr_id;
        this.email = email;
        this.telephone = telephone;
        this.cmnt = cmnt;
        this.etat = "traitement en cours";
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", Id_evenement_id=" + Id_evenement_id + ", id_tr_id=" + id_tr_id + ", date=" + date + ", email=" + email + ", telephone=" + telephone + ", cmnt=" + cmnt + ", etat=" + etat + "\n";
    }

public Timestamp getCurrentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
}

    
}
