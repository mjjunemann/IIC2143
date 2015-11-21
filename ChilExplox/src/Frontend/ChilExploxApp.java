/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.controlsfx.control.Notifications;


/**
 *
 * @author matia
 */
public class ChilExploxApp extends Application implements iNotificationListener {
    
    private static ChilExplox chilexplox;
    private Subsidiary actual;
    public FXMLLoader loader;
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.getIcons().add(new Image("/images/truck_normal.png"));
        this.stage.setOnCloseRequest(e->chilexplox.Exit());
        System.out.println(com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());
        
        /*
        //We add two Subsidiaries to ChilExplox and a User
        Address addr = new Address("Amapolas",1500,"Providencia","Santiago");
        Address addr2 = new Address("Hernando de Aguirre",1133,
                "Providencia","Santiago");
        Address addr3 = new Address("Cumbre San Juan",12496,
                "Las Condes","Santiago");

        chilexplox.addSubsidary(addr);
        chilexplox.addSubsidary(addr2);
        chilexplox.addSubsidary(addr3);
        chilexplox.addUser("admin", "Administrador", "admin", Role.Administrator);
        chilexplox.addUser("fdoflorenzano", "Fernando", "blorg");
        chilexplox.addUser("user", "Usuario", "user");
        
        Truck t1 = new Truck("ER-3434",45,Type.Normal,chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t1);
        Truck t2 = new Truck("FH-1288",20,Type.Fragile, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t2);
        Truck t3 = new Truck("GH-2188",2,Type.Radioactive, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t3);
        Truck t4 = new Truck("FT-1348",10,Type.Refrigerated, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t4);
        
        t1 = new Truck("TH-3434",45,Type.Normal,chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t1);
        t2 = new Truck("QW-1288",20,Type.Fragile, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t2);
        t3 = new Truck("ER-1288",2,Type.Radioactive, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t3);
        t4 = new Truck("TY-1288",10,Type.Refrigerated, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t4);
        
        t1 = new Truck("AA-3434",45,Type.Normal,chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t1);
        t2 = new Truck("AB-1288",20,Type.Fragile, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t2);
        t3 = new Truck("AC-1288",2,Type.Radioactive, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t3);
        t4 = new Truck("BT-1288",10,Type.Refrigerated, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t4);
        
        */
        
