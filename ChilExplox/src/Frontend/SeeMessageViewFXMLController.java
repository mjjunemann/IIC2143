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
public class SeeMessageViewFXMLController implements Initializable, iController {

    ChilExploxApp main;
    @FXML
    private Button returnButton;
    @FXML
    private Button replyButton;
    @FXML
    private TextField senderTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea contentTextArea;
    
    Message message;
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
    }
    
    public void setMessage(Message message){
        this.message = message;
        Mailbox originMailbox = message.getOriginMailbox();
        String origin = originMailbox.getSubsidaryAddress().getAddress();
        senderTextField.setText(origin);
        subjectTextField.setText(message.getSubject());
        contentTextArea.setText(message.getContent());
                
    }

    @FXML
    private void returnToMailbox(ActionEvent event) {
        main.changeScene("MailboxViewFXML.fxml",
                MailboxViewFXMLController.class);
    }

    @FXML
    private void replyMessage(ActionEvent event) {
        main.changeScene("CreateMailViewFXML.fxml",
                CreateMailViewFXMLController.class);
    }
    
}
