/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Address;
import Backend.Order;
import Backend.Parcel;
import Backend.Truck;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class LoadParcelFXMLController implements Initializable, iController {

    ChilExploxApp main;
    Truck truck;
    ObservableList<Parcel> truck_parcels;
    ObservableList<Parcel> subsidiary_parcels;
    @FXML
    TableView<Parcel> parcels;
    @FXML
    TableView<Parcel> truck_load;
    @FXML
    TableColumn<Parcel,String> truck_ids; 
    @FXML
    TableColumn<Parcel,String> parcel_ids; 
    @FXML
    TableColumn<Parcel,Float> truck_weights; 
    @FXML
    TableColumn<Parcel,Float> parcel_weights; 
    @FXML
    TableColumn<Parcel,Float> truck_volumes; 
    @FXML
    TableColumn<Parcel,Float> parcel_volumes; 
    @FXML
    TableColumn<Parcel,Address> truck_destination; 
    @FXML
    TableColumn<Parcel,Address> parcel_destination; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableViews();
    }
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        ArrayList<Order> orders = new ArrayList<Order>(this.main.getChilExplox().getCurrentSubsidiary().getOrders().values());
        orders.stream().forEach(order-> {
            subsidiary_parcels.addAll(order.getParcel());
        });
    }
    
    public void initializeTableViews(){
        parcel_ids.setCellValueFactory(i->i.getValue().idProperty());
        parcel_volumes.setCellValueFactory(i->i.getValue().volumeProperty().asObject());
        parcel_weights.setCellValueFactory(i->i.getValue().weightProperty().asObject());
        parcel_destination.setCellValueFactory(i->i.getValue().destinationProperty());
        
        truck_ids.setCellValueFactory(i->i.getValue().idProperty());
        truck_volumes.setCellValueFactory(i->i.getValue().volumeProperty().asObject());
        truck_weights.setCellValueFactory(i->i.getValue().weightProperty().asObject());
        truck_destination.setCellValueFactory(i->i.getValue().destinationProperty());
        
        truck_parcels = FXCollections.observableArrayList(Parcel.extractor());
        subsidiary_parcels = FXCollections.observableArrayList(Parcel.extractor());
        
        truck_load.setItems(truck_parcels);
        parcels.setItems(subsidiary_parcels);
        
    }
    public void setTruck(Truck truck){
        this.truck = truck;
        this.truck.getParcels().stream().forEach(p->truck_parcels.add(p));
        
    }
    @FXML
    private void returnScene(ActionEvent event){
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }
    
}
