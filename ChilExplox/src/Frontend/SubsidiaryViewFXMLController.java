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
    private Button viewOrdersButton;
    @FXML 
    private Button viewTrucksButton;
    @FXML
    private Label welcomeLabel;
    
    ChilExploxApp main;
    Subsidiary current_subsidiary;
    User logged_user;

    
    @FXML
    private Button viewMessagesButton;
    @FXML
    private Button newUserButton;
    @FXML
    private Button newSubsidiaryButton;
    @FXML
    private Button newTruckButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void createNewOrder(ActionEvent event) {
        System.out.println("New order created");
        this.changeSceneWithOutOrder();
        

    }
    
     private void changeSceneWithOutOrder(){
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("CreateOrderViewFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            CreateOrderViewFXMLController controller = loader.getController();
            
            controller.setChilExploxApp(this.main);
            controller.initializeWithoutOrder();
         
            this.main.changeSceneFromPage(page);
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.current_subsidiary = main.getChilExplox().getCurrentSubsidiary();
        this.logged_user = main.getChilExplox().getCurrentUser();
        
        this.welcomeLabel.setText("Bienvenido "+this.logged_user.getName()
                                    + " \nHas ingresado via la sucursal en \n"
                                    + this.current_subsidiary.getAddress());
        Role user_role = this.logged_user.getRole();
        if (user_role.equals(Role.Administrator)){
            this.newUserButton.setVisible(true);
            this.newTruckButton.setVisible(true);
            this.newSubsidiaryButton.setVisible(true);
        }
    }
    
    @FXML
    private void logoutUser(ActionEvent event){
        main.changeScene("LoginViewFXML.fxml", LoginViewFXMLController.class);
    }

    
    @FXML
    private void watchParcels(ActionEvent event)
    {
     main.changeScene("WatchParcelsFXML.fxml",WatchParcelsFXMLController.class);   
    }
    @FXML
    private void watchTrucks(ActionEvent event) {
        main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }
    
    @FXML
    private void openMailbox(ActionEvent event){
        main.changeScene("MailboxViewFXML.fxml", MailboxViewFXMLController.class);
    }

    @FXML
    private void viewOrders(ActionEvent event){
        main.changeScene("WatchOrdersViewFXML.fxml", WatchOrdersViewFXMLController.class);
    }

    @FXML
    private void createNewUser(ActionEvent event) {
    }

    @FXML
    private void createNewSubsidiary(ActionEvent event) {
    }

    @FXML
    private void createNewTruck(ActionEvent event) {
    }
    
    
}
