package controllers;


import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
import models.reclamation;

import models.type_reclamation;

import services.ServiceTypeReclamation;
import services.ServiceReclamation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class TypeReclamationController implements Initializable {

    @FXML
    private Button btnEnregistrerType;

    @FXML
    private Button btnType;

    @FXML
    private Button btnTypeModifier;

    @FXML
    private Button btnTypeSupp;

    @FXML
    private TextField ftnom;

    @FXML
    private TextField ftnom1;

    @FXML
    private TableView<type_reclamation> table;






    @Override
    public void initialize(URL url, ResourceBundle rb) {
//Type Reclamation
        TableColumn<type_reclamation, String> colType = (TableColumn<type_reclamation, String>) table.getColumns().get(0);
        colType.setCellValueFactory(new PropertyValueFactory<>("nom"));

        ServiceTypeReclamation sp = new ServiceTypeReclamation();
        ServiceReclamation sr = new ServiceReclamation();
        List<type_reclamation> lt = sp.afficher_type_reclamation();
        List<reclamation> lr = sr.afficher_reclamation();
        ObservableList<type_reclamation> obsListType = FXCollections.observableArrayList(sp.afficher_type_reclamation());
        table.setItems(obsListType);

        // Ajouter l'écouteur de sélection de ligne
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Afficher le nom du type_reclamation sélectionné dans le TextField ftnom1
                ftnom1.setText(newSelection.getNom());
            }
        });




    }
    @FXML
    private void saveType(ActionEvent event) {
        type_reclamation t = new type_reclamation();
        t.setNom(ftnom.getText());
        ServiceTypeReclamation sp =new ServiceTypeReclamation();
        sp.ajouter_type_reclamation(t);
        ObservableList<type_reclamation> obsListType = FXCollections.observableArrayList(sp.afficher_type_reclamation());
        table.setItems(obsListType);
        ftnom.setText("");

    }

    @FXML
    private void ModifierType(ActionEvent event) {
        type_reclamation t = table.getSelectionModel().getSelectedItem();
        if (t != null) {
            ftnom1.setEditable(true);
            ftnom1.setText(t.getNom());
        }
    }

    @FXML
    private void EnregistrerType(ActionEvent event) {
        type_reclamation t = table.getSelectionModel().getSelectedItem();
        if (t != null) {
            t.setNom(ftnom1.getText());
            ServiceTypeReclamation sp = new ServiceTypeReclamation();
            sp.modifier_type_reclamation(t);
            ObservableList<type_reclamation> obsListType = FXCollections.observableArrayList(sp.afficher_type_reclamation());
            table.setItems(obsListType);
            ftnom1.setEditable(false);
            ftnom1.setText("");
        }
    }

    @FXML
    private void SuppType(ActionEvent event) {
        type_reclamation t = table.getSelectionModel().getSelectedItem();
        if (t != null) {
            ServiceTypeReclamation sp = new ServiceTypeReclamation();
            sp.supprimer_type_reclamation(t.getId());
            ObservableList<type_reclamation> obsListType = FXCollections.observableArrayList(sp.afficher_type_reclamation());
            table.setItems(obsListType);
            ftnom1.setText("");
        }
    }
//lota


}