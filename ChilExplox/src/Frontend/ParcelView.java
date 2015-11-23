/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;
    
import Backend.Parcel;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Fernando
 */
class ParcelView {
    final Parcel parcel;
    Image img;
    final ImageView view;
    final Button button;
    UnloadParcelFXMLController controller;
    Tooltip tool;
    
    public ParcelView(Parcel parcel){
        this.parcel=parcel;
        updateImage();
        this.view = new ImageView(this.img);
        this.button = new Button();
        this.button.setGraphic(this.view);
    }
    
    public void setMouseevent(UnloadParcelFXMLController c){
        this.view.setStyle("-fx-cursor:hand;");
        this.controller = c;
        this.button.setOnMouseClicked((MouseEvent t) -> {
            controller.idParcelLabel.setText(this.parcel.getId());
            controller.destinationParcelLabel.setText(this.parcel.getDestination().toString());
            controller.selectedParcel = view;
            controller.typeParcelLabel.setText(this.parcel.getType().toString());
            controller.stateParcelLabel.setText(this.parcel.getState().toString());
            controller.volumeParcelLabel.setText(String.valueOf(this.parcel.getVolume()));
            controller.weightParcelLabel.setText(String.valueOf(this.parcel.getWeight()));
            controller.priorityParcelLabel.setText(String.valueOf(this.parcel.getPriority()));
        });
    }
    
    public void setToolTip(){
        this.tool = new Tooltip();
        this.tool.setText(String.format("ID: %s\n"
                    + "Dest: %s\nType: %s\n"
                    + "State: %s\nVol: %f\n"
                    + "Wei: %f\nPriority: %d", this.parcel.getId(),
                    this.parcel.getDestination().toString(), 
                    this.parcel.getType().toString(),
                    this.parcel.getState().toString(),
                    this.parcel.getVolume(),
                    this.parcel.getWeight(),
                    this.parcel.getPriority()));
        this.button.setTooltip(this.tool);
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
