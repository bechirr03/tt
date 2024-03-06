package controllers;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.reclamation;
import models.Evenement;
import models.type_reclamation;
import javafx.scene.chart.XYChart.Series;
import services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.web.HTMLEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import services.ServiceTypeReclamation;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;


public class ReclamationAdminController implements Initializable {

    @FXML
    private TextField ftnom;
    @FXML
    private TableView<type_reclamation> table;
    @FXML
    private TextField ftnom1;
    @FXML
    private TableView<reclamation> table1;
    @FXML
    private TextField selectEmail;
    @FXML
    private TextField selectTel;
    @FXML
    private TextField selectEtat;
    @FXML
    private TextField ajoutTel;
    @FXML
    private TextField ajoutEmail;


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
    @FXML
    private Button btnType;
    @FXML
    private Button btnTypeSupp;
    @FXML
    private Button btnTypeModifier;
    @FXML
    private Button btnEnregistrerType;
    @FXML
    private TableColumn<?, ?> ColEvenement;
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<type_reclamation, String> colType = (TableColumn)this.table.getColumns().get(0);
        colType.setCellValueFactory(new PropertyValueFactory("nom"));
        ServiceTypeReclamation sp = new ServiceTypeReclamation();
        ServiceReclamation sr = new ServiceReclamation();
        List<type_reclamation> lt = sp.afficher_type_reclamation();
        List<reclamation> lr = sr.afficher_reclamation();
        ObservableList<type_reclamation> obsListType = FXCollections.observableArrayList(sp.afficher_type_reclamation());
        this.table.setItems(obsListType);
        this.table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.ftnom1.setText(newSelection.getNom());
            }

        });
        TableColumn<reclamation, Evenement> colEvenement = (TableColumn)this.table1.getColumns().get(0);
        colEvenement.setCellValueFactory((cellData) -> {
          //  return new SimpleObjectProperty(((reclamation)cellData.getValue()).getId_evenement_id();
               return new SimpleObjectProperty(((reclamation)cellData.getValue()).getId_tr_id());

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
        TableColumn<reclamation, String> colEtat = (TableColumn)this.table1.getColumns().get(6);
        colEtat.setCellValueFactory(new PropertyValueFactory("etat"));
        colEtat.setCellFactory((column) -> {
            return new TableCell<reclamation, String>() {
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        this.setText(item);
                    } else {
                        this.setText((String)null);
                    }

                }
            };
        });
        this.table1.setRowFactory((tv) -> {
            return new TableRow<reclamation>() {
                public void updateItem(reclamation item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        switch (item.getEtat()) {
                            case "Reclamation traitée":
                                this.setStyle("-fx-background-color: green;");
                                break;
                            case "traitement en cours":
                                this.setStyle("-fx-background-color: red;");
                                break;
                            default:
                                this.setStyle("");
                        }
                    } else {
                        this.setStyle("");
                    }

                }
            };
        });
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
        ObservableList<XYChart.Series<String, Integer>> observablerm = FXCollections.observableArrayList();
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

    /* {


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




    }*/


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
        String password = "azqs147258";

        String host = "smtp.gmail.com";
        String port = "3306";

        // Set up properties for the SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "3306");
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
