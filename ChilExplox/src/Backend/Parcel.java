/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private transient SimpleStringProperty parcelId;
    public transient SimpleObjectProperty<Address> origin;
    public transient SimpleObjectProperty<Address> destination;
    private transient SimpleObjectProperty<State> state;
    private transient SimpleObjectProperty<Type> type;
    private transient SimpleObjectProperty<Order> order;

    
    /**
     * 
     * @param type
     * @param weight 
     * @param volume
     * @param priority
     * @param origin
     * @param destination 
     */
    public Parcel(Type type,float weight,float volume,int priority,Address origin,Address destination,Order order, String id)
    {
        setOrigin(origin);
        setType(type);
        setState(State.Origin);
        setId(id);
        setDestination(destination);
        setVolume(volume); 
        setWeight(weight);
        setOrder(order);
        setPriority(priority);
    }
 
    
    public void updateStatus()
    {/*This method will only be called when status must change and it does
        so without checking correctness, must be carefull when called.*/
        State tmp_state = getState();
        if (tmp_state == State.Origin){
            setState(State.OnTransit);
        }else if (tmp_state == State.OnTransit){
            setState(State.Destination);
        }else{
            setState(State.Delivered);
        }
        /*Every time a parcel changes status, the whole order may change
        status. Example: one parcel was missing from being delivered, once is 
        delivered, the whole order is delivered.
        So It calls the update Status from Order.
        */
        getOrder().updateStatus();
    }
    //<editor-fold desc="Setter&Getters">
     public final Address getDestination()
     {
         return destinationProperty().get();
     }
     public final void setDestination(Address subsidiary)
     {
         destinationProperty().set(subsidiary);
     }
     public Order getOrder()
     {
         return orderProperty().get();
     }
     public void setOrder(Order o)
     {
         orderProperty().set(o);
     }
     public final Address getOrigin()
     {
         return originProperty().get();
     }
     public final void setOrigin(Address subsidiary)
     {
         originProperty().set(subsidiary);
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
         return this.volumeProperty().get();
     }
     
     public final String getId()
     {
         return this.idProperty().get();
     }
     
     public final void setId(String id)
     {
         this.idProperty().set(id);
     }
     public final void setState(State s)
     {
         this.stateProperty().set(s);
     }
     public State getState()
     {
         return this.stateProperty().get();
     }
     public final void setType(Type t)
     {
         this.typeProperty().set(t);
     }
     public Type getType()
     {
         return this.typeProperty().get();
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
      public final ObjectProperty<Address> originProperty()
      {
          if (origin == null)
          {
              origin = new SimpleObjectProperty<Address>();
          }
          return origin;
      }
      public final ObjectProperty<Address> destinationProperty()
      {
          if (destination == null)
          {
              destination = new SimpleObjectProperty<Address>();
          }
          return destination;
      }
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
      public final StringProperty idProperty()
      {
          if (parcelId == null)
          {
              parcelId = new SimpleStringProperty();
          }
          return parcelId;
      }
      
      public final ObjectProperty<Type> typeProperty()
      {
          if (type == null)
          {
              type = new SimpleObjectProperty();
          }
          return type;
      }
      public final ObjectProperty<State> stateProperty()
      {
          if (state == null)
          {
              state = new SimpleObjectProperty();
          }
          return state;
      }
      public final ObjectProperty<Order> orderProperty()
      {
          if (order == null)
          {
              order = new SimpleObjectProperty();
          }
          return order;
      }
     //</editor-fold>
     
    public static  Callback<Parcel,Observable[]> extractor()
    {
        return (Parcel p) -> new Observable[]{p.weightProperty(),p.volumeProperty(),
            p.priorityProperty(),p.originProperty(),
            p.destinationProperty(),p.idProperty(),
            p.stateProperty(),p.typeProperty(),p.orderProperty()
        };
    }
    
    
    private void writeObject(ObjectOutputStream oos)
    throws IOException
    {
      oos.defaultWriteObject();
      List params = new ArrayList<>();
      params.add(getWeight());
      params.add(getVolume());
      params.add(getPriority());
      oos.writeObject(params);
      oos.writeObject(getOrigin());
      oos.writeObject(getDestination());
      oos.writeObject(getId());
      oos.writeObject(getState());
      oos.writeObject(getType());
      oos.writeObject(getOrder());
      }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException,IOException
    {
        ois.defaultReadObject();
        List params = (List)ois.readObject();
        Address origin = (Address)ois.readObject();
        Address destiny = (Address)ois.readObject();
        String id = (String)ois.readObject();
        this.setWeight((float) params.get(0));
        this.setVolume((float) params.get(1));
        this.setPriority((int) params.get(2));     
        this.setOrigin(origin);
        this.setDestination(destiny);
        this.setId(id);
        this.setState((State) ois.readObject());
        this.setType((Type) ois.readObject());
        this.setOrder((Order) ois.readObject());
    }
}   
