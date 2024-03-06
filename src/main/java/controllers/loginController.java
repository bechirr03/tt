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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class loginController {

    @FXML
    private TextField email_textField;

    @FXML
    private PasswordField password_textField;

    private Session session;
    private User user;
    private Compte compte;
    private UserController userController;


    @FXML
    void naviguezVersMenu(ActionEvent event) throws IOException {
        String email = email_textField.getText();
        String password = password_textField.getText();

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            email_textField.setStyle("-fx-border-color: rgba(255,0,0,0.87);");
            password_textField.setStyle("-fx-border-color: rgba(255,0,0,0.87);");
            return;
        }

        try {
            int count = new CompteService().exist(email, password);
            if (count == 1) {
                // Login successful
                email_textField.setStyle("-fx-border-color: rgb(63,205,40);");
                password_textField.setStyle("-fx-border-color: rgb(63,205,40);");

                // Navigate to Menu page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
                Parent root = loader.load();
                MenuController controller = loader.getController();
                controller.getLoginSessionLogin(LoginSession());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                // Login failed

                email_textField.setStyle("-fx-border-color: rgba(255,0,0,0.87);");
                password_textField.setStyle("-fx-border-color: rgba(255,0,0,0.87);");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void naviguezVersSignUp(ActionEvent event) throws IOException, SQLException {
        // Navigate to Menu page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUP.fxml"));
        Parent root = loader.load();
        SignUpController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    void naviguezVersWelcome(ActionEvent event) throws IOException, SQLException {
        // Navigate to Menu page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WelcomePage.fxml"));
        Parent root = loader.load();
        WelcomeController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public User LoginUser() throws SQLException {
        UserService us = new UserService();
        String email = email_textField.getText();
        user = us.getUser(email) ;
        return user;
    }
    public Compte LoginCompte() throws SQLException {
        CompteService cs = new CompteService();
        String email = email_textField.getText();
        String password = password_textField.getText();
        compte = cs.getCompte(email,password) ;
        return compte;
    }
    public Session LoginSession( ) throws SQLException {
        compte=LoginCompte();
        user=LoginUser();
        Session session = new Session();
        session.setUser(user);
        session.setCompte(compte);
        return session;
    }

}