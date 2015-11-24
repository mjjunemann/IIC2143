/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.ArchiveType;
import Backend.Record;
import Backend.Report;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class SaleReportsFXMLController implements Initializable, iController {

    ChilExploxApp main;
    private Desktop desktop = Desktop.getDesktop();
    String option;
    
    @FXML
     Button backButton;
    @FXML
     Button generateButton;
    @FXML
     Label titleLabel;
    @FXML
     ListView<Record> reportListView;
    
    final FileChooser fileChooser = new FileChooser();
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    
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
    
    @FXML
    public void generatePDF(){
        
        File f = this.directoryChooser.showDialog(this.main.getStage());
        if (f != null) {
            String filepath = f.getPath()+"/report.pdf";
            System.out.print(f.getPath());
            Report r = new Report(ArchiveType.Delivery,filepath);
            File fil = new File(filepath);
            openFile(fil);
        }
    }
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                SaleReportsFXMLController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}
