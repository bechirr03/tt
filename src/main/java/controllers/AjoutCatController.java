package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categorie;
import services.ServiceCategorie;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AjoutCatController implements Initializable {
    @FXML
    private TextField n;
    @FXML
    private Button b;

    public AjoutCatController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void AjouterC(ActionEvent event) {
        String nom = this.n.getText();
        Categorie c = new Categorie(nom);
        ServiceCategorie sc = new ServiceCategorie();
        sc.ajouterCategorie(c);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Cat.fxml")) ;
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
