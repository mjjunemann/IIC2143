/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class WatchOrdersViewFXMLController implements Initializable, iController {

    
    ChilExploxApp main;
    @FXML
    private ListView<String> ordersListView;
    @FXML
    private Button returnToSubsidiaryButton;

    private ObservableList<String> ordersList = 
                FXCollections.observableArrayList();
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
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
        this.initializeOrders();
    }
    


    @FXML
    private void returnTuSubsidiary(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml",SubsidiaryViewFXMLController.class);
    }
    
    private void initializeOrders(){
        
        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            ordersList.add(order.toString());
        }
        ordersListView.setItems(ordersList);
    }

    @FXML
    private void searchId(ActionEvent event) {
        ordersList = FXCollections.observableArrayList();

        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            if (searchTextField.getText().equals(order.getId()) ||
                    searchTextField.getText().equals("")) {
                ordersList.add(order.toString());
            }
        }
        ordersListView.setItems(ordersList);
    }
    
    
}
