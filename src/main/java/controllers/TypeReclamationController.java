/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import models.reclamation;
import models.stat;
import models.type_reclamation;

import models.Evenement;
import services.ServiceTypeReclamation;
import services.ServiceReclamation;
import services.ServiceEvenement;
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



public class TypeReclamationController implements Initializable {
    //Type Reclamation 
    @FXML
    private TextField ftnom;
    @FXML
    private TableView<type_reclamation> table;
    @FXML
    private TextField ftnom1;
    //Reclamation
    @FXML
    private TableView<reclamation> table1;

    @FXML
    private TextField selectEmail;
    @FXML
    private TextField selectTel;
    @FXML
    private TextField selectEtat;
    private TextField ajoutTel;
    private TextField ajoutEmail;
    private ChoiceBox<String> ajoutTypeRec;
    @FXML
    private TextField selectTypeRec;
    @FXML
    private TextArea selectCmnt;
    private TextArea ajoutCmnt;
    @FXML
    private HTMLEditor mail;
    @FXML
    private LineChart<String, Integer> ChartMois;
    @FXML
    private BarChart<String, Integer> ChartType;
    @FXML
    private PieChart ChartTraite;
    @FXML
    private Label nbrT;
    @FXML
    private Label nbrR;
    private AnchorPane contenu;
    @FXML
    private Button btnType;
    @FXML
    private Button btnTypeSupp;
    @FXML
    private Button btnTypeModifier;
    @FXML
    private Button btnEnregistrerType;
    @FXML
    private TableColumn<?, ?> EvenementCol;
    @FXML
    private TableColumn<?, ?> TypeCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private TableColumn<?, ?> telCol;
    @FXML
    private TableColumn<?, ?> cmntCol;
    @FXML
    private TableColumn<?, ?> etatCol;
    @FXML
    private Button btnRecSupp;


    /**
     * Type Reclamation.
     */

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


//Reclamation    



