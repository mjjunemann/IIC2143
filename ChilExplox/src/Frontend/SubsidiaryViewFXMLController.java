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
public class SubsidiaryViewFXMLController implements Initializable, iController {

    @FXML
    private Button newOrderButton; 
    @FXML 
    private Button logoutButton;
    @FXML 
    private Button viewMessageButton;
    @FXML 
    private Button viewOrdersButton;
    @FXML 
    private Button viewTrucksButton;
    @FXML
    private Label welcomeLabel;
    
    ChilExploxApp main;
    Subsidiary current_subsidiary;
    User logged_user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void createNewOrder(ActionEvent event) {
        System.out.println("New order created");
        main.changeScene("CreateOrderViewFXML.fxml", CreateOrderViewFXMLController.class);

    }
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.current_subsidiary = main.getChilExplox().getCurrentSubsidiary();
        this.logged_user = main.getChilExplox().getCurrentUser();
        this.welcomeLabel.setText("Bienvenido "+this.logged_user.username
                                    + " \nHas ingresado via la sucursal en \n"
                                    + this.current_subsidiary.getAddress());
    }
    
    @FXML
    private void logoutUser(ActionEvent event){
        main.changeScene("LoginViewFXML.fxml", LoginViewFXMLController.class);
    }
    
    
    
}
