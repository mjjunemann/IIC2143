/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Record;
import Backend.Report;
import Backend.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class UserReportFXMLController implements Initializable , iController{

    ChilExploxApp main;
    User user;
    boolean sale;
    
    @FXML
     Button backButton;
    @FXML
     Button generateButton;
    @FXML
     Label titleLabel;
    @FXML
     Label userLabel;
    @FXML
     ListView<Record> reportListView;
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
    }
    public void setUser(User u){
        this.user = u;
        userLabel.setText(u.getUsername());
    }
    public void setMode(boolean sale){
        this.sale = sale;
        ObservableList<Record> recordsList;
        if (sale) {
            titleLabel.setText("Sales Records of:");
            recordsList = 
                FXCollections.observableArrayList(this.user.getSaleRecords());
        }else{
            titleLabel.setText("Error Records of:");
            recordsList = 
                FXCollections.observableArrayList(this.user.getErrorRecords());
        }
        reportListView.setItems(recordsList);
    }
    @FXML
    public void backScene(){
        this.main.changeScene("ViewReportsFXML.fxml", ViewReportsFXMLController.class);
    }
    
}
