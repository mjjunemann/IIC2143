/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Parcel;
import Backend.State;
import Backend.Truck;
import Backend.Type;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Fernando
 */
public class TruckImage {
    
    final Truck truck;
    Image img;
    final ImageView view;
    final WatchTrucksListFXMLController controller;
    final Button button;
    final Tooltip tool;
    
    public TruckImage(Truck truck, WatchTrucksListFXMLController c){
        this.truck=truck;
        this.controller = c;
        updateImage();
        this.view = new ImageView(this.img);
        this.button = new Button();
        this.button.setGraphic(this.view);
        this.tool = new Tooltip();
        this.tool.setText(String.format("Plate: %s\nClick para ver su info!\nDoble Click para cargar!", truck.getPlate()));
        this.button.setTooltip(this.tool);
        this.view.setStyle("-fx-cursor:hand;");
        this.button.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 1) {
                controller.plateLabel.setText(truck.getPlate());
                controller.typeLabel.setText(truck.getType().toString());
                controller.stateLabel.setText(truck.getAvaibility().toString());
                controller.destinationLabel.setText(truck.getDestinyString());
                controller.nParcelsLabel.setText(String.valueOf(truck.getParcels().size()));
                controller.selectedTruck = truck;
                controller.parcelTile.getChildren().clear();
                ParcelView pv;
                for (Parcel p: truck.getParcels()) {
                    pv = new ParcelView(p);
                    pv.setToolTip();
                    controller.parcelTile.getChildren().add(pv.button);
                }
            }else{
                if (controller.muestraLocal && 
                        (this.truck.getAvaibility()==State.Origin ||
                        this.truck.getAvaibility()==State.OriginError) ) {
                    controller.changeSceneToTruck(this.truck);
                }else{
                    controller.changeSceneToUnloadParcel(this.truck);
                }
            }
            
        });
    }
    public void updateImage(){
        switch(this.truck.getType()){
            case Normal:
                if (this.truck.getAvaibility() == State.Origin){
                    img = new Image("images/truck_normal.png",120,120,false,false);
                }else if(this.truck.getAvaibility() == State.OriginError){
                    img = new Image("images/truck_normal_error.png",120,120,false,false);
                }
                else{
                    img = new Image("images/truck_normal_red.png",120,120,false,false);
                }
                break;
            case Radioactive:
                if (this.truck.getAvaibility() == State.Origin){
                    img = new Image("images/truck_radioactive.png",120,120,false,false);
                }else if(this.truck.getAvaibility() == State.OriginError){
                    img = new Image("images/truck_radioactive_error.png",120,120,false,false);
                }
                else{
                    img = new Image("images/truck_radioactive_red.png",120,120,false,false);
                }
                break;
            case Fragile:
                if (this.truck.getAvaibility() == State.Origin){
                    img = new Image("images/truck_fragile.png",120,120,false,false);
                }else if(this.truck.getAvaibility() == State.OriginError){
                    img = new Image("images/truck_fragile_error.png",120,120,false,false);
                }
                else{
                    img = new Image("images/truck_fragile_red.png",120,120,false,false);
                }
                break;
            case Refrigerated:
                if (this.truck.getAvaibility() == State.Origin){
                    img = new Image("images/truck_refrigerate.png",120,120,false,false);
                }else if(this.truck.getAvaibility() == State.OriginError){
                    img = new Image("images/truck_refrigerate_error.png",120,120,false,false);
                }
                else{
                    img = new Image("images/truck_refrigerate_red.png",120,120,false,false);
                }
                break;
        }
        
    }
}
