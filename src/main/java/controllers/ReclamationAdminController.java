package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.reclamation;
import models.Evenement;
import models.type_reclamation;

import services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;


public class ReclamationAdminController implements Initializable {

    @FXML
    private TableColumn<?, ?> TypeCol;

    @FXML
    private TableColumn<?, ?> colEvenement;

    @FXML
    private Button btnRecSupp;

    @FXML
    private Button btnRecSupp1;

    @FXML
    private TableColumn<?, ?> cmntCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TextArea selectCmnt;

    @FXML
    private TextField selectEmail;


    @FXML
    private TextField selectTel;

    @FXML
    private TextField selectTypeRec;

    @FXML
    private TableView<reclamation> table1;

    @FXML
    private TableColumn<?, ?> telCol;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        TableColumn<reclamation, Evenement> colEvenement = (TableColumn)this.table1.getColumns().get(0);
        colEvenement.setCellValueFactory((cellData) -> {
            return new SimpleObjectProperty(((reclamation)cellData.getValue()).getId_evenement_id());
        });
        colEvenement.setCellFactory((column) -> {
            return new TableCell<reclamation, Evenement>() {
                protected void updateItem(Evenement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        this.setText(item.getNom());
                    } else {
                        this.setText((String)null);
                    }

                }
            };
        });
        TableColumn<reclamation, type_reclamation> colTypeRec = (TableColumn)this.table1.getColumns().get(1);
        colTypeRec.setCellValueFactory((cellData) -> {
            return new SimpleObjectProperty(((reclamation)cellData.getValue()).getId_tr_id());
        });
        colTypeRec.setCellFactory((column) -> {
            return new TableCell<reclamation, type_reclamation>() {
                protected void updateItem(type_reclamation item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        this.setText(item.getNom());
                    } else {
                        this.setText((String)null);
                    }

                }
            };
        });
        TableColumn<reclamation, Timestamp> colDate = (TableColumn)this.table1.getColumns().get(2);
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        TableColumn<reclamation, String> colEmail = (TableColumn)this.table1.getColumns().get(3);
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        TableColumn<reclamation, Integer> colTelephone = (TableColumn)this.table1.getColumns().get(4);
        colTelephone.setCellValueFactory(new PropertyValueFactory("telephone"));
        TableColumn<reclamation, String> colCommentaire = (TableColumn)this.table1.getColumns().get(5);
        colCommentaire.setCellValueFactory(new PropertyValueFactory("cmnt"));


        ServiceReclamation rec = new ServiceReclamation();
        ObservableList<reclamation> obListType = FXCollections.observableArrayList(rec.afficher_reclamation());
        this.table1.setItems(obListType);
        this.table1.setRowFactory((tv) -> {
            TableRow<reclamation> myRow = new TableRow();
            myRow.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 1 && !myRow.isEmpty()) {
                    int myIndex = this.table1.getSelectionModel().getSelectedIndex();
                    int id = Integer.parseInt(String.valueOf(((reclamation)this.table1.getItems().get(myIndex)).getId()));
                    this.selectEmail.setText(((reclamation)this.table1.getItems().get(myIndex)).getEmail());
                    this.selectTel.setText(String.valueOf(((reclamation)this.table1.getItems().get(myIndex)).getTelephone()));
                    this.selectTypeRec.setText(((reclamation)this.table1.getItems().get(myIndex)).getId_tr_id().getNom());
                    this.selectCmnt.setText(((reclamation)this.table1.getItems().get(myIndex)).getCmnt());
                }

            });
            return myRow;
        });







    }
    @FXML
    private void SuppRec(ActionEvent event) {
        reclamation r = (reclamation)this.table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            ServiceReclamation rec = new ServiceReclamation();
            rec.supprimer_reclamation(r.getId());
            ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
            this.table1.setItems(obsListRec);
            this.selectEmail.setText("");
            this.selectTel.setText("");
            this.selectCmnt.setText("");

        }

    }

    @FXML
    void ajouter_type(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/TypeReclamation.fxml")) ;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
