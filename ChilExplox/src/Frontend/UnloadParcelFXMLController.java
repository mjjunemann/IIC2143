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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class UnloadParcelFXMLController implements Initializable, iController {

    ChilExploxApp main;
    Truck truck;
    ImageView selectedParcel;
    private Map<ImageView,ParcelView> trucksParcelsImgs = new HashMap();
    
    @FXML
    public TilePane parcelTile;
    @FXML
    public Label plateTruckLabel;
    @FXML
    public Label stateTruckLabel;
    @FXML
    public Label destinationTruckLabel;
    @FXML
    public Label nParcelsTruckLabel;
    @FXML
    public Label maxSpaceLabel;
    @FXML
    public Label idParcelLabel;
    @FXML
    public Label destinationParcelLabel;
    @FXML
    public Label stateParcelLabel;
    @FXML
    public Label typeParcelLabel;
    @FXML
    public Label weightParcelLabel;
    @FXML
    public Label volumeParcelLabel;
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
    }
    public void initTableView()
    {
        parcelTile.setHgap(5);
        parcelTile.setVgap(5);
        selectedParcel = null;
        ParcelView pv;
        for(Parcel p: this.truck.getParcels()){
            pv = new ParcelView(p);
            pv.setMouseevent(this);
            trucksParcelsImgs.put(pv.view, pv);
            parcelTile.getChildren().add(pv.view);
        }
        
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
        plateTruckLabel.setText(truck.getPlate());
        stateTruckLabel.setText(truck.getAvaibility().toString());
        destinationTruckLabel.setText(truck.getDestinyString());
        nParcelsTruckLabel.setText(String.valueOf(truck.getParcels().size()));
        maxSpaceLabel.setText(String.valueOf(this.truck.getMaxParcels()));
        initTableView();
    }
    @FXML
    private void returnScene(ActionEvent event){
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }  
    @FXML
    private void unloadParcel(ActionEvent event){
        if (selectedParcel != null) {
            truck.unloadArrived(trucksParcelsImgs.get(selectedParcel).parcel);
            parcelTile.getChildren().remove(selectedParcel);
            stateTruckLabel.setText(truck.getAvaibility().toString());
            destinationTruckLabel.setText(truck.getDestinyString());
            nParcelsTruckLabel.setText(String.valueOf(truck.getParcels().size()));
            selectedParcel = null;
        }
    }
    @FXML 
    private void sendTruckBack(ActionEvent event){
        if (truck.getParcels().size()==0) {
            main.getChilExplox().getCurrentSubsidiary().sendBack(truck);
            this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
        }
    }
}
