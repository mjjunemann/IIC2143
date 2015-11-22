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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class ModifySubsidiariesViewFXMLController implements Initializable, iController {
    
    ChilExploxApp main;
    Subsidiary subsidiary_selected;
    
    @FXML
    private Button returnButton;
    @FXML
    private Button newSubsidiaryButton;
    @FXML
    private TableView<Subsidiary> subsidiariesTableView;
    @FXML
    private TableColumn<Subsidiary, String> addressColumn;
    @FXML
    private TableColumn<Subsidiary, String> stateColumn;
    @FXML
    private Button saveSubsidiaryButton;
    @FXML
    private TextField street;
    @FXML
    private TextField number;
    @FXML
    private TextField neighborhood;
    @FXML
    private TextField city;
    @FXML
    private CheckBox enabled;
    
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
        initializeSubsidiariesTableView();
        
    }
    
    private void cleanFields(){
        street.setText(null);
        number.setText(null);
        neighborhood.setText(null);
        city.setText(null);
        enabled.setSelected(true);
    }
    
    private void disableFields(){
        street.setDisable(true);
        number.setDisable(true);
        neighborhood.setDisable(true);
        city.setDisable(true);
        enabled.setDisable(true);
        saveSubsidiaryButton.setDisable(true);
    }
    
    private void enableFields(){
        street.setDisable(false);
        number.setDisable(false);
        neighborhood.setDisable(false);
        city.setDisable(false);
        enabled.setDisable(false);
        saveSubsidiaryButton.setDisable(false);
    }
    
    private String getState(boolean bool){
        if (bool){
            return "Activa";
        }
        return "Desactiva";
    }
    
    private void initializeSubsidiariesTableView(){
        addressColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(c.getValue().getAddress()));
        stateColumn.setCellValueFactory(c -> 
                new SimpleStringProperty(getState(c.getValue().getEnabled())));
        refreshSubsidiaries();
    }
    
    private void refreshSubsidiaries(){
        ObservableList<Subsidiary> subsidiaries = 
                FXCollections.observableArrayList(
                        this.main.getChilExplox().getAllSubsidiaries());
        subsidiariesTableView.setItems(subsidiaries);
        subsidiariesTableView.refresh();
    }

    private void setFieldsWithSubsidiary(Subsidiary sub){
        street.setText(sub.getAddr().getStreet());
        neighborhood.setText(sub.getAddr().getNeighborhood());
        city.setText(sub.getAddr().getCity());
        number.setText(String.valueOf(sub.getAddr().getNumber()));
        enabled.setSelected(sub.getEnabled());
    }
    
    @FXML
    private void returnToSubsidiary(ActionEvent event) {
        this.main.changeScene("SubsidiaryViewFXML.fxml", 
                SubsidiaryViewFXMLController.class);
    }
   

    @FXML
    private void newSubsidiary(ActionEvent event) {
        cleanFields();
        enableFields();
        subsidiary_selected = null;
    }


    @FXML
    private void saveSubsidiary(ActionEvent event) {
        if (checkSubsidiaryInputs()){
            if (this.subsidiary_selected == null){
                Address address = new Address(
                        street.getText(), 
                        Integer.valueOf(number.getText()), 
                        neighborhood.getText(), 
                        city.getText());
                this.main.getChilExplox().addSubsidary(address);
                ShowAlert.message(
                        "Sucursal creada", 
                        "La sucursal ha sido creada con exito");
            }
            else{
                this.subsidiary_selected.setEnabled(enabled.isSelected());
                ShowAlert.message(
                        "Sucursal modificada", 
                        "La sucursal ha sido modificada con exito");
            }
        }
        disableFields();
        subsidiary_selected = null;
        refreshSubsidiaries();
    }
    
    @FXML
    private void selectSubsidiary(MouseEvent event) {
        if (event.getClickCount() == 2){
            subsidiary_selected = subsidiariesTableView.getSelectionModel().getSelectedItem();
            setFieldsWithSubsidiary(subsidiary_selected);
            disableFields();
            enabled.setDisable(false);
            saveSubsidiaryButton.setDisable(false);
        }
    }
    
    private boolean checkSubsidiaryInputs(){
        if (street.getText() == null){
            ShowAlert.alertWithFieldAndMessage("Calle", "Debe ingresar una calle");
           return false; 
        }
        if (number.getText() == null){
            ShowAlert.alertWithFieldAndMessage("Numero", "Debe ingresar un numero");
            return false;
        }
        if (neighborhood.getText() == null){
            ShowAlert.alertWithFieldAndMessage("Comuna", "Debe ingresar una comuna");
            return false;
        }
        if (city.getText() == null){
            ShowAlert.alertWithFieldAndMessage("Ciudad", "Debe ingresar una ciudad");
            return false;
        }
        try {
            InputValidator.CheckName(street.getText());
        } catch (Exception e){
            ShowAlert.alertWithField(e, "calle");
            return false;
        }
        try {
            InputValidator.CheckName(neighborhood.getText());
        } catch (Exception e){
            ShowAlert.alertWithField(e, "comuna");
            return false;
        }
        try {
            InputValidator.CheckName(city.getText());
        } catch (Exception e){
            ShowAlert.alertWithField(e, "ciudad");
            return false;
        }
        try {
            InputValidator.IsNumber(number.getText());
        } catch (Exception e){
            ShowAlert.alertWithField(e, "numero");
            return false;
        }
        
        return true;
    }
    
    
}
