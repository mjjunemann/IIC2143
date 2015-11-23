/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Parcel;
import Backend.Record;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class HistoryParcelFXMLController implements Initializable , iController{

    ChilExploxApp main;
    Parcel parcel;
    @FXML
    Label titleLabel;
    @FXML
    Label idLabel;
    @FXML
    Label destinationLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label weightLabel;
    @FXML
    Label volumeLabel;
    @FXML
    Label priorityLabel;
    @FXML
    ListView reportListView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
    }
    public void setParcel(Parcel p){
        this.parcel = p;
        idLabel.setText(p.getId());
        destinationLabel.setText(p.getDestination().toString());
        typeLabel.setText(p.getType().toString());
        weightLabel.setText(String.valueOf(p.getWeight()));
        volumeLabel.setText(String.valueOf(p.getVolume()));
        priorityLabel.setText(String.valueOf(p.getPriority()));
        ObservableList<Record> recordsList ;
        recordsList = 
                FXCollections.observableArrayList(this.parcel.getRecords());
        reportListView.setItems(recordsList);
    }
    
    @FXML
    public void backScene(){
        main.changeScene("WatchParcelsFXML.fxml", WatchParcelsFXMLController.class);
    }
    
}
