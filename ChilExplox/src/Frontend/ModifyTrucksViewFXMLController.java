/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
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
    private ChoiceBox<Type> type;
    @FXML
    private TextField driver_name;
    @FXML
    private TextField driver_rut;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disableFields();
    } 

    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
        initializeChoiceBox();
        initializeTrucksTableView();
        
    }
    
    public void initializeChoiceBox(){
        ObservableList<Type> types_list = 
                FXCollections.observableArrayList(Type.values());
        type.setItems(types_list);
        
     
    }
    
    
    public void cleanFields(){
        license_plate.setText(null);
        capacity.setText(null);
        driver_name.setText(null);
        driver_rut.setText(null);
        type.setSelectionModel(null);
    }
    
    public void disableFields(){
        license_plate.setDisable(true);
        capacity.setDisable(true);
        type.setDisable(true);
        driver_name.setDisable(true);
        driver_rut.setDisable(true);
        saveTruckButton.setDisable(true);
        removeTruckButton.setDisable(true);
    }
    
    private void enableFields(){
        license_plate.setDisable(false);
        capacity.setDisable(false);
        type.setDisable(false);
        driver_name.setDisable(false);
        driver_rut.setDisable(false);
        saveTruckButton.setDisable(false);
        removeTruckButton.setDisable(false);
    }

    private void initializeTrucksTableView(){
        licensePlateColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getPlate()));
        capacityColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(
                        String.valueOf(c.getValue().getMaxParcels())));
        typeColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getType().toString()));
        driverColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getDriver()));
        refreshTrucks();
    }
    
    private void refreshTrucks(){
        ObservableList<Truck> trucks = 
                FXCollections.observableArrayList(
                        this.main.getChilExplox().getCurrentSubsidiary().getTrucks());
        trucksTableView.setItems(trucks);
    }
    
    @FXML
    private void returnToSubsidiary(ActionEvent event) {
        this.main.changeScene("SubsidiaryViewFXML.fxml", 
                SubsidiaryViewFXMLController.class);
    }

    @FXML
    private void newTruck(ActionEvent event) {
        enableFields();
    }

    @FXML
    private void selectTruck(MouseEvent event) {
        
    }

    @FXML
    private void saveTruck(ActionEvent event) {
        if (checkTruckInputs()){
            Truck truck = new Truck(license_plate.getText(),
                            Integer.valueOf(capacity.getText()),
                            type.getSelectionModel().getSelectedItem(),
                            this.main.getChilExplox().getCurrentSubsidiary(),
                            driver_rut.getText(),
                            driver_name.getText());
            this.main.getChilExplox().getCurrentSubsidiary().addVehicle(truck);
            cleanFields();
            ShowAlert.message("Camión creado", "Camión creado con exito");
        }
    }

    @FXML
    private void removeTruck(ActionEvent event) {
        cleanFields();
    }
    
    
    
    private boolean checkTruckInputs(){
        if (license_plate.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "patente",
                    "debe ingresar patente");
            return false;
        }
        if (capacity.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "capacidad",
                    "debe ingresar la capacidad del camión");
            return false;
        }
        if (driver_name.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "nombre del conductor","debe ingresar un nombre");
            return false;
        }
        if (driver_rut.getText() == null){
            ShowAlert.alertWithFieldAndMessage(
                    "rut del conductor","debe ingresar un rut");
            return false;
        }
        if (type.getSelectionModel().isEmpty()){
            ShowAlert.alertWithFieldAndMessage(
                    "tipo de camion", 
                    "Seleccion un tipo para el camión");
            return false;
        }
        try {
            InputValidator.CheckLicensePlate(license_plate.getText());
        }catch(Exception e){
            ShowAlert.alertWithField(e, "patente");
            return false;
        }
        try {
            InputValidator.IsNumber(capacity.getText());
        }catch(Exception e){
            ShowAlert.alertWithField(e, "capacidad");
            return false;
        }
        try {
            InputValidator.CheckName(driver_name.getText());
        }catch(Exception e){
            ShowAlert.alertWithField(e, "nombre del conductor");
            return false;
        }
        try {
            InputValidator.CheckRut(driver_rut.getText());
        }catch(Exception e){
            ShowAlert.alertWithField(e, "rut del conductor");
            return false;
        }
        
        return true;
    }
    
}
