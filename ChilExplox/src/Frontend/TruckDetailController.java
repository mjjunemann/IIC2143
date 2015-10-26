/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Order;
import Backend.Parcel;
import Backend.Truck;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class TruckDetailController implements Initializable, iController {

    
    ChilExploxApp main;
    Truck truck;
    private ObservableList<Parcel> parcels;
    //<editor-fold desc="FXML">
    @FXML
    private TableView<Parcel> truckContent;
    @FXML
    private TableColumn<Parcel,String> parcel_id;
    @FXML
    private TableColumn<Parcel,Float> volume;
    @FXML
    private TableColumn<Parcel,Float> weight;
    @FXML
    private TableColumn<Parcel,String> destination;
    //</editor-fold>
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
    }
    public void initTableView()
    {
        parcel_id.setCellValueFactory(i->i.getValue().idProperty());
        volume.setCellValueFactory(i->i.getValue().volumeProperty().asObject());
        weight.setCellValueFactory(i->i.getValue().weightProperty().asObject());
        // FALTA DESTINATION
        
        parcels = FXCollections.observableArrayList(Parcel.extractor());
        truckContent.setItems(parcels);

    }
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
       
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
        this.truck.getParcels().stream().forEach(p-> parcels.add(p));
    }
    
    @FXML
    private void returnScene(ActionEvent event){
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }

    
}
