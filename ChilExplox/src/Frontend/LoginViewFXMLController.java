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
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginViewFXML.fxml'.";

    }   
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
    }

    @FXML
    private void loginUser(ActionEvent event) {
        System.out.print("Logged-in as: "+ usernameTextField.getText() +" with the password "+ passwordTextField.getText() +" \n");
        main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
        
    }
    
    
}
