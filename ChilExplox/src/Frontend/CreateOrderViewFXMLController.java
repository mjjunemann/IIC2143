/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class CreateOrderViewFXMLController implements Initializable, iController {

    /**
     * Initializes the controller class.
     */
    ChilExploxApp main;
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
    }

    @FXML
    private void cancelOrder(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);

    }
    
}
