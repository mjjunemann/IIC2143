/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
    }
    
}
