/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.ArchiveType;
import Backend.Subsidiary;
import Backend.Truck;
import Backend.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class ViewReportsFXMLController implements Initializable , iController{
    
     ChilExploxApp main;
     ArchiveType selectedType;
     @FXML
     Button backButton;
     @FXML
     Button userErrorButton;
     @FXML
     Button userSaleButton;
     @FXML
     ListView<User> userListView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setItemsListView(){
        ObservableList<User> addressSubsidiariesList = 
                FXCollections.observableArrayList(
                        this.main.getChilExplox().getUsers());
        userListView.setItems(addressSubsidiariesList);
    }
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        setItemsListView();
    }
    @FXML
    public void backScene(){
        this.main.changeScene("SubsidiaryViewFXML.fxml", SubsidiaryViewFXMLController.class);
    }
    
    @FXML
    public void userErrorAction(){
        User user_selected = userListView.getSelectionModel().getSelectedItem();
        if (user_selected != null) {
            changeSceneToUser(user_selected,false);
        }
    }
    
    @FXML
    public void userSaleAction(){
        User user_selected = userListView.getSelectionModel().getSelectedItem();
        if (user_selected != null) {
            changeSceneToUser(user_selected,true);
        }
    }
    @FXML
    public void daySaleAction(){
        changeSceneToSales("Day");
    }
    @FXML
    public void weekSaleAction(){
        changeSceneToSales("Week");
    }
    @FXML
    public void monthSaleAction(){
        changeSceneToSales("Month");
    }
    
    public void changeSceneToUser(User user, boolean sale){
        
        try{
                FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("userReportFXML.fxml"));
                AnchorPane page = (AnchorPane)loader.load();
                UserReportFXMLController controller = loader.getController();
                controller.setChilExploxApp(this.main);
                controller.setUser(user);
                controller.setMode(sale);
                this.main.changeSceneFromPage(page);
            
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void changeSceneToSales(String option){
        try{
                FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("SaleReportsFXML.fxml"));
                AnchorPane page = (AnchorPane)loader.load();
                SaleReportsFXMLController controller = loader.getController();
                controller.setChilExploxApp(this.main);
                controller.setOption(option);
                this.main.changeSceneFromPage(page);
            
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
