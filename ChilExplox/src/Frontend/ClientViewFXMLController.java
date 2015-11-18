/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class ClientViewFXMLController implements Initializable, iController {

    /**
     * Initializes the controller class.
     */
    ChilExploxApp main;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button newOrderButton;
    @FXML
    private Button viewOrdersButton;
    @FXML
    private Button watchParcelsButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
    }

    @FXML
    private void logoutClient(ActionEvent event) {
        main.changeScene("LoginViewFXML.fxml", LoginViewFXMLController.class);
    }

    @FXML
    private void createNewOrder(ActionEvent event) {
        this.changeSceneNewOrderFromClient();
    }
    
    private void changeSceneNewOrderFromClient(){
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("CreateOrderViewFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            CreateOrderViewFXMLController controller = loader.getController();
            
            controller.setChilExploxApp(this.main);
            controller.initializeWithClient(this.main.getChilExplox().getCurrentClient());
            
         
            this.main.changeSceneFromPage(page);
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewOrders(ActionEvent event) {
        main.changeScene("WatchOrdersViewFXML.fxml", WatchOrdersViewFXMLController.class);

    }

    @FXML
    private void watchParcels(ActionEvent event) {
        main.changeScene("WatchParcelsFXML.fxml",WatchParcelsFXMLController.class);   

    }
    
}
