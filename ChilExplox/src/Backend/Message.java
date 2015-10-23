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

public final class Message implements java.io.Serializable {
    private Mailbox origin;
    private Mailbox destiny;
    private String subject;
    private String content;
    private MessageType type;
    private boolean seen = false;
    
    public Message(Mailbox origin, Mailbox destiny, String subject,
            String content, MessageType type){
        this.setOriginMailbox(origin);
        this.setDestinyMailbox(destiny);
        this.setSubject(subject);
        this.setContent(content);
        this.type = type;
    }
    
    
    //<editor-fold desc="Setter & Getters">
   
    /**
     * Get the origin Mailbox of the message
     * @return Mailbox
     */
    public Mailbox getOriginMailbox()
    {
        return this.origin;
    }
    
    /**
     * Set the origin Mailbox of the message
     * @param mailbox 
     */
    public void setOriginMailbox(Mailbox mailbox){
        this.origin = mailbox;
    }

    /**
     * Get the destiny Mailbox of the message
     * @return Mailbox
     */
    public Mailbox getDestinyMailbox()
    {
        return this.destiny;
    }
    
    /**
     * Set the destiny Mailbox of the message
     * @param mailbox 
     */
    public void setDestinyMailbox(Mailbox mailbox){
        this.destiny = mailbox;
    }
    
   
    /**
     * Get the subject of the message
     * @return 
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Set the subject of the message
     * @param subject 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Get the content of the message
     * @return 
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content of the message
     * @param content 
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean getSeen() {
        return this.seen;
    }
    
    public MessageType getMessageType(){
        return this.type;
    }

    public boolean isErrorMessage(){
        return this.type == MessageType.Error;
    }
    /**
     * Set the content of the message
     * @param content 
     */
    public void setSeen(boolean b) {
        this.seen = b;
    }
    //</editor-fold>
    
}