        this.changeScene("LoginViewFXML.fxml", LoginViewFXMLController.class);
        // We log into the system in the first Subsidiary
        /*
        if(chilexplox.login("fdoflorenzano", "blorg", addr)){
            
            actual = chilexplox.current_subsidiary; //Logged-In
            //Start New Order without Client info
            Order o = actual.newOrder();
            //Add two parcels to the order. Calculate each total.
            Parcel p1 = new Parcel(10,20,10,addr,addr,o);
            Parcel p2 = new Parcel(30,10,10,addr,addr2,o);
            o.addParcel(p1);
            o.addParcel(p2);
            //Calculate the total amount
            BudgetCalculator.calculateTotal(o.getParcel());
            
            Client fernando2 = new Client("Fernando","Cumbre San Juan 12496","18933054-5","76143431");
            actual.setOrder(o, fernando2);
            
            Subsidiary subsidiaryAmapolas = chilexplox.getSubsidiary(addr2);

            System.out.print("--------------------------\nProbando Envio!\n");
            System.out.print("Order Estado:" + o.getState() + "\n");
            for(Parcel p: o.getParcel()){
                System.out.print("Parcel Estado:" + p.getState() + "\n");
            }
            System.out.print("Creamos camion y cargamos encominedas:\n");
            Truck t1 = new Truck("ER-3434",45, actual);
            actual.addVehicle(t1);
            t1.loadParcel(p1);
            t1.loadParcel(p2);
            System.out.print("Estado camion:" +t1.getState()+ " numero encomiendas cargadas: " +t1.getParcels().size() +"\n");
            System.out.print("Enviamos camion!\n");
            actual.sendsVehicle(t1, subsidiaryAmapolas);
            System.out.print("Estado camion:" +t1.getState()+ " numero encomiendas cargadas: " +t1.getParcels().size() +"\n");
            System.out.print("Order Estado:" + o.getState() + "\n");
            for(Parcel p: o.getParcel()){
                System.out.print("Parcel Estado:" + p.getState() + "\n");
            }
            System.out.print("Recibimos camion!\n");
            subsidiaryAmapolas.receivesParcels(t1);
            System.out.print("Estado camion:" +t1.getState()+ " numero encomiendas cargadas: " +t1.getParcels().size() +"\n");
            System.out.print("Order Estado:" + o.getState() + "\n");
            for(Parcel p: o.getParcel()){
                System.out.print("Parcel Estado:" + p.getState() + "\n");
            }
            System.out.print("Enviamos camion devuelta!\n");
            subsidiaryAmapolas.sendBack(t1);
            System.out.print("Estado camion:" +t1.getState()+ " numero encomiendas cargadas: " +t1.getParcels().size() +"\n");
            System.out.print("Order Estado:" + o.getState() + "\n");
            for(Parcel p: o.getParcel()){
                System.out.print("Parcel Estado:" + p.getState() + "\n");
            }
            System.out.print("--------------------------\n");
            //Loader.saveSubsidiary(subsidiaryAmapolas);
            
            

            btn.setText("send");
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Hello World!");
                    String subject = textFieldSubject.getText();
                    String content = textFieldContent.getText();

                    chilexplox.messaging.sendMessage(subsidiaryAmapolas.getMailbox(),
                            actual.getMailbox(), subject, content,MessageType.Regular);
                }
            });

            Pane root = new Pane();

            btn.setLayoutX(130);
            btn.setLayoutY(220);
            root.getChildren().add(btn);

            textFieldSubject.setPrefWidth(200);
            textFieldSubject.setLayoutX(50);
            textFieldSubject.setLayoutY(100);
            root.getChildren().add(textFieldSubject);

            textFieldContent.setPrefWidth(200);
            textFieldContent.setPrefHeight(50);
            textFieldContent.setLayoutX(50);
            textFieldContent.setLayoutY(150);
            root.getChildren().add(textFieldContent);

            Text t = new Text(120, 95, "Subject");
            t.setFont(new Font(14));
            root.getChildren().add(t);

            Text t2 = new Text(120, 145, "Content");
            t2.setFont(new Font(14));
            root.getChildren().add(t2);


            
       
            
        }
        */
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        chilexplox = ChilExplox.getInstance();
        launch(args);
    }
    
    public Stage getStage(){
        return this.stage;
    }
    
    public void changeScene(String fxmlName, Class className){
        
        try{
            loader = new FXMLLoader();
            loader.setLocation(ChilExploxApp.class.getResource(fxmlName));
            //FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.getResource(fxmlName));
            AnchorPane page = (AnchorPane)loader.load();
            iController controller = (iController)className.newInstance();
            controller = loader.getController();
            controller.setChilExploxApp(this);

            //chilexplox.startNotifying(this);
            
            Scene sceneLogin = new Scene(page);
            stage.setScene(sceneLogin);
            stage.setTitle("ChilExplox");
            stage.show();
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeSceneFromPage(AnchorPane page){
        Scene sceneLogin = new Scene(page);
        stage.setScene(sceneLogin);
        stage.show();
    }
    
    public ChilExplox getChilExplox(){
        return this.chilexplox;
    }

    @Override
    public void showNotification(Parcel parcel) {
        Platform.runLater( () -> {
            
            String contenido = "La encomienda " + parcel.getId() 
                    + " lleva mucho tiempo esperando";
            NotificationController.notificationParcel(this, parcel,
                    "Se debe enviar encomienda",
                    contenido);
        });
    }
    
}
