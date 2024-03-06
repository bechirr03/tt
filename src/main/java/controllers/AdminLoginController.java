package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private PasswordField AdminCode_textField = new PasswordField();
    @FXML
    private Label status_label ;

    public void verifierAdmin (ActionEvent event) throws IOException {
        String code = AdminCode_textField.getText();
        if (code.equalsIgnoreCase("onsbahri")||code.equalsIgnoreCase("bader")||code.equalsIgnoreCase("bachir")||code.equalsIgnoreCase("ghassen") ){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminSpace.fxml"));
            Parent root = loader.load();
            AdminController controller = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        }else {
            status_label.setText("This code is invalid !");
        }
    }
}
