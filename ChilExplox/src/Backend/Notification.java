/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Date;
import java.util.concurrent.*;
/**
 *
 * @author matia
 */
/*
public abstract class Notification 
{
  private String content;
  public abstract String getContent();
  public abstract void action();
}
*/
public class Notification
{
    private String content;
    private int initial_priority;
    private int priority;
    private Date initial_date;
    
    public Notification(String content, int priority){
        this.content = content;
        this.initial_date = new Date();
        this.priority = priority;
        this.initial_priority = this.priority;
    }
    /*
    Priority increases with the amount of hours passed from the creation of the
    notification.
    */
    public int updatePriority(){
        Date current = new Date();
        long difDate = TimeUnit.HOURS.convert
        (current.getTime()-this.initial_date.getTime(),TimeUnit.MILLISECONDS);
        int dif = (int)difDate/5 + 1;
        this.priority = dif * this.initial_priority;
        return this.priority;
    }
}