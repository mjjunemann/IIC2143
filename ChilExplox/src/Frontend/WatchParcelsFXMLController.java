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
import Backend.Filter.FilterParcelAddress;
import Backend.Filter.FilterParcelID;
import Backend.Filter.FilterParcelOrderId;
import Backend.Filter.FilterParcelState;
import Backend.Filter.FilterParcelType;
import Backend.Filter.FilterReset;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.BreadCrumbBar;

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
    BreadCrumbBar testBar;

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
    @FXML 
    private BorderPane topBorderPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      initializeParcelTable();
      initializeBreadCrumbBar();
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
        iFilter tmp4 = new FilterParcelType();
        iFilter tmp5 = new FilterParcelOrderId();
        iFilter tmp6 = new FilterParcelAddress();

        
        
        VisualFilter tmp_2 = new VisualFilter(tmp2,filteredParcels,selectedToggles,testBar);
        VisualFilter tmp_3 = new VisualFilter(tmp3,filteredParcels,selectedToggles,testBar);
        VisualFilter tmp_4 = new VisualFilter(tmp4,filteredParcels,selectedToggles,testBar);
        VisualFilter tmp_5 = new VisualFilter(tmp5,filteredParcels,selectedToggles,testBar);
        VisualFilter tmp_6 = new VisualFilter(tmp6,filteredParcels,selectedToggles,testBar);


        
        FilterBox.getChildren().add(tmp_2);
        FilterBox.getChildren().add(tmp_3);
        FilterBox.getChildren().add(tmp_4);
        FilterBox.getChildren().add(tmp_5);
        FilterBox.getChildren().add(tmp_6);


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
    
    public void initializeBreadCrumbBar()
    {   /*
        ADD TO THE VisualFilter, that add a Crumb to the BreadCrumbBar, so it can
        */
        testBar = new BreadCrumbBar<>();
        
        testBar.setOnCrumbAction(new EventHandler<BreadCrumbBar.BreadCrumbActionEvent<VisualFilter>>()
        {

            @Override
            public void handle(BreadCrumbBar.BreadCrumbActionEvent<VisualFilter> event) 
            {
                
                if (event.getSelectedCrumb().getValue().filter.getClass().equals(FilterReset.class))
                {
                    event.getSelectedCrumb().getValue().resetToLeaf(event.getSelectedCrumb());

                    event.getSelectedCrumb().getValue().ResetFilter();
                }
                else
                {
                    event.getSelectedCrumb().getValue().resetToLeaf(event.getSelectedCrumb());
                    event.getSelectedCrumb().getValue().filterSelectedCrumb(event.getSelectedCrumb());
                }
                
            }
            
        });
        topBorderPane.setCenter(testBar);
        //testBar.autoNavigationEnabledProperty().setValue(Boolean.TRUE);
        testBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
        testBar.selectedCrumbProperty().set(initializeResetFilter());
        
        
    }
    private TreeItem initializeResetFilter()
    {
        iFilter reset = new FilterReset();
        VisualFilter tmp = new VisualFilter(reset,filteredParcels,new ArrayList<>(),testBar);
        TreeItem tmp2 = new TreeItem<VisualFilter>(tmp)
        {
          @Override 
          public String toString()
          {
            return this.getValue().toString();
          }
        };
        return tmp2;
    }
    private void initializeOrders()
    {
        ArrayList<Order> orders = new ArrayList<>(this.main.getChilExplox().getCurrentSubsidiary().getOrders().values());
        orders.stream().forEach(order->subsidiaryParcels.addAll(order.getParcel()));

        
    }
    @FXML
    private void returnTuSubsidiary(ActionEvent event) {
        if (main.getChilExplox().clientLogged){
            main.changeScene("ClientViewFXML.fxml",
                ClientViewFXMLController.class);
        }else{
            main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);
        }
    }
    @FXML
    private void historyParcelAction(ActionEvent event) {
        Parcel p = this.parcelTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            changeToParcel(p);
        }
    }
    
    public void changeToParcel(Parcel p){
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("historyParcelFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            HistoryParcelFXMLController controller = loader.getController();
            controller.setChilExploxApp(this.main);
            controller.setParcel(p);
            
            this.main.changeSceneFromPage(page);
            
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
