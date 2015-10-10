/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 *
 * @author matia
 */
public class ChilExplox extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        
        TextField textFieldSubject = new TextField();
        TextField textFieldContent = new TextField();

        
        Address addr = new Address("Amapolas",1500,"Providencia","Santiago");
        
        Address addr2 = new Address("Hernando de Aguirre",1133,
                "Providencia","Santiago");
        
        Parcel p1 = new Parcel(10,10,10,addr,addr);
        Parcel p2 = new Parcel(10,10,10,addr,addr);
        Order o = new Order();
        o.addParcel(p1);
        o.addParcel(p2);
        
        Subsidiary subsidiaryAmapolas = new Subsidiary(addr);
        Subsidiary subsidiaryHernando = new Subsidiary(addr2);
        subsidiaryHernando.newOrder();
        Loader.saveSubsidiary(subsidiaryAmapolas);
        Subsidiary amapolas1 = Loader.loadSubsidary("Amapolas1500");
        System.out.print(amapolas1.getAddress()+"\n");
        Messaging messaging = new Messaging();
        
        float a = o.getTotal();
        String b = Float.toString(a);
        System.out.print(b);
        
       
        btn.setText("send");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                String subject = textFieldSubject.getText();
                String content = textFieldContent.getText();
                
                messaging.sendMessage(subsidiaryAmapolas.getMailbox(),
                        subsidiaryHernando.getMailbox(), subject, content);
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
