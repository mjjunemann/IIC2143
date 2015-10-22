/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author guillermofigueroa
 */
public class Messaging implements java.io.Serializable{

    private ArrayList<Mailbox> mailboxes;
    
    public Messaging(){
        this.mailboxes = new ArrayList<>();
    }
    
    public void addMailBox(Mailbox m){
        this.mailboxes.add(m);
    }
    
    /**
     * Sends a message from a mailbox to another
     * @param origin
     * @param destiny
     * @param subject
     * @param content
     * @return boolean: true if message was sent
     */
    public boolean sendMessage(Mailbox origin, Mailbox destiny, String subject, 
            String content, MessageType type){
        Message message = createMessage(origin, destiny, subject, content, type);
        return origin.sendMessage(message) && destiny.receiveMessage(message);
    }   
    
    private Message createMessage(Mailbox origin, Mailbox destiny, 
            String subject, String content, MessageType type){
        return new Message(origin, destiny, subject, content, type);
    }
    
}

