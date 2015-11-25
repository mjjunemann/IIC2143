/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.ArchiveType;
import Backend.Record;
import Backend.Report;
import Backend.User;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class UserReportFXMLController implements Initializable , iController{

    ChilExploxApp main;
    User user;
    boolean sale;
    ArrayList<Record> records;
    
    final FileChooser fileChooser = new FileChooser();
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    private Desktop desktop = Desktop.getDesktop();
    
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
    
    @FXML
    public void generatePDF(){
        
        File f = this.directoryChooser.showDialog(this.main.getStage());
        if (f != null) {
            System.out.print(f.getPath());
            Report r;
            if (sale) {
                r = new Report(ArchiveType.Sale, user, f.getPath());
            }else{
                r = new Report(ArchiveType.Error, user, f.getPath());
            }
            String filepath;
            if (sale) {
                filepath = r.generateUserSaleReport(this.user);
            }else{
                filepath = r.generateUserErrorReport(this.user);
            }
            if (filepath!="") {
                File fil = new File(filepath);
                openFile(fil);
            }
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
