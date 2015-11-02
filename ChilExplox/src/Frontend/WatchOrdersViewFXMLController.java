/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    
    private ObservableList<String> ordersList;
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
    private ListView<String> ordersListView;
    
    @FXML
    private Button returnToSubsidiaryButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private AnchorPane pane;
    //</editor-fold>

    private TranslateTransition transition;
    private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);
    private TranslateTransition createTranslateTransition(final Rectangle rectangle) 
    {
    final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, rectangle);

    return transition;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        this.initializeOrders();

       
    }
    
    private void initializeTable()
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
    
    
    @FXML
    private void returnTuSubsidiary(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml",SubsidiaryViewFXMLController.class);
    }
    /*
    private void initializeOrders()
    {
        
    }
    */
    private void initializeOrders(){
        
        //this.main.getChilExplox().getCurrentSubsidiary().getOrders();
        ArrayList<Order> orders = new ArrayList<>(this.main.getChilExplox().getCurrentSubsidiary().getOrders().values());
        orders.stream().forEach(order-> subsidiaryOrders.add(order));        
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
            changeSceneToModifyOrder(orderSelected);
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
