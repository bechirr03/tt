package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeController {

    @FXML
    void naviguezVersLogin(ActionEvent event) throws IOException, SQLException {
        // Navigate to Menu page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        loginController controller = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void naviguezVersAdminLogin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminLogin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin login");
        stage.show();
    }


    @FXML
    void showChatbot(ActionEvent event) throws IOException {
        // Load the chatbot FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ICityChat.fxml"));
        Parent root = loader.load();

        // Create a new stage and set the chatbot scene
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Chatbot");
        stage.show();
    }
}
