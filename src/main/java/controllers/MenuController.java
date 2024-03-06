package controllers;

import models.Compte;
import models.Session;
import models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class MenuController {


    private User user;
    private Compte compte;
    private Session session;

    @FXML
    void naviguezVersUser(ActionEvent event) throws IOException, SQLException {


        // Navigate to user page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User.fxml"));
        Parent root = loader.load();
        UserController controller = loader.getController();

        controller.getLoginSessionMenu(getLoginSessionLogin(session));
        controller.initAffiche(session);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public Session getLoginSessionLogin (Session session){
        this.session= session;
        return session;
    }
    public Session getLoginSessionUser (Session session){
        this.session= session;
        return session;
    }
}
