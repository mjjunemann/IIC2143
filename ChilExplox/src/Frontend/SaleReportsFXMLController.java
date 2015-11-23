/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Record;
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
public class SaleReportsFXMLController implements Initializable, iController {

    ChilExploxApp main;
    String option;
    
    @FXML
     Button backButton;
    @FXML
     Button generateButton;
    @FXML
     Label titleLabel;
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
        this.option = option;
    }
    public void setOption(String option){
        this.option = option;
        this.titleLabel.setText(option+ " Sales Records.");
        ObservableList<Record> recordsList ;
        if ("Day".equals(option)) {
            recordsList = 
                FXCollections.observableArrayList(this.main.getChilExplox()
                        .getCurrentSubsidiary().getDaySaleRecords());
        }else if("Week".equals(option)){
            recordsList = 
                FXCollections.observableArrayList(this.main.getChilExplox()
                        .getCurrentSubsidiary().getWeekSaleRecords());
        }else{
            recordsList = 
                FXCollections.observableArrayList(this.main.getChilExplox()
                        .getCurrentSubsidiary().getMonthSaleRecords());
        }
        reportListView.setItems(recordsList);
    }
    
    @FXML
    public void backScene(){
        this.main.changeScene("ViewReportsFXML.fxml", ViewReportsFXMLController.class);
    }
}
