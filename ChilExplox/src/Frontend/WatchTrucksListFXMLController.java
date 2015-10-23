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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class WatchTrucksListFXMLController implements Initializable, iController {


    
    ChilExploxApp main;
    @FXML
    private Button backButton;
    @FXML
    private Button localTruckButton;
    @FXML
    private Button arrivedTruckButton;
    @FXML
    private Button viewContentButton;
    @FXML
    private Button loadParcelButton;
    @FXML
    private Button unloadParcelButton;
    @FXML
    private Button backTruckButton;
    @FXML
    private TableView<Truck> truckTable;
    @FXML
    private TableColumn<Truck,String> idColumn;
    @FXML
    private TableColumn<Truck,String> stateColumn;
    @FXML
    private TableColumn<Truck,Number> numberColumn;
    @FXML
    private TableColumn<Truck,String> destinyColumn;
    
    private boolean muestraLocal = true;
    
    
    private ObservableList<Truck> transportData = FXCollections.observableArrayList();
    /*@FXML
    private ListView<String> trucksListView;*/
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
        for (String key: transports.keySet()){
            Truck transport = (Truck) transports.get(key);
            transportData.add(transport);
        }
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));
        numberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty( cellData.getValue().getParcels().size()));
        destinyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestinyString()));
        
        truckTable.setItems(transportData);
    }   
    @FXML
    private void loadLocalTrucks(ActionEvent event ){
        transportData = FXCollections.observableArrayList();
        Map<String,ITransport> transports = 
                this.main.getChilExplox().getCurrentSubsidiary().getVehicles();
        for (String key: transports.keySet()){
            Truck transport = (Truck) transports.get(key);
            transportData.add(transport);
        }
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));
        numberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty( cellData.getValue().getParcels().size()));
        destinyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestinyString()));
        
        truckTable.setItems(transportData);
        
        muestraLocal = !muestraLocal;
    }
    @FXML
    private void loadArrivedTrucks(ActionEvent event ){
        transportData = FXCollections.observableArrayList();
        ArrayList<ITransport> transports = 
                this.main.getChilExplox().getCurrentSubsidiary().getArrivedVehicles();
        for (ITransport transport: transports){
            Truck truck = (Truck) transport;
            transportData.add(truck);
        }
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));
        numberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty( cellData.getValue().getParcels().size()));
        destinyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestinyString()));
        
        truckTable.setItems(transportData);
        
        muestraLocal = !muestraLocal;
    }
}