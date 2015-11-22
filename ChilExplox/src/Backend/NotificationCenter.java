/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.util.*;
import javax.swing.event.*;
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
  EventListenerList listeners = new EventListenerList();
  ChilExplox main;  
  
  public NotificationCenter(ChilExplox main){
      this.main = main;
      this.unsolved_notifications = new ArrayList<>();
      this.solved_notifications = new ArrayList<>();
      
  }
  
  public Timer initializeTimer(){
      Timer timer = new Timer();
      timer.schedule(new NotificationTask(), 0, 30000);
      return timer;
  }
  
  public void cleanListener(){
      listeners = new EventListenerList();
  }
  
  public void addListener(iNotificationListener listener){
      listeners.add(iNotificationListener.class, listener);
  }
  
  public void removeListener(iNotificationListener listener){
      listeners.remove(iNotificationListener.class, listener);
  }
  /**
   * Show a notification on 
   * @param notification 
   *
   * /public void showNotification(Notification notification)
  {
  
  }*/
  
  
  /**
   * Add a notification for every order
   * @param o 
   */
  public void addOrderNotification(Order o)
  {
      Notification n = new Notification("Must send Order: "+o.getId(),o.getPriority());
      this.unsolved_notifications.add(n);
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
  
  public boolean shouldNotify(Parcel parcel){
      int priority = parcel.getPriority();
      Date saleDate = parcel.getOrder().getSaleDate();
      Date today = new Date();
      long difference = today.getTime() - saleDate.getTime();
      float daysDifference = (float)difference / 1000; /// 60 / 24;
      if (daysDifference > 120){
          return true;
      }
      return false;
  }
  
  public ArrayList<Parcel> parcelsToNotify(){
      Subsidiary subsidiary = this.main.getCurrentSubsidiary();
      ArrayList<Parcel> parcelsShouldBeNotified = new ArrayList<Parcel>();
     
      if (this.main.getCurrentLogged() != null && (
              this.main.getCurrentLogged().getRole().equals(Role.Administrator) 
              || this.main.getCurrentLogged().getRole().equals(Role.User))){
        if (subsidiary != null){
            for (Order order: subsidiary.getOrders().values()){
                for (Parcel parcel: order.getParcels()){
                    if (shouldNotify(parcel)){
                      parcelsShouldBeNotified.add(parcel);
                    }
                }
            }
        }
      }
      return parcelsShouldBeNotified;
  }
  
  public void stopTimer(){
      
      //timer.cancel();
      //timer.purge();
              
  }
  
  class NotificationTask extends TimerTask {
      @Override
      public void run(){
          
          ArrayList<Parcel> parcels = parcelsToNotify();
          for (Parcel parcel: parcels){
            Object[] listenersList = listeners.getListenerList();
            for (int i = 0; i < listenersList.length; i+= 2){
                if (listenersList[i] == iNotificationListener.class){
                    ((iNotificationListener)listenersList[i+1])
                          .showNotification(parcel);
                }
            }
          } 
      }
  }
}


