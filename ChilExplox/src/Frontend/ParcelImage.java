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
        this.view.setOnMouseClicked((MouseEvent t) -> {
            
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
        img = new Image("images/parcel.png",60,60,false,false);
    }
}
