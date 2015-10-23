/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Address;
import Backend.BudgetCalculator;
import Backend.ChilExplox;
import Backend.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Backend.Order;
import Backend.Parcel;
import Backend.Subsidiary;
import Frontend.Cells.AddressCell;
import Frontend.Cells.ParcelCell;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class CreateOrderViewFXMLController implements Initializable, iController {

    /**
     * Initializes the controller class.
     */
    ChilExploxApp main;
    ChilExplox app;
    Subsidiary subsidiary;
    ArrayList<String> parcelArray;
    ObservableList<Parcel> parcels;
    
    //<editor-fold desc="FXML">
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
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
    private Button saveOrder;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField addressField;
    @FXML 
    private TextField phoneNumber;
    @FXML
    private TextField rut;
    //</editor-fold>
    
    private Order order;
    
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


    }
    
    @FXML
    private void newParcel(ActionEvent event) {
        String peekID = this.order.peekId();
        destinies.setDisable(false);
        weight.setText(null);
        volume.setText(null);
        weight.setDisable(false);
        volume.setDisable(false);
        parcel_id.setText(peekID);
        destinies.setValue(null);
        saveParcel.setDisable(false);
    }
    
    @FXML
    private void saveParcel(ActionEvent event)
    {
        boolean succesful = this.checkInputParcel();
        if (succesful)
        {
            weight.setDisable(true);
            volume.setDisable(true);
            destinies.setDisable(true);
            float p_weight = Float.parseFloat(weight.getText());
            float p_volume = Float.parseFloat(volume.getText());
            Address addr1 = this.subsidiary.getAddr();
            Parcel p = this.order.addParcel(p_weight,p_volume,0,addr1,addr1);
            this.parcels.add(p);
            changeTotals(this.order,p);
            saveParcel.setDisable(true);
        }
        
        
    }
    @FXML
    private void saveOrder(ActionEvent event)
    {
        Client c = getClient();
        this.subsidiary.setOrder(this.order,c);
        main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);

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
        main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);

    }
    
    public void initializeWithOrder(Order order){
        this.initializeMin();
        this.order = order;
        this.setClient(order.getClient());
        this.setOrderInfo(this.order);
        this.order.getParcel().stream().forEach( p -> this.parcels.add(p));
        this.listView.setItems(this.parcels);
    }
    
    public void initializeWithoutOrder()
    {
        this.initializeMin();
        this.order = this.subsidiary.newOrder();
        this.setOrderInfo(this.order);
    }
    
    public void initializeMin()
    {
        destinies.setDisable(true);
        weight.setText(null);
        volume.setText(null);
        weight.setDisable(true);
        volume.setDisable(true);
        destinies.setValue(null);
        saveParcel.setDisable(true);
        
        ArrayList<String> tmp = new ArrayList();
        destinies.setDisable(true);
        for (Address addr: this.app.getSubsidiariesAddress())
        {
            tmp.add(addr.getMainStreet());
        }
        ObservableList<String> list = FXCollections.observableArrayList(tmp);
        this.parcels = FXCollections.observableArrayList(Parcel.extractor());
        listView.setItems(this.parcels);
        destinies.setItems(list);
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
        String name =  firstName.getText();
        String addr =  addressField.getText();
        String client_rut =  this.rut.getText();
        String phone=  phoneNumber.getText();
        // Validate inputs //
        c.setName(name);
        c.setAddress(addr);
        c.setPhone(phone);
        c.setRut(client_rut);

    }
    public void setClient(Client c)
    {
        firstName.setText(c.getName());
        addressField.setText(c.getAddress());
        rut.setText(c.getRut());
        phoneNumber.setText(c.getPhone());
    }
    private boolean checkInputParcel()
    {
        return true;   
    }
    
    public void setParcel(Parcel p)
    {
        parcel_id.setText(p.getId());
        weight.setText(String.format("%f",p.getWeight()));
        volume.setText(String.format("%f",p.getVolume()));
        destinies.setValue(null);
        this.setSubTotal(p);
    }
    @FXML
    public void onMouseClickParcel(MouseEvent e)
    {
        Parcel p = listView.getSelectionModel().getSelectedItem();
        if (p != null)
        {
            this.setParcel(p);
        }
    }

}
