package controllers;


import java.awt.image.BufferedImage;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categorie;
        import models.Evenement;
        import services.ServiceCategorie;
        import services.ServiceEvenement;
        import java.awt.FileDialog;
        import java.awt.image.RenderedImage;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.IOException;
        import java.net.URL;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;
        import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javafx.collections.FXCollections;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.control.Button;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.image.WritableImage;
        import javafx.stage.FileChooser;
        import javafx.stage.FileChooser.ExtensionFilter;
        import javax.imageio.ImageIO;
        import javax.swing.JFileChooser;
        import javax.swing.filechooser.FileNameExtensionFilter;


public class AjouteventController implements Initializable {

    private TextField i;
    @FXML
    private TextField n;
    @FXML
    private TextField d;
    @FXML
    private TextField p;
    @FXML
    private ChoiceBox<String> c;
    @FXML
    private Button a;
    @FXML
    private Button r;
    @FXML
    private AnchorPane content;
    private ImageView imgg;
    @FXML
    private Label path;
    @FXML
    private Button Image;

    private File selectedFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategorie sc = new ServiceCategorie();
        List<String> lc = sc.getallnamesCategorie();
        c.setItems(FXCollections.observableArrayList(lc));
    }
    ServiceCategorie sc = new ServiceCategorie();
    @FXML
    /*private void ajouterBOUTTON(ActionEvent event) {

        String Image=path.getText();
        String nom=n.getText();
        String description=d.getText();
        String nbr= p.getText();
        System.out.println(nbr);
        Categorie caaa = new Categorie();
        String categorie=c.getValue();
        List<Categorie> lc = sc.afficherCategorie();
        for (Categorie a:lc){
            if (categorie.equals(a.getNom())){
                caaa=a;
                break;
            }
        }
        AnchorPane content = new AnchorPane();
        ServiceEvenement sm = new ServiceEvenement();
        float nbrf=Float.parseFloat(nbr);
        Evenement m = new Evenement(nom,caaa,description,Image,nbrf);
        sm.ajouterEvenement(m);
        content.getChildren().removeAll(content.getChildren());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/evenement.fxml")) ;
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */
    private void ajouterBOUTTON(ActionEvent event) {
        try {
            String Image = path != null ? path.getText() : null;
            String nom = n != null ? n.getText() : null;
            String description = d != null ? d.getText() : null;
            String nbr = p != null ? p.getText() : null;
            if (nbr != null) {
                System.out.println(nbr);
            }
            Categorie caaa = new Categorie();
            String categorie = c != null ? c.getValue() : null;
            List<Categorie> lc = sc.afficherCategorie();
            for (Categorie a : lc) {
                if (categorie != null && categorie.equals(a.getNom())) {
                    caaa = a;
                    break;
                }
            }
            AnchorPane content = new AnchorPane();
            ServiceEvenement sm = new ServiceEvenement();
            float nbrf = nbr != null ? Float.parseFloat(nbr) : 0;
            Evenement m = new Evenement(nom, caaa, description, Image, nbrf);
            sm.ajouterEvenement(m);
            content.getChildren().removeAll(content.getChildren());
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/evenement.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reinBOUTTON(ActionEvent event) {

        n.setText("");
        d.setText("");
        p.setText("");
        c.setValue("");
        path.setText("");
    }
    @FXML
    void addimgcours(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif"}));
        this.selectedFile = fileChooser.showOpenDialog(this.Image.getScene().getWindow());
        if (this.selectedFile != null) {
            this.saveImage(this.selectedFile);
        }

    }

    private void saveImage(File file) {
        Path destinationFolderPath = Paths.get("imgs/imgs");
        if (!Files.exists(destinationFolderPath)) {
            try {
                Files.createDirectories(destinationFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path destinationFilePath = destinationFolderPath.resolve(file.getName());
        path.setText(file.getName());
        try {
            Files.copy(file.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}