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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static utils.Mail.sendPinEmail;

public class SignUpController {

    @FXML
    private TextField cin_textField;

    @FXML
    private TextField name_textField;

    @FXML
    private TextField Fname_textField;

    @FXML
    private TextField number_textField;

    @FXML
    private TextField email_textField;

    @FXML
    private PasswordField password_textfield;

    @FXML
    private Label cin_error;
    @FXML
    private Label name_error;
    @FXML
    private Label fname_error;
    @FXML
    private Label number_error;
    @FXML
    private Label email_error;
    @FXML
    private Label password_error;
    private boolean valide = true;
    private User user;
    private Compte compte;
    private Session session;

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        valide = true;

        UserService userService = new UserService();
        CompteService compteService = new CompteService();

        // Validate CIN
        String textCIN = cin_textField.getText();
        if (!textCIN.matches("\\d{8}") || textCIN.isEmpty()) {
            cin_error.setText("Please enter a valid CIN.");
            valide = false;
        } else {
            try {
                int count = userService.chercher(String.valueOf(Integer.parseInt(textCIN)));
                if (count > 0) {
                    cin_error.setText("CIN already exists.");
                    valide = false;
                }else {
                    cin_error.setText("");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        // Validate email
        String textEmail = email_textField.getText();
        if (!textEmail.matches("\\w+(\\.\\w+)?+@\\w+\\.\\w+") || textEmail.isEmpty()) {
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
        String textPassWord = password_textfield.getText();
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
                userService.ajouter(user);

                Compte compte = new Compte();
                compte.setEmail(textEmail);
                compte.setMotDePasse(textPassWord);
                compte.setCIN(Integer.parseInt(textCIN));
                compteService.ajouter(compte);
                sendPinEmail("icity.app.3a18@gmail.com","rmms ykaw ohyg bfnh",textEmail,"Welcome to ICity", textName+" "+textFirstName);



                // Navigate to Menu page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent root = loader.load();
                loginController controller = loader.getController();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));


                cin_error.setText("Congratulations, your account has been created!");
                password_error.setText("");
                name_error.setText("");
                fname_error.setText("");
                number_error.setText("");
                email_error.setText("");

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                cin_error.setText("Error creating account. Please try again later.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void naviguezVersLogin(ActionEvent event) throws IOException, SQLException {
        // Navigate to Menu page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        loginController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}