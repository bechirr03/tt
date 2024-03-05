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
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReclamationAdminController implements Initializable {

    @FXML
    private TableColumn<?, ?> TypeCol;

    @FXML
    private TableColumn<?, ?> colEvenement;

    @FXML
    private Button btnRecSupp;

    @FXML
    private HTMLEditor mail;

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
    public void traiter() {
        int myIndex = this.table1.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(((reclamation)this.table1.getItems().get(myIndex)).getId()));
        String ma = ((reclamation)this.table1.getItems().get(myIndex)).getEmail();
        String content = this.mail.getHtmlText();
        Document doc = Jsoup.parse(content);
        String formattedText = doc.body().text();
        this.envoyeremail(ma, formattedText, id);
        ServiceReclamation rec = new ServiceReclamation();
        ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
        this.table1.setItems(obsListRec);
        this.mail.setHtmlText("");
    }


    public void envoyeremail(String mail, String reponse, int id) {
        String to = mail;
        String from = "bboughalmi8@gmail.com";
        String password = "bnvpqsupwhqfroeg";

        String host = "smtp.gmail.com";
        String port = "587";

        // Set up properties for the SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a new Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Traiement de votre Reclamation");
            message.setText(reponse);

            // Send the message
            Transport.send(message);

            System.out.println("Message sent successfully!");
            // Modifier le champ etat
            ServiceReclamation sr = new ServiceReclamation();
            System.out.println(id);
            sr.TrRec(id);

        } catch (MessagingException e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }






    private void ModifierRec(ActionEvent event) {
        reclamation r = (reclamation)this.table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            this.selectEmail.setEditable(true);
            this.selectEmail.setText(r.getEmail());
            this.selectTel.setEditable(true);
            this.selectTel.setText(Integer.toString(r.getTelephone()));
            this.selectCmnt.setEditable(true);
            this.selectCmnt.setText(r.getCmnt());

        }

    }

    private void EnregistrerRec(ActionEvent event) {
        reclamation r = (reclamation)this.table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            r.setEmail(this.selectEmail.getText());
            r.setTelephone(Integer.parseInt(this.selectTel.getText()));
            r.setCmnt(this.selectCmnt.getText());

            ServiceReclamation rec = new ServiceReclamation();
            rec.modifier_reclamation(r);
            ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
            this.table1.setItems(obsListRec);
            this.selectEmail.setEditable(false);
            this.selectEmail.setText("");
            this.selectTel.setEditable(false);
            this.selectTel.setText("");
            this.selectCmnt.setEditable(false);
            this.selectCmnt.setText("");

        }

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
