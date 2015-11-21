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
import javafx.scene.control.*;
import Backend.Order;
import Backend.Parcel;
import Backend.Role;
import Backend.Subsidiary;
import Frontend.Cells.AddressCell;
import Frontend.Cells.ParcelCell;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
import javafx.scene.control.Alert.AlertType;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.textfield.TextFields;
public class CreateOrderViewFXMLController implements Initializable, iController {

    /**
     * Initializes the controller class.
     */
    ChilExploxApp main;
    ChilExplox app;
    Subsidiary subsidiary;
    ArrayList<String> parcelArray;
    ObservableList<Parcel> parcels;
    Parcel current_parcel;
    
    //<editor-fold desc="FXML">
    @FXML
    private Button cancelButton;
    @FXML
    private Button newParcelButton;
    @FXML
    private Text date_text;
    @FXML
    private Text order_id;
    @FXML
    private Text parcel_number;
    @FXML
    private TextField parcel_id;
    @FXML
    private ChoiceBox destinies;
    @FXML
    private ChoiceBox parcel_types;
    @FXML
    private TextField volume;
    @FXML
    private TextField weight;
    @FXML
    private ListView<Parcel> listView;
    @FXML
    private Button saveParcel;
    @FXML
    private Label subTotal;
    @FXML
    private Label total;
    @FXML
    private Text parcelState;
    @FXML
    private Button saveOrder;
    @FXML
    private Button deleteParcelButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField addressField;
    @FXML 
    private TextField phoneNumber;
    @FXML
    private TextField rut;
    @FXML
    private Label parcel_state;
    @FXML
    private Button deleteButton;
    //</editor-fold>
    
