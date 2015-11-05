/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Parcel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    }
    public void updateImage(){
        img = new Image("images/parcel.png",60,60,false,false);
    }
}
