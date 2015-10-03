/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Address;
import Backend.Order;
import Backend.Parcel;
import Backend.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author matia
 */
public class ChilExplox extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        
        
        Address addr = new Address("Amapolas",1500,"Providencia","Santiago");
        Parcel p1 = new Parcel(10,10,10,addr,addr);
        Parcel p2 = new Parcel(10,10,10,addr,addr);
        Order o = new Order();
        o.addParcel(p1);
        o.addParcel(p2);
        float a = o.getTotal();
        String b = Float.toString(a);
        System.out.print(b);
        
        btn.setText("hello");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
