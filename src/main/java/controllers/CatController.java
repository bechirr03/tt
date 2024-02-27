package controllers;


import models.Categorie;
import models.Evenement;
import services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class CatController implements Initializable {

    @FXML
    private TableView<Categorie> t;
    @FXML
    private TableColumn<Categorie, String> n;
    @FXML
    private Button aj;
    @FXML
    private Button s;
    @FXML
    private Button m;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategorie sc = new ServiceCategorie();
        List<Categorie> categoriesList = sc.afficherCategorie();
        n.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));
        ObservableList<Categorie> categoriesObservableList = FXCollections.observableList(categoriesList);
        t.setItems(categoriesObservableList);
    }

   /* @FXML
    private void h(ActionEvent event) {
        Stage primaryStage=new Stage();
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutCat.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajouter Categorie");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("ahla bik");

        }
    }
*/
    @FXML
    private void goAjouter (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AjoutCat.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Categorie selectedItem = t.getSelectionModel().getSelectedItem();
        ServiceCategorie sc = new ServiceCategorie();
        if (selectedItem != null) {

            String value = n.getCellData(selectedItem);
            List<Categorie> lm = sc.afficherCategorie();
            int i=0;
            for(Categorie m:lm){
                String nom=m.getNom();
                if(value.equals(nom)){
                    i = m.getId();
                    break;
                }
            }
            sc.supprimerCategorie(i);
        }
    }
}


