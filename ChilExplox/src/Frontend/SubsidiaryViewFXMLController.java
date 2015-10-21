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
    
    ChilExploxApp main;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void createNewOrder(ActionEvent event) {
        System.out.println("New order created");
    }
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
    }
    
    
    
}
