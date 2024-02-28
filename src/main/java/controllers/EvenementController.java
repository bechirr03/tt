package controllers;


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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Evenement;
import services.ServiceCategorie;
import services.ServiceEvenement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class EvenementController implements Initializable {



    @FXML
    private TableView<Evenement> T;
    @FXML
    private TableColumn<Evenement, String> i;
    @FXML
    private TableColumn<Evenement, String> n;
    @FXML
    private TableColumn<Evenement, String> d;
    @FXML
    private TableColumn<Evenement, String> c;
    @FXML
    private TableColumn<Evenement, Float> p;
    @FXML
    private Button a;
    @FXML
    private TextField cher;
    @FXML
    private AnchorPane content;
    @FXML
    private Label nbrc;
    @FXML
    private Label nbrm;
    @FXML
    private Button supp;
    private ImageView imgg;
    ServiceEvenement sm = new ServiceEvenement();
    ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private PieChart ch;

    @FXML
    private Button mod;

    public static int idm;
    public static String ipa;

    public EvenementController() {
    }

public void initialize(URL url, ResourceBundle rb) {
    try {
        List<Evenement> evenementsList = sm.afficherEvenement();

        ObservableList<Evenement> evenementObservableList = FXCollections.observableArrayList(evenementsList);
        if (n == null) {
            System.out.println("leeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee") ;
        }
        if (n != null) {
            n.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        }
        if (d != null) {
            d.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        }
        if (c != null) {
            c.setCellValueFactory(new PropertyValueFactory<Evenement, String>("categorie"));
        }
        if (p != null) {
            p.setCellValueFactory(new PropertyValueFactory<Evenement, Float>("nbr"));
        }
        if (i != null) {
            i.setCellValueFactory(new PropertyValueFactory<>("image"));
            i.setCellFactory(column -> new TableCell<Evenement, String>() {
                private final ImageView imageView = new ImageView();


                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);

                    } else {
                        try {
                            Image image = new Image(new File(imagePath).toURI().toString());
                            imageView.setImage(image);
                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);
                            setGraphic(imageView);

                        } catch (IllegalArgumentException e) {
                            setGraphic(null);
                        }
                    }
                }
            });
        }
        if (T != null) {
            T.setItems(evenementObservableList);
        }
        int im = evenementsList.size();
        System.out.println(im);
        List<String> lc = sc.getallnamesCategorie();
        int ic = lc.size();
        if (nbrc != null) {
            nbrc.setText("Nombre de categories : " + ic);
        }
        if (nbrm != null) {
            nbrm.setText("Nombre de Evenement :" + im);
        }
    } catch (Exception ex) {
        Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
    }
}




    @FXML
    private void ajouter(ActionEvent event)  throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/Ajoutevent.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Evenement selectedItem = T.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String value = n.getCellData(selectedItem);
            List<Evenement> lm = sm.afficherEvenement();
            int i=0;
            for(Evenement m:lm){
                String nom=m.getNom();
                if(value.equals(nom)){
                    i = m.getId();
                    break;
                }
            }
            sm.supprimerEvenement(i);
            System.out.println("jaw");
            List<Evenement> evenementsList = sm.afficherEvenement();
            ObservableList<Evenement> evenementsObservableList = FXCollections.observableList(evenementsList);
            T.setItems(evenementsObservableList);
            int im=evenementsList.size();
            List<String> lc = sc.getallnamesCategorie();
            int ic= lc.size();
            nbrc.setText("Nombre de categories : "+ic);
            nbrm.setText("Nombre de evenement :"+im);

        }
    }


    @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
        Evenement selectedItem = T.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String value = n.getCellData(selectedItem);
            List<Evenement> lm = sm.afficherEvenement();
            int i=0;
            for(Evenement m:lm){
                String nom=m.getNom();
                if(value.equals(nom)){
                    idm = m.getId();
                    break;
                }
            }
            if (content != null) {
                content.getChildren().removeAll(content.getChildren());
            }
            Parent root = FXMLLoader.load(getClass().getResource("/Modifierevent.fxml")) ;
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    private ImageView getImageView(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        return imageView;
    }
    @FXML
    private void ajouter_categorie (ActionEvent event)  throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/Cat.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void voir_reclamation (ActionEvent event)  throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/ReclamationAdmin.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}