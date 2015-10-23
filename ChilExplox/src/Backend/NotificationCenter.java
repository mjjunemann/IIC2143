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
public class NotificationCenter implements java.io.Serializable
{
    /*
  private Map<String,ArrayList<Notification>> notifications;
  private ArrayList<Notification> displayed_notifations;
  private Object lock;
  */
    
  private ArrayList<Notification> unsolved_notifications;
  private ArrayList<Notification> solved_notifications;
  
  public NotificationCenter(){
      this.unsolved_notifications = new ArrayList<>();
      this.solved_notifications = new ArrayList<>();
  }
  /**
   * Show a notification on 
   * @param notification 
   *
   * /public void showNotification(Notification notification)
  {
  
  }*/
  
  /*Add a notification for every order*/
  public void addOrderNotification(String id, Order o)
  {
      Notification n = new Notification("Must send Order: "+id,o.getPriority());
      unsolved_notifications.add(n);
      /* Maybe add a notification for each parcel?
      for(Parcel p: o.getParcel()){
          n = new Notification("Must send Parcel: "+id,p.getPriority());
          unsolved_notifications.add(n);
      */
  }
  public void solveNotification(Notification n){
      this.unsolved_notifications.remove(n);
      this.solved_notifications.add(n);
  }
  public ArrayList<Notification> getUnsolvedNot(){
      return this.unsolved_notifications;
  }
  public ArrayList<Notification> getSolvedNot(){
      return this.solved_notifications;
  }
  /**
   * Show a group of notifications in the front end application in
   * a notification center. 
   * @param NotificationList Receives a list of notifications.
   * /
  public void displayNotificationTipe(ArrayList<Notification> NotificationList)
  {
      for(Notification not: NotificationList)
      {
          
      }
  }*/
}