    private Order order;
    @FXML
    private TextField priority;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initListView();
        saveParcel.setDisable(true);
    }   
    public void initListView()
    {
    listView.setCellFactory((ListView<Parcel> param) -> {
            return new ParcelCell();
    });
    }
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.app = main.getChilExplox();
        this.subsidiary = this.app.getCurrentSubsidiary();
        TextFields.bindAutoCompletion(rut, this.subsidiary.getClients().keySet());


    }
    
    @FXML
    private void newParcel(ActionEvent event) {
        this.current_parcel = null;
        String peekID = this.order.peekId();
        parcel_types.setDisable(false);
        destinies.setDisable(false);
        weight.setDisable(false);
        volume.setDisable(false);
        saveParcel.setDisable(false);
        priority.setDisable(false);
        
        weight.setText(null);
        volume.setText(null);
        priority.setText(null);
        
        parcelSetState(State.Origin);
        parcel_id.setText(peekID);
        
        parcel_types.setValue(null);
        destinies.setValue(null);
        
    }
    
    @FXML
    private void saveParcel(ActionEvent event)
    {
        boolean succesful = this.checkInputParcel();
        if (succesful && this.current_parcel == null)
        {
            float p_weight = 0;
            float p_volume = 0;
            int p_priority = 0;
            disableEditParcel();
            p_weight = Float.parseFloat(weight.getText());
            p_volume = Float.parseFloat(volume.getText());
            p_priority = Integer.parseInt(priority.getText());
            
            Type p_type = (Type) parcel_types.getSelectionModel().getSelectedItem();
            Address addr1 = this.subsidiary.getAddr();
            Address addr2 = (Address) destinies.getSelectionModel().getSelectedItem();
            Parcel p = this.order.addParcel(p_type,p_weight,p_volume,
                    p_priority,addr1,addr2);
            this.parcels.add(p);
            changeTotals(this.order,p);
            saveParcel.setDisable(true);
        }
        else if (succesful)
        {
            disableEditParcel();
            editParcel(this.current_parcel);
        }
        
        
    }
    
    @FXML
    private void saveOrder(ActionEvent event)
    {
        if (!parcels.isEmpty() && completeClient())
        {
            Client c = getClient();
            this.order.saveParcels();
            this.subsidiary.setOrder(this.order,c);
            if (main.getChilExplox().clientLogged){
                main.changeScene("ClientViewFXML.fxml", 
                        ClientViewFXMLController.class);
                
            }else{
                main.changeScene("SubsidiaryViewFXML.fxml",
                    SubsidiaryViewFXMLController.class);
            }
        }/*
        else
        {
            Client c = getClient();
            this.subsidiary.setOrder(this.order,c);
            main.changeScene("SubsidiaryViewFXML.fxml",
                    SubsidiaryViewFXMLController.class);
        }
        */
    }
    
    private void changeTotals(Order o, Parcel p)
    {
        this.setSubTotal(p);
        this.setTotalOrder(o);
                 
    }
    private void addToListView()
    {
        //listView.
    }
    @FXML
    private void cancelOrder(ActionEvent event) {
        order.cancelSave();

        if (main.getChilExplox().getCurrentLogged().getRole().equals(Role.Client)){
            main.changeScene("ClientViewFXML.fxml", 
                    ClientViewFXMLController.class);
        }else{
            main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);
        }

    }
    
    public void initializeWithOrder(Order order){
        this.initializeMin();
        this.order = order;
        this.setClient(order.getClient());
        this.setOrderInfo(this.order);
        this.order.getParcels().stream().forEach( p -> this.parcels.add(p));
        this.listView.setItems(this.parcels);
    }
    
    public void initializeWithoutOrder()
    {
        this.initializeMin();
        this.order = this.subsidiary.newOrder();
        this.setOrderInfo(this.order);
    }
    
    public void initializeWithClient(Client client)
    {
        this.initializeMin();
        this.order = this.subsidiary.newOrder();
        this.setOrderInfo(this.order);
        setClient(client);
    }
    
    public void initializeMin()
    {
        destinies.setDisable(true);
        weight.setText(null);
        volume.setText(null);
        priority.setText(null);
        weight.setDisable(true);
        volume.setDisable(true);
        priority.setDisable(true);
        destinies.setValue(null);
        saveParcel.setDisable(true);
        
        destinies.setDisable(true);
        parcel_types.setDisable(true);
        
        ObservableList<Address> list = FXCollections.observableArrayList(this.app.getSubsidiariesAddress());
        ObservableList<Type> typesArray = FXCollections.observableArrayList(Type.values());
        this.parcels = FXCollections.observableArrayList(Parcel.extractor());
        listView.setItems(this.parcels);
        parcel_types.setItems(typesArray);
        destinies.setItems(list);
        
        ChilExplox chile = this.main.getChilExplox();
        iPerson person = chile.getCurrentLogged();
        Role role = person.getRole();
        String name = person.getName();
      
        
        if (this.main.getChilExplox().getCurrentLogged().getRole().equals(Role.Client)){
            firstName.setEditable(false);
            this.deleteButton.setDisable(true);
            this.rut.setEditable(false);
            this.lastName.setEditable(false);
        }
        
    }
    
    public void setOrderInfo(Order o)
    {
        date_text.setText(o.getSaleDate().toString());
        order_id.setText(o.getId());
        setTotalOrder(o);
    }
    public void setTotalOrder(Order o)
    {
        total.setText(String.format("$%d",(int)o.getTotal()));

    }
    public void setSubTotal(Parcel p)
    {
         subTotal.setText(String.format("$%d",(int)BudgetCalculator.calculateParcel(p)));
    }
    public Client getClient()
    {
        Client c = null;
        if (this.order.getClient() != null)
        {
            c = this.order.getClient();
            this.editClient(c);
        }
        else{
            c = new Client();
            editClient(c);
        }
        return c;
    }
    public void editClient(Client c)
    {
        
        completeClient();
        c.setRut(rut.getText());
        c.setName(firstName.getText());
        c.setAddress(addressField.getText());
        c.setLastname(lastName.getText());
        c.setPhone(phoneNumber.getText());
        c.setEmail(email.getText());

    }
    
    public void editParcel(Parcel p)
    {
        float p_weight = Float.parseFloat(weight.getText());
        float p_volume = Float.parseFloat(volume.getText());
        int priorityValue = Integer.parseInt(priority.getText());
        Type p_type = (Type) parcel_types.getSelectionModel().getSelectedItem();
        System.out.print(p_type);
        Address addr2 = (Address) destinies.getSelectionModel().getSelectedItem();
        p.setWeight(p_weight);
        p.setVolume(p_volume);
        p.setType(p_type);
        p.setDestination(addr2);
        p.setPriority(priorityValue);
        changeTotals(this.order,p);

    }
    public void setClient(Client c)
    {
        if (c != null)
        {
            firstName.setText(c.getName());
            addressField.setText(c.getAddress());
            rut.setText(c.getRut());
            phoneNumber.setText(c.getPhone());
            email.setText(c.getEmail());
            lastName.setText(c.getLastname());
        }
    }

    private boolean checkInputParcel()
    {
        try
        {
            InputValidator.IsFloat(volume.getText());
        }
        catch(Exception e)
        {
            ShowAlert.alertWithField(e, "volumen");
            return false;
        }
        try{
            InputValidator.IsFloat(weight.getText());
        }
        catch(Exception e)
        {
            ShowAlert.alertWithField(e, "peso");
            return false;
        }
        try{
            InputValidator.IsNumber(priority.getText());
        }
        catch(Exception e)
        {
            ShowAlert.alertWithField(e, "prioridad");
            return false;
        }
        return true;   
    }
    
    public void setParcel(Parcel p)
    {
        this.current_parcel = p;
        
        parcel_id.setText(p.getId());
        weight.setText(String.format("%f",p.getWeight()));
        volume.setText(String.format("%f",p.getVolume()));
        priority.setText(String.format("%d",p.getPriority()));
        
        parcelSetState(p.getState());
        System.out.print(p.getType());
        
        parcel_types.setValue(p.getType());
        
        destinies.setValue(p.getDestination());
        //destinies.setValue(null);
        //enableEditParcel();
        this.setSubTotal(p);
    }
    @FXML
    public void onMouseClickParcel(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            Parcel p = listView.getSelectionModel().getSelectedItem();
            if (p != null)
            {
                this.setParcel(p);
            }
        }
    }
    
    @FXML
    private void autoFillClient(KeyEvent e)
    {
     if(e.getCode().equals(KeyCode.ENTER))
     {
         if (rut.getText() != null)
         {
            setClient(this.subsidiary.getClient(rut.getText()));
            
        } 
     }
    }
    @FXML
    private void deleteParcel(ActionEvent event)
    {
        Parcel p = listView.getSelectionModel().getSelectedItem();
        if (p != null)
        {
            parcels.remove(p);
            this.order.deleteParcel(p);
        }
    }
    
    private boolean completeClient()
    {
        try
        {
           InputValidator.CheckRut(rut.getText());
           
        }
        catch(Exception e)
        {
            ShowAlert.alertWithField(e, rut.getId());
            return false;
        }
        try{
            InputValidator.CheckName(firstName.getText());
        } catch(Exception e){
            ShowAlert.alertWithField(e, "nombre");
            return false;
        }
        try{
            InputValidator.CheckName(lastName.getText());
        } catch(Exception e){
            ShowAlert.alertWithField(e, "apellido");
            return false;
        }
        try{
            InputValidator.CheckPhone(phoneNumber.getText());
        } catch(Exception e){
            ShowAlert.alertWithField(e, "telefono");
            return false;
        }
        try{
            InputValidator.CheckEmail(email.getText()); 
        } catch(Exception e){
            ShowAlert.alertWithField(e, email.getId());
            return false;
        }
     
        return true;
        /*
        if (!firstName.getText().isEmpty()
                && !rut.getText().isEmpty()
                && !phoneNumber.getText().isEmpty()
                && !addressField.getText().isEmpty()
                && !email.)
        {
            return true;
        }
        return false;
        */
    }
    private void validateClient()
    {
        
    }
    public void parcelSetState(Backend.State s)
    {
        switch(s)
        {
            case Origin:
                parcelState.setStyle("-fx-fill:green;");
                break;
            case OnTransit:
                parcelState.setStyle("-fx-fill:green;");
                break;
            case Destination:
                parcelState.setStyle("-fx-fill:red;");
                break;
            case Delivered:
                parcelState.setStyle("-fx-fill:red;");
                break;
                
        }
        parcelState.setText(s.toString());

    }
    
    public void disableEditParcel()
    {
        weight.setDisable(true);
        volume.setDisable(true);
        priority.setDisable(true);
        destinies.setDisable(true);
        parcel_types.setDisable(true);
        saveParcel.setDisable(true);

    }
    @FXML
    public void enableEditParcel()
    {
        
        if (this.current_parcel != null && this.current_parcel.getState() == State.Origin)
        {
            parcel_types.setDisable(false);
            destinies.setDisable(false);
            weight.setDisable(false);
            volume.setDisable(false);
            priority.setDisable(false);
            saveParcel.setDisable(false);
        }
        else
        {
         Alert dlg = new Alert(AlertType.WARNING);
         dlg.setTitle("Warning");
         dlg.setContentText("Please set a parcel to continue or the "
                 + "}parcel you are selection is not on origin");
         dlg.showAndWait();
        }
    }
    
    @FXML
    public void deleteOrder()
    {
        Alert alert = deleteOrderAlert();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            this.subsidiary.deleteOrder(this.order);
            main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);
        }
            
        
        
    }
    public Alert deleteOrderAlert()
    {
        Alert dlg = new Alert(AlertType.CONFIRMATION);
        dlg.setTitle("Deleting Order");
        dlg.setContentText("Are you sure you wanna delete this order?");
        return dlg;
    }
}
