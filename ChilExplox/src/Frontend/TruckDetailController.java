/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Order;
import Backend.Parcel;
import Backend.State;
import Backend.Truck;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class TruckDetailController implements Initializable, iController {

    @FXML
    TilePane truckTile;
    @FXML
    TilePane restTile;
    @FXML
    Button sendButton;
    
    @FXML
    public Label plateTruckLabel;
    @FXML
    public Label typeTruckLabel;
    @FXML
    public Label stateTruckLabel;
    @FXML
    public Label destinationTruckLabel;
    @FXML
    public Label nParcelsTruckLabel;
    @FXML
    public Label maxSpaceLabel;
    
    @FXML
    public Label destinationParcelLabel;
    @FXML
    public Label idParcelLabel;
    @FXML
    public Label stateParcelLabel;
    @FXML
    public Label typeParcelLabel;
    @FXML
    public Label weightParcelLabel;
    @FXML
    public Label volumeParcelLabel;

    
    ChilExploxApp main;
    Truck truck;
    private Map<ImageView,ParcelImage> trucksParcelsImgs = new HashMap();
    private Map<ImageView,ParcelImage> restOfParcelsImgs = new HashMap();
    private Parcel selectedParcel;
    //<editor-fold desc="FXML">
    
    //</editor-fold>
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void initTableView()
    {
        truckTile.setHgap(5);
        truckTile.setVgap(5);
        restTile.setHgap(5);
        restTile.setVgap(5);
        selectedParcel = null;
        
        ParcelImage pi;
        for(Parcel p: this.truck.getParcels()){
            pi = new ParcelImage(p,this);
            trucksParcelsImgs.put(pi.view, pi);
            truckTile.getChildren().add(pi.view);
        }
        
        Map<String,Order> subOrders = this.main.getChilExplox().getCurrentSubsidiary().getOrders();
        for (String key: subOrders.keySet()){
            for(Parcel p: subOrders.get(key).getParcels()){
                if (p.getState()== State.Origin && !this.truck.getParcels().contains(p)) {
                    pi = new ParcelImage(p,this);
                    restOfParcelsImgs.put(pi.view,pi);
                    restTile.getChildren().add(pi.view);
                }
            }
        }
        if (truck.getAvaibility()==State.OriginError) {
            sendButton.setVisible(false);
        }

    }
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
       
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
        plateTruckLabel.setText(truck.getPlate());
        typeTruckLabel.setText(truck.getType().toString());
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
    public void onDragOver(DragEvent event)
    {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasString())
        {
            try{
            TilePane pane = (TilePane) event.getTarget();
            
            event.acceptTransferModes(TransferMode.MOVE);
            pane.setStyle(
            "-fx-border-color: steelblue;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
            }catch(Exception e){}
        }
        event.consume();
    }
    @FXML
    public void onDragExited(DragEvent event)
    {
        TilePane pane = (TilePane) event.getTarget();
        pane.setStyle("-fx-border-color: transparent;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
        event.consume();
    }
    @FXML
    public void onDragDropped(DragEvent event)
    {
        TilePane paneTarget = (TilePane) event.getTarget();
        ImageView im = (ImageView) event.getGestureSource();
        TilePane paneSource = (TilePane)im.getParent();
        System.out.println(paneTarget.getId());
        System.out.println(im);
        System.out.println(paneSource.getId());
        System.out.println(paneTarget.getId().compareTo("truckTile"));
        
        Parcel p;
        
        if (paneTarget.getId().compareTo("truckTile") == 0) 
        {
            p = restOfParcelsImgs.get(im).parcel;
            System.out.println(this.truck.getDestinyString());
            System.out.println(p.getDestination().toString());
            System.out.println(this.truck.canParcelLoad(p));
            if (this.truck.canParcelLoad(p)) {
                this.truck.loadParcel(p);
                this.destinationTruckLabel.setText(truck.getDestinyString());
                this.nParcelsTruckLabel.setText(String.valueOf(truck.getParcels().size()));
                paneSource.getChildren().remove(im);
                paneTarget.getChildren().add(im);
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ChilExplox");
                alert.setHeaderText("Load Error - Can't Load.");
                alert.setResizable(false);
                String content = String.format("Only can load on this truck parcels:\n Of type: %s.\n And that go to: %s",truck.getType().toString(),truck.getDestinyString());
                alert.setContentText(content);
                alert.showAndWait();
            }
        }
        else
        {
            p = restOfParcelsImgs.get(im).parcel;
            if (this.truck.unload(p)) {
                System.out.println("unload");
                paneSource.getChildren().remove(im);
                paneTarget.getChildren().add(im);
                this.destinationTruckLabel.setText(truck.getDestinyString());
                this.stateTruckLabel.setText(truck.getState().toString());
                this.nParcelsTruckLabel.setText(String.valueOf(
                                                truck.getParcels().size()));
            }else{
                System.out.println("didnt unload");
            }
        }
        event.setDropCompleted(true);
        event.consume();
    }
    @FXML
    void sendTruck(){
        if (this.truck.getParcels().size() != 0) {
            this.main.getChilExplox().getCurrentSubsidiary().sendsVehicle(truck,
                    main.getChilExplox().getSubsidiary( truck.getDestiny()));
            this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
        }
    }
}
