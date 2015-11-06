/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Address;
import Backend.Filter.FilterOrderID;
import Backend.Filter.FilterOrderState;
import Backend.Filter.FilterOrderTotal;
import Backend.Filter.FilterParcelID;
import Backend.Filter.FilterParcelState;
import Backend.Filter.FilterRut;
import Backend.Order;
import Backend.Parcel;
import Backend.State;
import Backend.Type;
import Backend.iFilter;
import Frontend.Cells.AddressCellTable;
import Frontend.Cells.VisualFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author matia
 */
public class WatchParcelsFXMLController implements Initializable , iController {

   
    /**
     * Initializes the controller class.
     */
    ChilExploxApp main;
    private ObservableList<Parcel> subsidiaryParcels;
    private FilteredList<Parcel> filteredParcels;

     @FXML
    private TableView<Parcel> parcelTable;
    @FXML
    private TableColumn<Parcel,String> parcelId;
    @FXML
    private TableColumn<Parcel,String> parcelOrderId;
    @FXML
    private TableColumn<Parcel,State> parcelState;
    @FXML
    private TableColumn<Parcel,Type> parcelType;
    @FXML
    private TableColumn<Parcel,Address> parcelDestination;
    @FXML
    private TableColumn<Parcel,Float> parcelWeight;
    @FXML
    private TableColumn<Parcel,Float> parcelVolume;
    @FXML
    private VBox FilterBox;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      initializeParcelTable();
      initializeFilters();
    }    

    @Override
    public void setChilExploxApp(ChilExploxApp main) {
        this.main = main;
        this.initializeOrders();
    }
    
    
    private void initializeFilters()
    {

        ArrayList<VisualFilter> selectedToggles = new ArrayList<>();

        iFilter tmp2 = new FilterParcelID();
        iFilter tmp3 = new FilterParcelState();
        VisualFilter tmp_2 = new VisualFilter(tmp2,filteredParcels,selectedToggles);
        VisualFilter tmp_3 = new VisualFilter(tmp3,filteredParcels,selectedToggles);

        
        FilterBox.getChildren().add(tmp_2);
        FilterBox.getChildren().add(tmp_3);
    }
    private void initializeParcelTable()
    {
        parcelOrderId.setCellValueFactory(i->i.getValue().orderProperty().getValue().orderIdProperty());
        parcelId.setCellValueFactory(i->i.getValue().idProperty());
        parcelState.setCellValueFactory(i->i.getValue().stateProperty());
        parcelType.setCellValueFactory(i->i.getValue().typeProperty());
        parcelWeight.setCellValueFactory(i->i.getValue().weightProperty().asObject());
        parcelVolume.setCellValueFactory(i->i.getValue().volumeProperty().asObject());
        parcelDestination.setCellValueFactory(i->i.getValue().destinationProperty());
        
        parcelDestination.setCellFactory(c-> new AddressCellTable());
        subsidiaryParcels = FXCollections.observableArrayList(Parcel.extractor());
        
        filteredParcels = new FilteredList<>(subsidiaryParcels,p->true);
        parcelTable.setItems(filteredParcels);
    }
    private void initializeOrders()
    {
        ArrayList<Order> orders = new ArrayList<>(this.main.getChilExplox().getCurrentSubsidiary().getOrders().values());
        orders.stream().forEach(order->subsidiaryParcels.addAll(order.getParcel()));

        
    }
    @FXML
    private void returnTuSubsidiary(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml",SubsidiaryViewFXMLController.class);
    }
}
