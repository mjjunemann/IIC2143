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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
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
    private TilePane TruckTile;
    @FXML
    public TilePane parcelTile;
    @FXML
    public Label plateLabel;
    @FXML
    public Label typeLabel;
    @FXML
    public Label stateLabel;
    @FXML
    public Label destinationLabel;
    @FXML
    public Label nParcelsLabel;
    @FXML
    private Label truckTitle;
    
    
    public boolean muestraLocal = true;
    public Truck selectedTruck;
    private ArrayList<TruckImage> localTrucksImgs;
    private ArrayList<TruckImage> arrivedTrucksImgs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        initializeTile();
    }
    @FXML
    private void returnToSubsidary(ActionEvent event) { main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class); }
    
    private void initializeTile(){
        TruckTile.setHgap(5);
        TruckTile.setVgap(5);
        selectedTruck = null;
        
        Map<String,ITransport> local = 
                this.main.getChilExplox().getCurrentSubsidiary().getVehicles();
        ArrayList<ITransport> arrived = 
                this.main.getChilExplox().getCurrentSubsidiary().getArrivedVehicles();
        
        localTrucksImgs = new ArrayList();
        arrivedTrucksImgs = new ArrayList();
        
        for (String key: local.keySet()){
            Truck transport = (Truck) local.get(key);
            localTrucksImgs.add(new TruckImage(transport,this));
        }
        for (ITransport t: arrived){
            arrivedTrucksImgs.add(new TruckImage((Truck) t,this));
        }
        loadLocalTrucks(new ActionEvent());

    }   
    @FXML
    private void loadLocalTrucks(ActionEvent event ){
        TruckTile.getChildren().clear();
        Button b;
        for (TruckImage ti: localTrucksImgs ){
            TruckTile.getChildren().add(ti.button);
        }
        muestraLocal = true;
        clearLabels();
    }
    @FXML
    private void loadArrivedTrucks(ActionEvent event ){
        TruckTile.getChildren().clear();
        for (TruckImage ti:  arrivedTrucksImgs){
            TruckTile.getChildren().add(ti.button);
        }
        muestraLocal = false;
        clearLabels();
    }
    
    private void clearLabels(){
        plateLabel.setText("-");
        stateLabel.setText("-");
        destinationLabel.setText("-");
        nParcelsLabel.setText("-");
        typeLabel.setText("-");
        selectedTruck = null;
        parcelTile.getChildren().clear();
    }
    
    @FXML
    public void seeContent(ActionEvent event){
        
        if (selectedTruck != null && 
                (selectedTruck.getAvaibility() == State.Origin || 
                selectedTruck.getAvaibility() == State.OriginError)) {
            changeSceneToTruck(selectedTruck);
        }
    }
    @FXML
    private void unloadParcel(ActionEvent event){
        if (selectedTruck != null) {
           changeSceneToUnloadParcel(selectedTruck);
        }
    }
    public void changeSceneToTruck(Truck truck){
        
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("TruckDetail.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            TruckDetailController controller = loader.getController();
            controller.setChilExploxApp(this.main);
            controller.setTruck(truck);
            
            this.main.changeSceneFromPage(page);
            
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void changeSceneToUnloadParcel(Truck truck){
        
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("UnloadParcelFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            UnloadParcelFXMLController controller = loader.getController();
            controller.setChilExploxApp(this.main);
            controller.setTruck(truck);
            
            this.main.changeSceneFromPage(page);
            
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

}