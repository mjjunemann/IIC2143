/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class LoginViewFXMLController implements Initializable, iController {
    @FXML
    private Button loginButton;
    private Backend.ChilExplox chilexplox = new Backend.ChilExplox();
    
    @FXML
    private TextField passwordTextField; 
    @FXML
    private TextField usernameTextField;

    
    
    ChilExploxApp main;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }   
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
    }

    @FXML
    private void loginUser(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Address address = new Address("Amapolas",1500,"Providencia","Santiago");
 
        if (main.getChilExplox().login(username, password, address)){
            main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
        }
    }
    
    
}
