package com.example;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookControlor implements Initializable {
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button addBtn;
    @FXML
    private Button exportBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button quitBtn;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> emailCol;
    @FXML
    private TableColumn<Person, String> firstNameCol;
    @FXML
    private TableColumn<Person, String> lastNameCol;

    private DataClass data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialiser les colonnes de la table
        emailCol.setCellValueFactory(new PropertyValueFactory<>( "email"));
        firstNameCol.setCellValueFactory(   new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(  new PropertyValueFactory<>("lastName"));

        // Initialisez votre objet DataClass
        data = new DataClass();

        // Mettre à jour la table avec les données initiales
        updateTable();

        // Associer les actions aux boutons
        addBtn.setOnAction(event -> addButtonClicked());
        exportBtn.setOnAction(event -> exportButtonClicked());
        importBtn.setOnAction(event -> importButtonClicked());
        removeBtn.setOnAction(event -> removeButtonClicked());
        quitBtn.setOnAction(event -> quitButtonClicked());
    }

    // Méthode pour mettre à jour la table avec les données actuelles
    private void updateTable() {
        table.setItems(getPeople());
    }

    // Méthode pour obtenir la liste de personnes à afficher dans la table
    private ObservableList<Person> getPeople() {
        // Ici, vous pouvez retourner la liste de personnes à partir de votre objet DataClass
        return FXCollections.observableArrayList(data.getImportList());
    }

    @FXML
private void addButtonClicked() {
    // Récupérer les valeurs des champs de texte
    String lastName = tfLastName.getText();
    String firstName = tfFirstName.getText();
    String email = tfEmail.getText();

    // Créer un nouvel objet Person
    Person newPerson = new Person(firstName, lastName, email);

    // Ajouter la personne à la liste
    data.getImportList().add(newPerson);

    // Mettre à jour la table
    updateTable();

    // Effacer les champs de texte
    clearFields();
}

    // Méthode associée au bouton "Exporter"
    @FXML
    private void exportButtonClicked() {
        // Logique pour exporter les contacts
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();
        data.setExportList(selectedPersons);
    }

    // Méthode associée au bouton "Importer"
    @FXML
    private void importButtonClicked() {
        // Logique pour importer des contacts
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();
        data.getImportList().addAll(selectedPersons);
        updateTable();
    }

    // Méthode associée au bouton "Supprimer"
    @FXML
    private void removeButtonClicked() {
        // Logique pour supprimer une personne
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();
        data.getImportList().removeAll(selectedPersons);
        updateTable();
    }

    // Méthode associée au bouton "Quitter"
    @FXML
    private void quitButtonClicked() {
        // Logique pour quitter l'application
        System.exit(0);
    }

    // Méthode pour effacer les champs de texte
    private void clearFields() {
        tfLastName.clear();
        tfFirstName.clear();
        tfEmail.clear();
    }
}
