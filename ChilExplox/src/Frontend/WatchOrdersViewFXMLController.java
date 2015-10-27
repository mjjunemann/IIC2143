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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private ArrayList<Order> ordersShown;

    private ObservableList<String> ordersList;
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
    /*
    private void initializeOrders()
    {
        
    }
    */
    private void initializeOrders(){
        ordersList = FXCollections.observableArrayList();
        ordersShown = new ArrayList<Order>();
        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            ordersList.add(order.toString());
            ordersShown.add(order);
        }
        ordersListView.setItems(ordersList);
    }

    @FXML
    private void searchId(ActionEvent event) {
        ordersList = FXCollections.observableArrayList();
        ordersShown = new ArrayList<Order>();
        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            if (searchTextField.getText().equals(order.getId()) ||
                    searchTextField.getText().equals("")) {
                ordersList.add(order.toString());
                ordersShown.add(order);
            }
        }
        ordersListView.setItems(ordersList);
    }
    
    @FXML
    private void modifyOrder(MouseEvent event) {
        int orderSelectedPosition = ordersListView.getSelectionModel().
                getSelectedIndex();
        Order orderSelected = ordersShown.get(orderSelectedPosition);
        changeSceneToModifyOrder(orderSelected);
        
    }
    
    private void changeSceneToModifyOrder(Order order){
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("CreateOrderViewFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            CreateOrderViewFXMLController controller = loader.getController();
            
            controller.setChilExploxApp(this.main);
            controller.initializeWithOrder(order);
         
        
            this.main.changeSceneFromPage(page);
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
