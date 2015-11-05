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
    
    ChilExploxApp main;
    Truck truck;
    private ArrayList<ParcelImage> trucksParcelsImgs = new ArrayList();
    private ArrayList<ParcelImage> restOfParcelsImgs = new ArrayList();
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
        
        for(Parcel p: this.truck.getParcels()){
            trucksParcelsImgs.add(new ParcelImage(p,this));
        }
        
        Map<String,Order> subOrders = this.main.getChilExplox().getCurrentSubsidiary().getOrders();
        for (String key: subOrders.keySet()){
            for(Parcel p: subOrders.get(key).getParcels()){
                restOfParcelsImgs.add(new ParcelImage(p,this));
            }
        }

        for (ParcelImage pi: trucksParcelsImgs ){
            truckTile.getChildren().add(pi.view);
        }
        for (ParcelImage pi: restOfParcelsImgs ){
            restTile.getChildren().add(pi.view);
        }

    }
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
       
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
        initTableView();
    }
    
    @FXML
    private void returnScene(ActionEvent event){
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }
    
    @FXML
    public void onDragEntered(DragEvent event)
    {
        if (event.getSource() != event.getTarget() && event.getDragboard().hasString())
        {
            TilePane pane = (TilePane) event.getGestureTarget();
            
            event.acceptTransferModes(TransferMode.MOVE);
            pane.setStyle(
            "-fx-border-color: tomato;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
        }
        event.consume();
    }
    @FXML
    public void onDragExited(DragEvent event)
    {
        TilePane pane = (TilePane) event.getGestureTarget();
        pane.setStyle("-fx-border-color: transparent;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
        event.consume();
    }
    
}
