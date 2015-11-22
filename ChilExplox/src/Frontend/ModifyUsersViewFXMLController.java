/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class ModifyUsersViewFXMLController implements Initializable, iController {

    ChilExploxApp main;
    @FXML
    private Button returnButton;
    @FXML
    private Button newUserButton;
    @FXML
    private Button saveUserButton;
    @FXML
    private Button removeUserButton;
    @FXML
    private TextField name;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeated_password;
    @FXML
    private ChoiceBox<Role> role;
    
    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disableFields();
        
        ObservableList<Role> roles_list = 
                FXCollections.observableArrayList(Role.usersValues());
        role.setItems(roles_list);

    }  

    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
        initializeUsersTableView();
    }

    @FXML
    private void returnToSubsidiary(ActionEvent event) {
        this.main.changeScene("SubsidiaryViewFXML.fxml", 
                SubsidiaryViewFXMLController.class);
    }

    @FXML
    private void newUser(ActionEvent event) {
        //Fields text are reseated
        cleanUsersView();
        
       //All fields are enabled
        enableFields();
    }
    
    public void disableFields(){
        user.setDisable(true);
        name.setDisable(true);
        password.setDisable(true);
        repeated_password.setDisable(true);
        role.setDisable(true);
    }

    public void enableFields(){
        user.setDisable(false);
        name.setDisable(false);
        password.setDisable(false);
        repeated_password.setDisable(false);
        role.setDisable(false);
    }
    
    private boolean checkUserInputs(){
        if (name.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "Nombre", 
                    "Ingrese un nombre");
            return false;
        }
        if (user.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "Usuario", 
                    "Ingrese un usuario");
            return false;
        }
        if (password.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "Contraseña", 
                    "Ingrese una contraseña");
            return false;
        }
        if (repeated_password.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "Repetición contraseña", 
                    "Repita la contraseña");
            return false;
        }
        try {
            InputValidator.CheckName(name.getText());
        }
        catch (Exception e){
            ShowAlert.alertWithField(e, "nombre");
            if (repeated_password.getText() == null){
                ShowAlert.alertWithFieldAndMessage(
                        "repetición contraseña",
                        "Ingrese la contraseña de nuevo");
                return false;
            }
            return false;
        }
        if (password.getText().equals(repeated_password.getText())){
            ShowAlert.message("Usuario creado", "Usuario creado exitosamente");
            return true;
        }
        ShowAlert.alertWithFieldAndMessage(
                "contraseña",
                "Las contraseñas no coinciden");
        return false;
              
    }
    
    @FXML
    private void saveUser(ActionEvent event) {
        if (checkUserInputs()){
            if (this.main.getChilExplox().addUser(
                    user.getText(),
                    name.getText(), 
                    password.getText(), 
                    role.getValue())){
                
                refreshUsers();
                cleanUsersView();
                disableFields();
            }
            else{
                ShowAlert.alertWithFieldAndMessage(
                        "Usuario Repetido", 
                        "El usuario ya existe");
            }

        }
    }
    
    @FXML
    private void removeUser(ActionEvent event) {
        if (ShowAlert.confirmation(
                "Borrar Usuario", 
                "¿Esta seguro que desea borrar el usuario\n"
                        + "Esta acción no es reversible")){
            cleanUsersView();
            disableFields();
        }
        
    }
    
    private void cleanUsersView(){
        user.setText(null);
        name.setText(null);
        password.setText(null);
        repeated_password.setText(null);
    }
    
    private void initializeUsersTableView(){
        userColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getUsername()));
        nameColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getName()));
        roleColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getRole().toString()));
        refreshUsers();
    }
    
    private void refreshUsers(){
        ObservableList<User> users = 
                FXCollections.observableArrayList(
                        this.main.getChilExplox().getUsers());
        usersTableView.setItems(users);
    }
    
  
    
}
