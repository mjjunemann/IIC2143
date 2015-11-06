/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.State;
import Backend.Truck;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
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
    
    public TruckImage(Truck truck, WatchTrucksListFXMLController c){
        this.truck=truck;
        this.controller = c;
        updateImage();
        this.view = new ImageView(this.img);
        this.view.setOnMouseClicked((MouseEvent t) -> {
            controller.plateLabel.setText(truck.getPlate());
            controller.stateLabel.setText(truck.getAvaibility().toString());
            controller.destinationLabel.setText(truck.getDestinyString());
            controller.nParcelsLabel.setText(String.valueOf(truck.getParcels().size()));
            controller.selectedTruck = truck;
        });
    }
    public void updateImage(){
        if (this.truck.getAvaibility() == State.Origin) {
            img = new Image("images/truck.png",120,120,false,false);
        }
        else{
            img = new Image("images/truck_red.png",120,120,false,false);
        }
    }
}
