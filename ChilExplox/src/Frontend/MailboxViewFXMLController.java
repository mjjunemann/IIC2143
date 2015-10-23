/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class MailboxViewFXMLController implements Initializable, iController {

    
    ChilExploxApp main;
    @FXML
    private Button exitMailboxButton;
    @FXML
    private Button newMailButton;
    @FXML
    private Button setReceivedMessagesButton;
    @FXML
    private Button setSentMessagesButton;
    @FXML
    private Button setErrorMessagesButton;
    @FXML
    private ListView<String> messagesListView;
    
    private Button actualButtonDefault;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualButtonDefault = setReceivedMessagesButton;
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        changeMessagesToReceived();
    }

    @FXML
    private void exitMailbox(ActionEvent event) {
        main.changeScene("SubsidiaryViewFXML.fxml",
                SubsidiaryViewFXMLController.class);
    }

    @FXML
    private void newMail(ActionEvent event) {
        main.changeScene("CreateMailViewFXML.fxml",
                CreateMailViewFXMLController.class);
    }

    @FXML
    private void setReceivedMessages(ActionEvent event) {
        actualButtonDefault.setDefaultButton(false);
        actualButtonDefault = setReceivedMessagesButton;
        actualButtonDefault.setDefaultButton(true);
        changeMessagesToReceived();
    }

    @FXML
    private void setSentMessages(ActionEvent event) {
        actualButtonDefault.setDefaultButton(false);
        actualButtonDefault = setSentMessagesButton;
        actualButtonDefault.setDefaultButton(true);
        changeMessagesToSent();
    }

    @FXML
    private void setErrorMessages(ActionEvent event) {
        actualButtonDefault.setDefaultButton(false);
        actualButtonDefault = setErrorMessagesButton;
        actualButtonDefault.setDefaultButton(true);
        changeMessagesToError();
    }
    
    private void changeMessagesToSent(){
        ObservableList<String> messages = 
                FXCollections.observableArrayList();
        for (Message message: main.getChilExplox().getCurrentSubsidiary().
                getMailbox().getSentMessages()){

            messages.add(message.getSubject());
            
        }
        messagesListView.setItems(messages);
    }
    
    private void changeMessagesToReceived(){
        ObservableList<String> messages = 
                FXCollections.observableArrayList();
        for (Message message: main.getChilExplox().getCurrentSubsidiary().
                getMailbox().getReceivedMessages()){
            if (message.isErrorMessage()){
                messages.add("Error: " + message.getSubject());
            }else{
                messages.add(message.getSubject());
            }
        }
        messagesListView.setItems(messages);
    }
    
    private void changeMessagesToError(){
        ObservableList<String> messages = 
                FXCollections.observableArrayList();
        for (Message message: main.getChilExplox().getCurrentSubsidiary().
                getMailbox().getReceivedMessages()){
            if (message.isErrorMessage()){
                messages.add(message.getSubject());
            }
        }
        messagesListView.setItems(messages);
    }

    public void changeSceneToMessage(Message message){
        
        try{
            FXMLLoader loader = new FXMLLoader(ChilExploxApp.class.
                    getResource("SeeMessageViewFXML.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            SeeMessageViewFXMLController controller = loader.getController();
            controller.setChilExploxApp(this.main);
            controller.setMessage(message);
            
                
            this.main.changeSceneFromPage(page);
        } catch(Exception ex) {
            Logger.getLogger(ChilExploxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void readMessage(MouseEvent event) {
        int messageSelected = messagesListView.getSelectionModel().
                getSelectedIndex();
        if (actualButtonDefault == setReceivedMessagesButton){
            Message message = main.getChilExplox().getCurrentSubsidiary().
                getMailbox().getReceivedMessages().get(messageSelected);
            changeSceneToMessage(message);
        } else if (actualButtonDefault == setSentMessagesButton){
            Message message = main.getChilExplox().getCurrentSubsidiary().
                getMailbox().getSentMessages().get(messageSelected);
            changeSceneToMessage(message);
        }

    }
    
}
