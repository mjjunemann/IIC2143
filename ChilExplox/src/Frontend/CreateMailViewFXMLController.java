/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author guillermofigueroa
 */
public class CreateMailViewFXMLController implements Initializable, iController {

    ChilExploxApp main;
    Message mail;
    
    @FXML
    private Button cancelMailButton;
    @FXML
    private Button sendMailButton;
    @FXML
    private TextArea contentTextArea;
    @FXML
    private TextField subjectTextField;
    @FXML
    private ChoiceBox<String> destinyChoiceBox;
    @FXML
    private CheckBox errorCheckBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
        initializeChoiceBox();
    }
    
    private void initializeChoiceBox(){
        ObservableList<String> addressStringList = 
                FXCollections.observableArrayList();
        ArrayList<Address> subsidiariesAddress = 
                this.main.getChilExplox().getSubsidiariesAddress();
        for (Address address : subsidiariesAddress) {
            addressStringList.add(address.getAddress());
        }
        destinyChoiceBox.setItems(addressStringList);
    }

    @FXML
    private void cancelMail(ActionEvent event) {
        main.changeScene("MailboxViewFXML.fxml",
                MailboxViewFXMLController.class);
    }

    private Mailbox getDestinyMailbox(){
        int subsidarySelected = destinyChoiceBox.getSelectionModel().getSelectedIndex();
        if (subsidarySelected >= 0){
            Address subsidaryAddress =  main.getChilExplox().
                    getSubsidiariesAddress().get(subsidarySelected);
            Mailbox mailbox = main.getChilExplox().
                    getSubsidiary(subsidaryAddress).getMailbox();
            return mailbox;
        }
        return null;
    }

    private MessageType getMessageType(){
        boolean error = errorCheckBox.isSelected();
        if (error){
            return MessageType.Error;
        }
        return MessageType.Regular;
    }
    
    private Message initializeMail(){
        ChilExplox chilexplox = main.getChilExplox();
        Mailbox origin = chilexplox.getCurrentSubsidiary().getMailbox();
        Mailbox destiny = getDestinyMailbox();
        String subject = subjectTextField.getText();
        String content = contentTextArea.getText();
        return new Message(origin,destiny, subject, content, getMessageType());
    }
    
    @FXML
    private void sendMail(ActionEvent event) {
        Message mail = initializeMail();
        if (mail.getDestinyMailbox() != null){
            main.getChilExplox().getCurrentSubsidiary().getMailbox().
                sendMessage(mail);
        }
        main.changeScene("MailboxViewFXML.fxml",
                MailboxViewFXMLController.class);
    }
    
}
