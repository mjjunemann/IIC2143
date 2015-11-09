/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.KeyCode;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class LoginViewFXMLController implements Initializable, iController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private Button loginButton;
    
    private Backend.ChilExplox chilexplox = new Backend.ChilExplox();
    
    @FXML
    private TextField passwordTextField; 

    @FXML
    private ListView<String> addressList = new ListView<String>();
    
    
    ChilExploxApp main;

    @FXML
    private TextField rutTextField;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void setItemsListView(){
        ObservableList<String> addressStringList = 
                FXCollections.observableArrayList();
        ArrayList<Address> subsidiariesAddress = 
                this.main.getChilExplox().getSubsidiariesAddress();
        for (Address address : subsidiariesAddress) {
            addressStringList.add(address.getAddress());
        }
        addressList.setItems(addressStringList);  
    }
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        setItemsListView();

        
    }

    @FXML
    private void onEnter(KeyEvent e)
    {
            
     if(e.getCode().equals(KeyCode.ENTER))
     {
      this.login();
     }
    }
    @FXML
    private void loginUser(ActionEvent event)
    {
        this.login();
    }
    
    private void login(){
        if (rutTextField.visibleProperty().getValue()){
            this.loginAsClient();
        }
        else{
            this.loginAsUser();
        }
    }
    
    private void loginAsClient(){
        String rut = rutTextField.getText();
        int positionSelected = addressList.getSelectionModel().getSelectedIndex();
        if (positionSelected >= 0){
            Address address = this.main.getChilExplox().getSubsidiariesAddress().get(positionSelected);
            if (this.main.getChilExplox().loginClient(rut, address)){
                this.main.changeScene("ClientViewFXML.fxml", ClientViewFXMLController.class);
            }
        } 
    }
    
    private void loginAsUser()
    {
      String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        int positionSelected = addressList.getSelectionModel().getSelectedIndex();
        if (positionSelected >= 0){
            Address address = this.main.getChilExplox().getSubsidiariesAddress().get(positionSelected);
            if (this.main.getChilExplox().login(username, password, address)){
                this.main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
            }
        }  
    }

    @FXML
    private void selectUserLogin(Event event) {
        if (rutTextField != null){
            rutTextField.setVisible(false);
            usernameTextField.setVisible(true);
            passwordTextField.setVisible(true);
        }
    }

    @FXML
    private void selectClientLogin(Event event) {
        rutTextField.setVisible(true);
        usernameTextField.setVisible(false);
        passwordTextField.setVisible(false);
    }
    
}
