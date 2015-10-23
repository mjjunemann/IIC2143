/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import javafx.beans.Observable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Callback;

/**
 *
 * @author matia
 */

public class Parcel implements java.io.Serializable
{
    private transient SimpleFloatProperty weight;
    private transient SimpleFloatProperty volume;
    private transient SimpleIntegerProperty priority;
    public Address origin;
    public Address destination;
    private State state;
    private Order order;
    
    /**
     * 
     * @param weight 
     * @param volume
     * @param priority
     * @param origin
     * @param destination 
     */
    public Parcel(float weight,float volume,int priority,Address origin,Address destination,Order order)
    {
        this.origin = origin;
        this.state = State.Origin;
        
        this.destination = destination;
        setVolume(volume); 
        setWeight(weight);
        this.order = order;
    }
    
    public void updateStatus()
    {/*This method will only be called when status must change and it does
        so without checking correctness, must be carefull when called.*/
        if (this.state == State.Origin){
            this.state = State.OnTransit;
        }else if (this.state == state.OnTransit){
            this.state = State.Destination;
        }else{
            this.state = State.Delivered;
        }
        /*Every time a parcel changes status, the whole order may change
        status. Example: one parcel was missing from being delivered, once is 
        delivered, the whole order is delivered.
        So It calls the update Status from Order.
        */
        order.updateStatus();
    }
    //<editor-fold desc="Setter&Getters">
     public Address getDestination()
     {
         return this.destination;
     }
     public void setDestination(Address subsidiary)
     {
         this.destination = subsidiary;
     }
     public void setOrigin(Address subsidiary)
     {
         this.origin = subsidiary;
     }
     public State getState()
     {
         return this.state;
     }
     public final void setPriority(int priority)
     {
         priorityProperty().set(priority);
     }
     public final int getPriority()
     {
         return priorityProperty().get();
     }
     public final void setWeight(float weight)
     {
         weightProperty().set(weight);
     }
     public final float getWeight()
     {
         return weightProperty().get();
     }
     public final void setVolume(float volume)
     {
         volumeProperty().set(volume);
     }
     public final float getVolume()
     {
         return volumeProperty().get();
     }
    //</editor-fold>
    
     //<editor-fold desc="Properties">
      public final FloatProperty weightProperty()
      {
          if (weight == null)
          {
              weight = new SimpleFloatProperty();
          }
          return weight;
      }
      /*
      public final ObjectProperty<Address> addressProperty()
      {
          if (destination == null)
          {
              destination = new Address();
          }
          return destination;
      }
      */
      public final FloatProperty volumeProperty()
      {
          if(volume == null)
          {
              volume = new SimpleFloatProperty();
          }
          return volume;
      }
      public final IntegerProperty priorityProperty()
      {
          if(priority == null)
          {
              priority = new SimpleIntegerProperty();
          }
          return priority;
      }
     //</editor-fold>
      
    public static  Callback<Parcel,Observable[]> extractor()
    {
        return (Address p) -> new Observable[]{p.weightProperty(),p.volumeProperty(),p.priorityProperty()};
    }
    
}
