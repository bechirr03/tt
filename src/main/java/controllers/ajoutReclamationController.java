package controllers;


import models.reclamation;
import models.type_reclamation;

import models.Evenement;

import models.Categorie;
import services.ServiceReclamation;
import services.ServiceTypeReclamation;
import services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ajoutReclamationController implements Initializable {

    @FXML
    private TextField ajoutTel;
    @FXML
    private TextField ajoutEmail;

    @FXML
    private Label errorTel;
    @FXML
    private Label errorEmail;

    @FXML
    private ChoiceBox<String> ajoutTypeRec;
    @FXML
    private ChoiceBox<String> ajoutTevent;

    @FXML
    private TextArea ajoutCmnt;
    @FXML
    private Button btnType1;
    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// Ajout de choix d'Evenement dans la ComboBox ajoutTevent
        ServiceEvenement sp1 = new ServiceEvenement();
        try {
            ObservableList<Evenement> obsListEvenement= FXCollections.observableArrayList(sp1.afficherEvenement());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> l1=sp1.getallnamesEvenement();
        ObservableList<String> l22 = FXCollections.observableArrayList(l1);
        ajoutTevent.setItems(l22);

// Ajout de choix de type_reclamation dans la ComboBox ajoutTypeRec
        ServiceTypeReclamation sp2 = new ServiceTypeReclamation();
        ObservableList<type_reclamation> obsListTypeRec = FXCollections.observableArrayList(sp2.afficher_type_reclamation());
        List<String> l=sp2.getallnames_type_reclamation();
        ObservableList<String> l2 = FXCollections.observableArrayList(l);
        ajoutTypeRec.setItems(l2);

// Sélection par défaut
        ajoutTypeRec.getSelectionModel().selectFirst();
        ajoutTevent.getSelectionModel().selectFirst();


// Contrôles de saisie pour le champ de téléphone
        ajoutTel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ajoutTel.setText(oldValue);
                errorTel.setText("Num Tel doit contenir uniquement des chiffres!");
            } else {
                errorTel.setText("");
            }
        });

    }

    @FXML
    private void saveAjout(ActionEvent event) throws SQLException {
        // Vérification des champs
        if (!validateChamps()) {
            return;
        }

        reclamation r = new reclamation();
        ServiceTypeReclamation sp = new ServiceTypeReclamation();
        ServiceReclamation re =new ServiceReclamation();
        ServiceEvenement spp = new ServiceEvenement();
        type_reclamation i=new type_reclamation();
        List<type_reclamation> lr=sp.afficher_type_reclamation();
        for(type_reclamation t:lr){
            String n=t.getNom();
            if(n.equals(ajoutTypeRec.getValue())){
                i=t;
                break;
            }
        }
        Evenement v=new Evenement();
        ServiceEvenement y = new ServiceEvenement() ;
        List<Evenement> le=y.afficherEvenement();
        for(Evenement w:le){
            String d=w.getNom();
            if(d.equals(ajoutTevent.getValue())){
                v=w;
                break;
            }
        }


        r.setId_evenement_id(v);
        r.setId_tr_id(i);

        r.setDate(r.getCurrentTimestamp());
        r.setEmail(ajoutEmail.getText());
        r.setTelephone(Integer.parseInt(ajoutTel.getText()));
        r.setCmnt(ajoutCmnt.getText());
        r.setEtat("traitement en cours");
        re.ajouter_reclamation(r);
        ObservableList<reclamation> obsListType = FXCollections.observableArrayList(re.afficher_reclamation());
        ajoutEmail.setText("");
        ajoutTel.setText("");
        ajoutCmnt.setText("");
    }
    private boolean validateChamps() {
        boolean isValid = true;

        // Vérification du champ email
        if (!ajoutEmail.getText().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            errorEmail.setText("L'adresse e-mail n'est pas valide!");
            isValid = false;
        } else {
            errorEmail.setText("");
        }

        return isValid;
    }

    @FXML
    private void goEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goAcc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goRec(ActionEvent event) {
    }




    @FXML
    private void logout(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/evenement.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
