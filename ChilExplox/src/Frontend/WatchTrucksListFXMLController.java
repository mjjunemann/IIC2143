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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class WatchTrucksListFXMLController implements Initializable, iController {


    
    ChilExploxApp main;
    @FXML
    private Button returnButton;
    @FXML
    private ListView<String> trucksListView; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        initializeListView();
    }

    @FXML
    private void returnToSubsidary(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
    }
    
    private void initializeListView(){
        Map<String,ITransport> transports = 
                this.main.getChilExplox().getCurrentSubsidiary().getVehicles();
        ObservableList<String> trucksStringList = 
                FXCollections.observableArrayList();
        for (String key: transports.keySet()){
            Truck transport = (Truck) transports.get(key);
            if (transport.getAvaibility() == State.Origin){
                String transportRepresentation = key + 
                        "\t \t capacidad: " + transport.checkSpace();
                trucksStringList.add(transportRepresentation);
            }
        }
        
        trucksListView.setItems(trucksStringList);

    }
    
}