        TableColumn<reclamation, Evenement> colEvenement = (TableColumn<reclamation, Evenement>) table1.getColumns().get(0);
        colEvenement.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId_evenement_id()));
        colEvenement.setCellFactory(column -> new TableCell<reclamation, Evenement>() {
            @Override
            protected void updateItem(Evenement item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });

        TableColumn<reclamation, type_reclamation> colTypeRec = (TableColumn<reclamation, type_reclamation>) table1.getColumns().get(1);
        colTypeRec.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId_tr_id()));
        colTypeRec.setCellFactory(column -> new TableCell<reclamation, type_reclamation>() {
            @Override
            protected void updateItem(type_reclamation item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });


        TableColumn<reclamation, Timestamp> colDate = (TableColumn<reclamation, Timestamp>) table1.getColumns().get(2);
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<reclamation, String> colEmail = (TableColumn<reclamation, String>) table1.getColumns().get(3);
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<reclamation, Integer> colTelephone = (TableColumn<reclamation, Integer>) table1.getColumns().get(4);
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        TableColumn<reclamation, String> colCommentaire = (TableColumn<reclamation, String>) table1.getColumns().get(5);
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("cmnt"));

        TableColumn<reclamation, String> colEtat = (TableColumn<reclamation, String>) table1.getColumns().get(6);
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));


        colEtat.setCellFactory(column -> new TableCell<reclamation, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                }
            }

        });
        table1.setRowFactory(tv -> new TableRow<reclamation>() {
            @Override
            public void updateItem(reclamation item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    switch (item.getEtat()) {
                        case "Reclamation traitée":
                            setStyle("-fx-background-color: green;");
                            break;
                        case "traitement en cours":
                            setStyle("-fx-background-color: red;");
                            break;
                        default:
                            setStyle("");
                            break;
                    }
                }
            }

        });


        ServiceReclamation rec = new ServiceReclamation();
        ObservableList<reclamation> obListType = FXCollections.observableArrayList(rec.afficher_reclamation());
        table1.setItems(obListType);

        table1.setRowFactory(tv -> {
            TableRow<reclamation> myRow = new TableRow<>();

            myRow.setOnMouseClicked ((event) ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex =  table1.getSelectionModel().getSelectedIndex();

                    int id = Integer.parseInt(String.valueOf(table1.getItems().get(myIndex).getId()));
                    selectEmail.setText(table1.getItems().get(myIndex).getEmail());
                    selectTel.setText(String.valueOf((Integer)table1.getItems().get(myIndex).getTelephone()));
                    selectEtat.setText(table1.getItems().get(myIndex).getEtat());

                    selectTypeRec.setText(table1.getItems().get(myIndex).getId_tr_id().getNom());
                    selectCmnt.setText(table1.getItems().get(myIndex).getCmnt());

                }
            });
            return myRow;


        });


        //*** Nombre reclamation et type reclamation ***
        int nb1=lt.size();
        int nb2=lr.size();
        nbrT.setText("Nombre de Type Reclamation : "+nb1);
        nbrR.setText("Nombre de Reclamation : "+nb2);
        //*** Nombre de reclamation par type reclamation ***
        Series<String, Integer> series = new Series<>();
        ObservableList<Series<String, Integer>> observableList = FXCollections.observableArrayList();
        for (type_reclamation t:lt){
            int nb=0;
            for(reclamation r:lr){
                if (t.getId()==r.getId_tr_id().getId()){
                    nb++;
                }
            }
            series.getData().add(new Data<>(t.getNom(),nb));
        }
        observableList.add(series);
        ChartType.setData(observableList);
        // *** Chart represente le nombre de reclamation traite et en cour
        int nbt=0;
        int nbc=0;
        for(reclamation r:lr){
            if (r.getEtat().equals("traitement en cours")){
                nbc++;
            }
            else{
                nbt++;
            }
        }
        ObservableList<PieChart.Data> schart = FXCollections.observableArrayList();
        schart.add(new PieChart.Data("Traitement en cours",nbc));
        schart.add(new PieChart.Data("Reclamation Traitée",nbt));
        ChartTraite.setData(schart);
        //*** Statistiques presente le nombre de reclamation par mois
        Series<String, Integer> rm = new Series<>();
        ObservableList<Series<String, Integer>> observablerm = FXCollections.observableArrayList();
        List<String> mois = Arrays.asList("Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre");
        for(int i=0;i<12;i++){
            int nbm=0;
            for(reclamation r:lr){
                Calendar cal = Calendar.getInstance();
                cal.setTime(r.getDate());
                int month = cal.get(Calendar.MONTH);
                if(month==i){
                    nbm++;
                }
            }
            rm.getData().add(new Data<>(mois.get(i),nbm));
        }
        observablerm.add(rm);
        ChartMois.setData(observablerm);





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

    /**
     * Reclamation.
     */



    @FXML
    private void SuppRec(ActionEvent event) {
        reclamation r = table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            ServiceReclamation rec = new ServiceReclamation();
            rec.supprimer_reclamation(r.getId());
            ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
            table1.setItems(obsListRec);
            selectEmail.setText("");
            selectTel.setText("");
            selectCmnt.setText("");
            selectEtat.setText("");
        }
    }

    private void ModifierRec(ActionEvent event) {
        reclamation r = table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            selectEmail.setEditable(true);
            selectEmail.setText(r.getEmail());
            selectTel.setEditable(true);
            selectTel.setText(Integer.toString(r.getTelephone()));
            selectCmnt.setEditable(true);
            selectCmnt.setText(r.getCmnt());
            selectEtat.setEditable(true);
            selectEtat.setText(r.getEtat());
        }
    }

    private void EnregistrerRec(ActionEvent event) {
        reclamation r = table1.getSelectionModel().getSelectedItem();
        if (r != null) {
            //        r.setId_tr_id(Integer.parseInt(selectTypeRec.getValue().getId(),selectTypeRec.getValue().getNom()));

            r.setEmail(selectEmail.getText());
            r.setTelephone(Integer.parseInt(selectTel.getText()));
            r.setCmnt(selectCmnt.getText());
            r.setEtat(selectEtat.getText());
            ServiceReclamation rec = new ServiceReclamation();
            rec.modifier_reclamation(r);
            ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
            table1.setItems(obsListRec);
            selectEmail.setEditable(false);
            selectEmail.setText("");
            selectTel.setEditable(false);
            selectTel.setText("");
            selectCmnt.setEditable(false);
            selectCmnt.setText("");
            selectEtat.setEditable(false);
            selectEtat.setText("");
        }

    }
 /*   @FXML
    public void traiter(){
              
        int myIndex = table1.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(table1.getItems().get(myIndex).getId()));
        
                String ma = table1.getItems().get(myIndex).getEmail();

                   String descr = mail.getHtmlText();
                   envoyeremail(ma, descr,id);

                   
    } 

}*/

    @FXML
    public void traiter() {
        int myIndex = table1.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(table1.getItems().get(myIndex).getId()));
        String ma = table1.getItems().get(myIndex).getEmail();
        String content = mail.getHtmlText();
        Document doc = Jsoup.parse(content);
        String formattedText = doc.body().text();
        envoyeremail(ma, formattedText, id);
        ServiceReclamation rec = new ServiceReclamation();
        ObservableList<reclamation> obsListRec = FXCollections.observableArrayList(rec.afficher_reclamation());
        table1.setItems(obsListRec);
        mail.setHtmlText("");
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


}


