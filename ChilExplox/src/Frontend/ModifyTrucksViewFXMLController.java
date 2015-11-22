/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class ModifyTrucksViewFXMLController implements Initializable, iController {

    ChilExploxApp main;
    @FXML
    private Button returnButton;
    @FXML
    private Button newTruckButton;
    @FXML
    private TableView<Truck> trucksTableView;
    @FXML
    private TableColumn<Truck, String> licensePlateColumn;
    @FXML
    private TableColumn<Truck, String> typeColumn;
    @FXML
    private TableColumn<Truck, String> capacityColumn;
    @FXML
    private TableColumn<Truck, String> driverColumn;
    @FXML
    private Button saveTruckButton;
    @FXML
    private Button removeTruckButton;
    @FXML
    private TextField license_plate;
    @FXML
    private TextField capacity;
    @FXML
    private ChoiceBox<User> driver;
    @FXML
    private ChoiceBox<Type> type;
    
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

    @FXML
    private void returnToSubsidiary(ActionEvent event) {
        this.main.changeScene("SubsidiaryViewFXML.fxml", 
                SubsidiaryViewFXMLController.class);
    }

    @FXML
    private void newTruck(ActionEvent event) {
    }

    @FXML
    private void selectTruck(MouseEvent event) {
    }

    @FXML
    private void saveTruck(ActionEvent event) {
    }

    @FXML
    private void removeTruck(ActionEvent event) {
    }
    
    
    
}
