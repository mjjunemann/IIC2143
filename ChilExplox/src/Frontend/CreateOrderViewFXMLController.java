/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Address;
import Backend.ChilExplox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Backend.Order;
import Backend.Parcel;
import Backend.Subsidiary;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ListView<String> listView;
    
    private Order order;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();
        System.out.print(date.toString());
        order = new Order(date);
        date_text.setText(order.getSaleDate().toString());
    }   
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.app = main.getChilExplox();
        this.subsidiary = this.app.getCurrentSubsidiary();
        ArrayList<String> tmp = new ArrayList();
        destinies.setDisable(true);
        for (Address addr: this.app.getSubsidiariesAddress())
        {
            tmp.add(addr.getMainStreet());
        }
        ObservableList<String> list = FXCollections.observableArrayList(tmp);
        destinies.setItems(list);

    }

    
    @FXML
    private void newParcel(ActionEvent event) {
        //String tmpID = this.subsidiary.nextId();
        //String peekID = this.subsidiary.peekId();
        destinies.setDisable(false);
        weight.setText(null);
        volume.setText(null);
        weight.setDisable(false);
        volume.setDisable(false);
        parcel_id.setText("ID Parcel");
        destinies.setValue(null);
        

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
            Parcel p = new Parcel(p_weight,p_volume,0,addr1,addr1,this.order);

        }
        
        
    }
    private void addToListView()
    {
        
    }
    @FXML
    private void cancelOrder(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);

    }

        private boolean checkInputParcel()
        {
         return true;   
        }

}
