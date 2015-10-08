/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author guillermofigueroa
 */
public class Messaging {

    public Messaging(){
        
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
            String content){
        Message message = createMessage(origin, destiny, subject, content);
        return origin.sendMessage(destiny, message);
    }   
    
    public Message createMessage(Mailbox origin, Mailbox destiny, 
            String subject, String content){
        return new Message(origin, destiny, subject, content);
    }
    
}

