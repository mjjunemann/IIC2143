/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Parcel;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Fernando
 */
class ParcelImage {
    final Parcel parcel;
    Image img;
    final ImageView view;
    final TruckDetailController controller;
    
    public ParcelImage(Parcel parcel, TruckDetailController c){
        this.parcel=parcel;
        this.controller = c;
        updateImage();
        this.view = new ImageView(this.img);
        this.view.setStyle("-fx-cursor:hand;");
        this.view.setOnMouseClicked((MouseEvent t) -> {
            controller.idParcelLabel.setText(this.parcel.getId());
            controller.destinationParcelLabel.setText(this.parcel.getDestination().toString());
            controller.typeParcelLabel.setText(this.parcel.getType().toString());
            controller.stateParcelLabel.setText(this.parcel.getState().toString());
            controller.volumeParcelLabel.setText(String.valueOf(this.parcel.getVolume()));
            controller.weightParcelLabel.setText(String.valueOf(this.parcel.getWeight()));
            controller.priorityParcelLabel.setText(String.valueOf(this.parcel.getPriority()));
        });
        this.view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
                System.out.print("hola");
                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(parcel.getId());
                db.setContent(content);

                event.consume();
            }
        });
    }
    public void updateImage(){
        switch(this.parcel.getType()){
            case Normal:
                img = new Image("images/parcel_normal.png",60,60,false,false);
                break;
            case Fragile:
                img = new Image("images/parcel_fragile.png",60,60,false,false);
                break;
            case Refrigerated:
                img = new Image("images/parcel_refrigerate.png",60,60,false,false);
                break;
            case Radioactive:
                img = new Image("images/parcel_radioactive.png",60,60,false,false);
                break;
        }
    }
}
