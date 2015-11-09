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
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class NotificationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public static void notification(String title, String text){
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent arg0) {
                        System.out.println("Notification clicked on!");
                    }
                });
        
        notificationBuilder.show(); 
    }
    
    public static void notificationParcel(ChilExploxApp main, Parcel parcel,
            String title, String text){
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text (text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent arg0) {
                        main.changeScene("WatchTrucksListFXML.fxml",
                                WatchTrucksListFXMLController.class);
                    }
                });
        
        notificationBuilder.showWarning();
    }
    
}
