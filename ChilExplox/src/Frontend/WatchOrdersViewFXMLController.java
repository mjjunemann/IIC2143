/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import Backend.Filter.FilterOrderID;
import Backend.Filter.FilterOrderState;
import Backend.Filter.FilterOrderTotal;
import Backend.Filter.FilterParcelState;
import Backend.Filter.FilterReset;
import Backend.Filter.FilterRut;
import Frontend.Cells.AddressCellTable;
import Frontend.Cells.VisualFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Box;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.BreadCrumbBar;
/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class WatchOrdersViewFXMLController implements Initializable, iController {

    
    ChilExploxApp main;
    private ArrayList<Order> ordersShown;
    private ObservableList<Order> subsidiaryOrders;
    private FilteredList<Order> filteredOrders;
    private ObservableList<Parcel> subsidiaryParcels;
    private BreadCrumbBar testBar;
    
    private ObservableList<String> ordersList;
    
    VisualFilter rutVisualFilter;
    iFilter rutFilter;
    //<editor-fold desc="FXML">
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order,String> orderId;
    @FXML
    private TableColumn<Order,Float> orderTotal;
    @FXML
    private TableColumn<Order,State> orderState;
    @FXML
    private TableColumn<Order,String> orderName;
    @FXML
    private TableColumn<Order,String> orderRut;
    @FXML
    private TableColumn<Order,Integer> orderQuantity;
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
    private ListView<String> ordersListView;
    @FXML
    private VBox FilterBox;
    @FXML
    private Button returnToSubsidiaryButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private AnchorPane pane;
    @FXML
    private BorderPane topBorderPane;
    //</editor-fold>

    private TranslateTransition transition;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeOrderTable();
        initializeBreadCrumbBar();
        //this.initializeParcelTable();
        this.initializeFilters();
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.initializeOrders();
        if (main.getChilExplox().clientLogged){
            rutVisualFilter.setVisible(false);
            //Aca hacer filtro de ordenes para el cliente
            rutFilter.Filter(filteredOrders, 
                    main.getChilExplox().getCurrentClient().getRut());
            //rutVisualFilter.filter.Filter(filteredOrders, main.getChilExplox().getCurrentClient())
        }else{
            rutVisualFilter.setVisible(true);
        }

       
    }
    
    private void initializeFilters()
    {
        
        
        
        
        
        ArrayList<VisualFilter> selectedToggles = new ArrayList<>();
        rutFilter = new FilterRut();
        iFilter tmp2 = new FilterOrderID();
        iFilter tmp3 = new FilterOrderState();
        iFilter tmp4 = new FilterOrderTotal();
        
        
        
        
        rutVisualFilter = new VisualFilter(rutFilter,filteredOrders,selectedToggles,testBar);
        VisualFilter tmp_2 = new VisualFilter(tmp2,filteredOrders,selectedToggles,testBar);
        VisualFilter tmp_3 = new VisualFilter(tmp3,filteredOrders,selectedToggles,testBar);
        VisualFilter tmp_4 = new VisualFilter(tmp4,filteredOrders,selectedToggles,testBar);
  
//testBar.selectedCrumbProperty();
        FilterBox.getChildren().add(rutVisualFilter);
        FilterBox.getChildren().add(tmp_2);
        FilterBox.getChildren().add(tmp_3);
        FilterBox.getChildren().add(tmp_4);
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
                
                System.out.print(event.getSelectedCrumb());
                if (event.getSelectedCrumb().getValue().filter.getClass().equals(FilterReset.class))
                {
                    event.getSelectedCrumb().getValue().ResetFilter();
                }
                else
                {
                    event.getSelectedCrumb().getValue().filterSelectedCrumb(event.getSelectedCrumb());
                }
//filterSelectedCrumb(event.getSelectedCrumb());
                
            }
            
        });
        topBorderPane.setCenter(testBar);
        //testBar.autoNavigationEnabledProperty().setValue(Boolean.TRUE);
        testBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
        testBar.setSelectedCrumb(BreadCrumbBar.buildTreeModel(initializeResetFilter()));
        
        
    }
    private VisualFilter initializeResetFilter()
    {
        iFilter reset = new FilterReset();
        VisualFilter tmp = new VisualFilter(reset,filteredOrders,new ArrayList<>(),testBar);
        return tmp;
    }
    private void initializeOrderTable()
    {
        
        orderId.setCellValueFactory(i->i.getValue().orderIdProperty());
        orderTotal.setCellValueFactory(i->i.getValue().totalProperty().asObject());
        orderState.setCellValueFactory(i->i.getValue().stateProperty());
        orderName.setCellValueFactory(i->i.getValue().getClient().nameProperty());
        orderRut.setCellValueFactory(i->i.getValue().getClient().rutProperty());
        //orderQuantity.setCellValueFactory(i->i.getValue().parcelProperty());
        
        subsidiaryOrders = FXCollections.observableArrayList(Order.extractor());
        filteredOrders = new FilteredList<>(subsidiaryOrders,p->true);
        
        orderTable.setItems(filteredOrders);
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
        parcelTable.setItems(subsidiaryParcels);
    }
    
    
    @FXML
    private void returnTuSubsidiary(ActionEvent event) {
        if (this.main.getChilExplox().clientLogged){
            main.changeScene("ClientViewFXML.fxml",
                    ClientViewFXMLController.class);
        } else {
            main.changeScene("SubsidiaryViewFXML.fxml",
                    SubsidiaryViewFXMLController.class);
        }
    }
    
    private void initializeOrders(){
        
        //this.main.getChilExplox().getCurrentSubsidiary().getOrders();
        ArrayList<Order> orders = new ArrayList<>(this.main.getChilExplox().getCurrentSubsidiary().getOrders().values());
        orders.stream().forEach(order-> subsidiaryOrders.add(order));    
        //orders.stream().forEach(order->subsidiaryParcels.addAll(order.getParcel()));
        /*
        ordersList = FXCollections.observableArrayList();
        ordersShown = new ArrayList<Order>();
        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            ordersList.add(order.toString());
            ordersShown.add(order);
        }
        ordersListView.setItems(ordersList);
                */
    }

    @FXML
    private void searchId(ActionEvent event) {
        ordersList = FXCollections.observableArrayList();
        ordersShown = new ArrayList<>();
        for (String orderRepresentation: this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().keySet()){
            Order order = this.main.getChilExplox().
                getCurrentSubsidiary().getOrders().get(orderRepresentation);
            if (searchTextField.getText().equals(order.getId()) ||
                    searchTextField.getText().equals("")) {
                ordersList.add(order.toString());
                ordersShown.add(order);
            }
        }
        ordersListView.setItems(ordersList);
    }
    
    @FXML
    private void modifyOrder(MouseEvent event) {
        if (event.getClickCount() == 2)
        {
            Order orderSelected = orderTable.getSelectionModel().getSelectedItem();
            if (orderSelected != null){

                changeSceneToModifyOrder(orderSelected);
                
            }
        }
    }
    
    private void changeSceneToModifyOrder(Order order){
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("CreateOrderViewFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            CreateOrderViewFXMLController controller = loader.getController();
            
            controller.setChilExploxApp(this.main);
            controller.initializeWithOrder(order);
         
        
            this.main.changeSceneFromPage(page);
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
    @FXML
    public void onDragDetected(MouseEvent event)
    {
        Dragboard db = ((Rectangle) event.getSource()).startDragAndDrop(TransferMode.ANY);
        
        ClipboardContent cb = new ClipboardContent();
        cb.putString("hello");
        db.setContent(cb);
        
        event.consume();
    }
    
    @FXML
    public void onDragOver(DragEvent event)
    {
        if (event.getSource() != event.getTarget() && event.getDragboard().hasString())
        {
            event.acceptTransferModes(TransferMode.MOVE);
            orderTable.setStyle(
            "-fx-border-color: tomato;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
        }
        event.consume();
    }
    
    @FXML
    public void onDragDropped(DragEvent event)
    {
        System.out.print(event.getGestureSource()  );
        System.out.print("Dropped Event");
        
    }
    
    @FXML
    public void onDragExited(DragEvent event)
    {
        orderTable.setStyle("-fx-border-color: transparent;"
                    + "-fx-border-width:2;"
                    + "-fx-border-style:solid;");
    }
    
    @FXML
    public void onDragDrop(DragEvent event)
    {
        //Todo
    }
    
    @FXML
    private void onEnter(KeyEvent e)
    {
     if(e.getCode().equals(KeyCode.ENTER))
     {
         search();
     } 
    }
    
    public void search()
    {
        Map<String,String> filtros;
        String input = searchTextField.getText();
        filtros = new HashMap();
        /*
        String[] input2 =  input.split(" ");
        for (String filter: input2)
        {
           switch(filter)
           {
               
               case "id":
                  break;
               case "total":
                  break;
               case "state":
                  break;
               case "quantity":
                  break;
               case "name":
                   break;
               case default:
                    break;
           }
        }*/
        filteredOrders.setPredicate((Order order)->
            {
                if (input == null || input.isEmpty())
                      return true;
                else
                {
                    return false; 
                }
            });
    }

    
    
}
