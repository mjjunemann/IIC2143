/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import fxsampler.FXSampler;
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
        
        //poblateApp();
        
        this.changeScene("LoginViewFXML.fxml", LoginViewFXMLController.class);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        chilexplox = ChilExplox.getInstance();
        launch(args);
        //FXSampler.main(args);
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

            chilexplox.startNotifying(this);
            
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
    
    private void poblateApp(){
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
        
        Truck t1 = new Truck("ER3434",45,Type.Normal,chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t1);
        Truck t2 = new Truck("FH1288",20,Type.Fragile, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t2);
        Truck t3 = new Truck("GH2188",2,Type.Radioactive, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t3);
        Truck t4 = new Truck("FT1348",10,Type.Refrigerated, chilexplox.getSubsidiary(addr));
        chilexplox.getSubsidiary(addr).addVehicle(t4);
        
        t1 = new Truck("TH3434",45,Type.Normal,chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t1);
        t2 = new Truck("QW1288",20,Type.Fragile, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t2);
        t3 = new Truck("ER1288",2,Type.Radioactive, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t3);
        t4 = new Truck("TY1288",10,Type.Refrigerated, chilexplox.getSubsidiary(addr2));
        chilexplox.getSubsidiary(addr2).addVehicle(t4);
        
        t1 = new Truck("AA3434",45,Type.Normal,chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t1);
        t2 = new Truck("AB1288",20,Type.Fragile, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t2);
        t3 = new Truck("AC1288",2,Type.Radioactive, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t3);
        t4 = new Truck("BT1288",10,Type.Refrigerated, chilexplox.getSubsidiary(addr3));
        chilexplox.getSubsidiary(addr3).addVehicle(t4);
        
    }
    
}
