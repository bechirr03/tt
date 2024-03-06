package controllers;

import models.Compte;
import models.Session;
import models.User;
import services.CompteService;
import services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UserController {
    UserService userService = new UserService();
    CompteService compteService = new CompteService();
    private User user;
    private Compte compte;
    private Session session;
    @FXML
    private TextField Fname_textField;
    @FXML
    private TextField cin_textField;
    @FXML
    private TextField email_textField;
    @FXML
    private TextField name_textField;
    @FXML
    private TextField number_textField;
    @FXML
    private TextField pass_textField;
    @FXML
    private Label status_label;
    @FXML
    private Label fname_error;
    @FXML
    private Label name_error;
    @FXML
    private Label cin_error;
    @FXML
    private Label number_error;
    @FXML
    private Label email_error;
    @FXML
    private Label password_error;
    @FXML
    private Label score_label;
    @FXML
    private Label money_label;



    public void setSession(Session session) {
        this.session = session;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @FXML
    void initAffiche(Session session)
    {
        setSession(session);
        user=session.getUser();
        compte=session.getCompte();
        Fname_textField.setText(user.getPrenom());
        String Cin = Integer.toString(user.getCIN());
        cin_textField.setText(Cin);
        email_textField.setText(user.getEmail());
        name_textField.setText(user.getNom());
        String num = Integer.toString(user.getNumbre());
        number_textField.setText(num);
        pass_textField.setText(compte.getMotDePasse());
        String money = Float.toString(compte.getMoney());
        money_label.setText(money+" DT");
        String score = Float.toString(compte.getScore());
        score_label.setText(score+" points");

    }


    @FXML
    void naviguezVersMenu(ActionEvent event) throws IOException, SQLException {
        // Navigate to Menu page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        controller.getLoginSessionLogin(getLoginSessionMenu(session));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public Session getLoginSessionMenu (Session session){
        this.session= session;
        return session;
    }



    @FXML
    void modifierUserCompte() throws SQLException {
        boolean valide = true;


        String textCIN = cin_textField.getText();
        if (!textCIN.matches("\\d{8}") || textCIN.isEmpty()) {
            cin_error.setText("Please enter a valid CIN.");
            valide = false;
        } else {
                    cin_error.setText("");
                }

        // Validate email
        String textEmail = email_textField.getText();
        if (!textEmail.matches("\\w+@\\w+\\.\\w+") || textEmail.isEmpty()) {
            email_error.setText("Please enter a valid email address.");
            valide = false;
        }else {
            email_error.setText("");
        }

        // Validate name
        String textName = name_textField.getText();
        if (!textName.matches("[a-zA-Z]+") || textName.isEmpty()) {
            name_error.setText("Please enter a valid name.");
            valide = false;
        }else {
            name_error.setText("");
        }

            // Validate first name
            String textFirstName = Fname_textField.getText();
            if (!textFirstName.matches("[a-zA-Z]+") || textFirstName.isEmpty()) {
                fname_error.setText("Please enter a valid first name.");
                valide = false;
            }else {
                fname_error.setText("");
            }

                // Validate phone number
                String textPhoneNumber = number_textField.getText();
                if (!textPhoneNumber.matches("\\d{8}") || textPhoneNumber.isEmpty()) {
                    number_error.setText("Please enter a valid phone number.");
                    valide = false;
                }else {
                    number_error.setText("");
                }

                    // Validate password
                    String textPassWord = pass_textField.getText();
                    if (textPassWord.isEmpty()) {
                        password_error.setText("Please enter a password.");
                        valide = false;
                    }else {
                        password_error.setText("");
                    }

                        if (valide) {
                            try {
                                User user = new User();
                                user.setNom(textName);
                                user.setPrenom(textFirstName);
                                user.setEmail(textEmail);
                                user.setNumbre(Integer.parseInt(textPhoneNumber));
                                user.setCIN(Integer.parseInt(textCIN));
                                userService.modifier(user);

                                Compte compte = new Compte();
                                compte.setEmail(textEmail);
                                compte.setMotDePasse(textPassWord);
                                compte.setCIN(Integer.parseInt(textCIN));
                                compteService.modifier(compte);
                                status_label.setText("Your changes have been successfully saved !!");
                                session.setCompte(compte);
                                session.setUser(user);


                            } catch (SQLException e) {
                            System.err.println(e.getMessage());
                            }
                        }
    }
                @FXML
                void supprimerUser(ActionEvent event) throws IOException, SQLException {

                    boolean valide= true;

                    String textCIN = cin_textField.getText();
                    int count = userService.chercher(String.valueOf(Integer.parseInt(textCIN)));
                    if (count==0) {
                        cin_error.setText("CIN n'existe pas !!");
                        valide=false;
                    }
                    if (valide){
                    compteService.supprimer(Integer.parseInt(textCIN));
                    userService.supprimer(Integer.parseInt(textCIN));

                    // Navigate to Menu page
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                    Parent root = loader.load();
                    loginController controller = loader.getController();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
    }




}