package controllers;

import models.Compte;
import models.User;
import services.CompteService;
import services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ShowDBController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableView<Compte> compteTable;
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

    private ObservableList<User> userData;
    private ObservableList<Compte> compteData;

    public void initialize() throws SQLException {
        // Retrieve user and compte data from the database
        CompteService cs = new CompteService();
        UserService us = new UserService();
        compteData = FXCollections.observableArrayList(cs.recuperer());
        userData = FXCollections.observableArrayList(us.recuperer());

        // Set up user table view
        userTable.setItems(userData);
        userTable.getColumns().addAll(createUserTableColumns());

        // Set up compte table view
        compteTable.setItems(compteData);
        compteTable.getColumns().addAll(createCompteTableColumns());

        // Set up event handler for user table selection
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Set text fields to the values of the selected user
                        Fname_textField.setText(newValue.getPrenom());
                        String Cin = Integer.toString(newValue.getCIN());
                        cin_textField.setText(Cin);
                        email_textField.setText(newValue.getEmail());
                        name_textField.setText(newValue.getNom());
                        String num = Integer.toString(newValue.getNumbre());
                        number_textField.setText(num);

                }}
        );
    }

    private ObservableList<TableColumn<User, ?>> createUserTableColumns() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<TableColumn<User, ?>> columns = FXCollections.observableArrayList();
        columns.addAll(idColumn, usernameColumn, nameColumn, emailColumn);
        return columns;
    }

    private ObservableList<TableColumn<Compte, ?>> createCompteTableColumns() {
        TableColumn<Compte, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Compte, String> accountNumberColumn = new TableColumn<>("Account Number");
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

        TableColumn<Compte, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        ObservableList<TableColumn<Compte, ?>> columns = FXCollections.observableArrayList();
        columns.addAll(idColumn, accountNumberColumn, balanceColumn);
        return columns;
    }
}