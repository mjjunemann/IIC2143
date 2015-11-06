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
class ParcelView {
    final Parcel parcel;
    Image img;
    final ImageView view;
    final UnloadParcelFXMLController controller;
    
    public ParcelView(Parcel parcel, UnloadParcelFXMLController c){
        this.parcel=parcel;
        this.controller = c;
        updateImage();
        this.view = new ImageView(this.img);
        this.view.setStyle("-fx-cursor:hand;");
        this.view.setOnMouseClicked((MouseEvent t) -> {
            c.idParcelLabel.setText(this.parcel.getId());
            c.destinationParcelLabel.setText(this.parcel.getDestination().toString());
            c.selectedParcel = view;
        });
        
    }
    public void updateImage(){
        img = new Image("images/parcel.png",60,60,false,false);
    }
}
