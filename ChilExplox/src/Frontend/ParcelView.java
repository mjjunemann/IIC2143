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
    UnloadParcelFXMLController controller;
    
    public ParcelView(Parcel parcel){
        this.parcel=parcel;
        updateImage();
        this.view = new ImageView(this.img);
    }
    
    public void setMouseevent(UnloadParcelFXMLController c){
        this.view.setStyle("-fx-cursor:hand;");
        this.controller = c;
        this.view.setOnMouseClicked((MouseEvent t) -> {
            controller.idParcelLabel.setText(this.parcel.getId());
            controller.destinationParcelLabel.setText(this.parcel.getDestination().toString());
            controller.selectedParcel = view;
            controller.typeParcelLabel.setText(this.parcel.getType().toString());
            controller.stateParcelLabel.setText(this.parcel.getState().toString());
            controller.volumeParcelLabel.setText(String.valueOf(this.parcel.getVolume()));
            controller.weightParcelLabel.setText(String.valueOf(this.parcel.getWeight()));
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
