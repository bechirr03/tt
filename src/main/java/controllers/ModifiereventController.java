
package controllers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categorie;
import models.Evenement;
import services.ServiceCategorie;
import services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class ModifiereventController implements Initializable {

    @FXML
    private TextField i;
    @FXML
    private TextField p;
    @FXML
    private ChoiceBox<String> c;
    @FXML
    private TextField d;
    @FXML
    private TextField n;
    @FXML
    private Button annuler;
    @FXML
    private Button enr;

    ServiceEvenement sm = new ServiceEvenement();
     ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private AnchorPane content;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         Evenement m =new Evenement();
        m=sm.getbyidEvenement(EvenementController.idm);
        i.setText(m.getImage());
        p.setText(""+m.getnbr());
        List<String> lc = sc.getallnamesCategorie();
      c.setItems(FXCollections.observableArrayList(lc));
        d.setText(m.getDescription());
        n.setText(m.getNom());
    }    

    @FXML
    private void GoBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/evenement.fxml"))) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML


    private void Update(ActionEvent event) throws SQLException, IOException {
        String image=i.getText();
        String nom=n.getText();
        String description=d.getText();
        String nbr= p.getText();
        System.out.println(nbr);
        Categorie caaa = new Categorie();
        String categorie=c.getValue();
        List<Categorie> lc = sc.afficherCategorie();
        for (Categorie a:lc){
            if (categorie.equals(a.getNom())){
                caaa=a;
                break;
            }
        }
        ServiceEvenement sm = new ServiceEvenement();
        float nbrf=Float.parseFloat(nbr);
        Evenement m = new Evenement(nom,caaa,description,image,nbrf);
        sm.modifierEvenement(m);
        if (content != null) {
            content.getChildren().removeAll(content.getChildren());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}