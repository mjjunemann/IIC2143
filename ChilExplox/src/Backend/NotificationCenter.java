/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.util.*;
/**
 *
 * @author matia
 */
public class NotificationCenter 
{
  private Map<String,ArrayList<Notification>> notifications;
  private ArrayList<Notification> displayed_notifations;
  private Object lock;
  
  /**
   * Show a notification on 
   * @param notification 
   */
  public void showNotification(Notification notification)
  {
  
  }
  
  /**
   * Show a group of notifications in the front end application in
   * a notification center. 
   * @param NotificationList Receives a list of notifications.
   */
  public void displayNotificationTipe(ArrayList<Notification> NotificationList)
  {
      for(Notification not: NotificationList)
      {
          
      }
  }
}
