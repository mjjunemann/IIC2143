/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.util.LinkedList;

/**
 *
 * @author guillermofigueroa
 */
public class Mailbox implements java.io.Serializable
{
    private final LinkedList<Message> sentMessages;
    private final LinkedList<Message> receivedMessages;
    
    public Mailbox(){
        sentMessages = new LinkedList<>();
        receivedMessages = new LinkedList<>();
        
    }
    
    /**
     * Sends a message from this mailbox to another
     * @param destiny
     * @param message
     * @return boolean: true if the message was sent
     */
    public boolean sendMessage( Message message){
        sentMessages.add(message);
        return true;
    }
    
    /**
     * A message is received by this mailbox
     * @param message 
     */
    public boolean receiveMessage(Message message){
        System.out.println("Message received: " + message.getContent());
        receivedMessages.add(message);
        return true;
    }
    
    /**
     * Getter of sentMessages
     * @return 
     */
    public LinkedList<Message> getSentMessages(){
        return this.sentMessages;
    }
    
    /**
     * Setter of sentMessages
     * @return 
     */
    public LinkedList<Message> getReceivedMessages(){
  
        return this.receivedMessages;
    }
    
    
}
