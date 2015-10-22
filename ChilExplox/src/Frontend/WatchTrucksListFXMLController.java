/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class WatchTrucksListFXMLController implements Initializable, iController {


    
    ChilExploxApp main;
    @FXML
    private Button returnButton;
    @FXML
    private TilePane arrivedTruckTile;
    @FXML
    private TilePane ownTruckTile;
    @FXML
    private AnchorPane gridAnchor;
    /*@FXML
    private ListView<String> trucksListView;*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        initializeListView();
    }

    @FXML
    private void returnToSubsidary(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
    }
    
    private void initializeListView(){
        Map<String,ITransport> transports = 
                this.main.getChilExplox().getCurrentSubsidiary().getVehicles();
        ObservableList<String> trucksStringList = 
                FXCollections.observableArrayList();
        for (String key: transports.keySet()){
            Truck transport = (Truck) transports.get(key);
            if (transport.getAvaibility() == State.Origin){
                String transportRepresentation = key + 
                        "\t \t capacidad: " + transport.checkSpace();
                trucksStringList.add(transportRepresentation);
            }
        }
        
        /*trucksListView.setItems(trucksStringList);*/
        Image im;
        ImageView imV ;
        for (int i=0; i < 3 ; i++){
            im = new Image("images/truck.png");
            imV = new ImageView();
            imV.setImage(im);
            imV.setFitWidth(75);
            /*
            imV.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                Parent root;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"));
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            }*/
            });
            imV.setPreserveRatio(true);
            imV.setSmooth(true);
            imV.setCache(true);
            arrivedTruckTile.getChildren().add(imV);
        }
        for (int i=0; i < 30 ; i++){
            im = new Image("images/truck.png");
            imV = new ImageView();
            imV.setImage(im);
            imV.setFitWidth(75);
            imV.setPreserveRatio(true);
            imV.setSmooth(true);
            imV.setCache(true);
            ownTruckTile.getChildren().add(imV);
        }
        
    }
    
}
