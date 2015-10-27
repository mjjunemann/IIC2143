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
import java.util.Date;
import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;

/**
 *
 * @author matia
 */
public class Order implements java.io.Serializable
{
    private transient SimpleObjectProperty<ArrayList<Parcel>> parcels;
    private transient SimpleObjectProperty<Date> sales_date;
    private transient SimpleObjectProperty<Date> delivery_date;
    private boolean calculated;
    private transient SimpleFloatProperty total_price;
    private transient SimpleObjectProperty<Client> client;
    private transient SimpleObjectProperty<State> state;
    private transient SimpleStringProperty orderId;
    
    private int parcelIdCounter = 100;
    /*constructor para testear */
    public Order(String id)
    {
        setId(id);
        this.setParcels(new ArrayList<>());
        this.calculated = false;
        setTotal(0);
        setState(State.Origin);
    }
    
    /**
     * Constructor receive a date.
     * @param date of the sale
     */
    public Order(Date date,String id)
    {
        setId(id);
        this.setParcels( new ArrayList<>());
        this.calculated = false;
        setTotal(0);
        setSaleDate(date);
        this.client = null;
    }
    
    
    /**
     * Constructor receive a date.
     * @param date of the sale
     */
    public Order(Client client, Date date)
    {
        this.setParcels(new ArrayList<>());
        this.calculated = false;
        setTotal(0);
        setSaleDate(date);
        setClient(client);
    }
    
    /**
     * Add a parcel to the order.
     */    
    public Parcel addParcel(float weight,float volume,int priority,Address origin,Address destination){
        this.calculated = false;
        String id = getId() + String.valueOf(parcelIdCounter);
        parcelIdCounter++;
        Parcel parcel = new Parcel(weight, volume, priority, origin, destination, this, id);
        this.getParcel().add(parcel);
        return parcel;
    }
    /**
     * Get the total price of the order
     * @return total price.
     */
    public float getTotal()
    {
        if (!this.calculated)
        {
            setTotal(BudgetCalculator.calculateTotal(getParcel()));
            this.calculated = true;
        }
        return getTotalValue();
    }
    
    public final float getTotalValue()
    {
        return totalProperty().get();
    }
    public final void setTotal(float amount)
    {
        totalProperty().set(amount);
    }
    
    public void setId(String id)
    {
        orderIdProperty().set(id);
    }
    public String getId(){
         return orderIdProperty().get();
    }
    
    public void updateStatus()
    {
        /*This method updates the status of the Order. Since an order is the sum
        of many parcels and each one can has a different status, this method 
        will follow the following logic:
        Orden is at Origin if and only if all Parcels are at Origin.
        Orden is Delivered if and only if all Parcels are Delivered.
        Orden is at Destination if and only if 
                            all Parcels are at Destination or Delivered.
        Otherwise, Order is on Transit.
        */
        Boolean origin = true;
        Boolean destination = true;
        Boolean delivered = true;
        for( Parcel p : this.getParcel()){
            State s = p.getState();
            if ( s == State.OnTransit){
                origin = false;
                delivered = false;
                destination = false;
                break;
            }
            else if (s == State.Destination){
                origin = false;
                delivered = false;
            }
            else if (s == State.Delivered){
                origin = false;
            }
            else{
                destination = false;
                delivered = false;
            }
        }
        if( !origin && !delivered && !destination ){
            setState(State.OnTransit);
        }else if (delivered){
            setState(State.Delivered);
        }else if (destination){
            setState(State.Destination);
        }else{
            setState(State.Origin);
        }
    }
    
    /**
     * Get the client who made the order
     * @return client
     */
    public final void setClient(Client c)
    {
        clientProperty().set(c);
    }
    public Client getClient(){
        return clientProperty().get();
    }
    
    public void setSaleDate(Date date){
        saleDateProperty().set(date);
    }
    
    
    public final void setParcels(ArrayList<Parcel> parcels)
    {
        parcelProperty().set(parcels);
    }
    
    public final ArrayList<Parcel> getParcel()
    {
        return parcelProperty().get();
    }
    /**
     * Get the date of the sale
     * @return 
     */
    public Date getSaleDate(){
        return saleDateProperty().get();
    }
    
    public final void setState(State s)
    {
        stateProperty().set(s);
    }
    public final State getState(){
        return stateProperty().get();
    }

    
    public final void setDeliveryDate(Date delivery)
    {
        deliveryDateProperty().set(delivery);
    }
    public final Date getDeliveryDate()
    {
        return deliveryDateProperty().get();
    }
    /*
    We define - for now - the prioriy of an order as the max priority of the 
    parcels that make up the order.
    */
    public int getPriority(){
        int max = this.getParcel().get(0).getPriority();
        int n;
        for( Parcel p : this.getParcel()){
            n = p.getPriority();
            if (n>max){
                max = n;
            }
        }
        return max;
    }
    
    public String peekId()
    {
        String id = getId() + String.valueOf(parcelIdCounter);
        return id;
    }
    
    @Override
    public String toString(){
        return this.getId() + ", price: " + this.getTotal();
    }
    //<editor-fold desc="Properties">
    
    public final ObjectProperty<ArrayList<Parcel>> parcelProperty()
    {
        if(parcels == null)
        {
            parcels = new SimpleObjectProperty<ArrayList<Parcel>>();
        }
        return parcels;
    }
    
    public final ObjectProperty<Date> saleDateProperty()
    {
        if (sales_date == null)
        {
            sales_date = new SimpleObjectProperty<Date>();
        }
        return sales_date;
    }
    public final ObjectProperty<Date> deliveryDateProperty()
    {
        if (delivery_date == null)
        {
            delivery_date = new SimpleObjectProperty<Date>();
        }
        return delivery_date;
    }
    public final FloatProperty totalProperty()
    {
        if (total_price == null)
        {
            total_price = new SimpleFloatProperty();
        }
        return total_price;
    }
    public final ObjectProperty<Client> clientProperty()
    {
        if (client == null)
        {
            client = new SimpleObjectProperty<Client>();
        }
        return client;
    }
    public final SimpleObjectProperty<State> stateProperty()
    {
        if (state == null)
        {
            state = new SimpleObjectProperty<State>();
        }
        return state;
    }
    public final SimpleStringProperty orderIdProperty()
    {
        if (orderId == null)
        {
            orderId = new SimpleStringProperty();
        }
        return orderId;
    }
    //</editor-fold>
    
    public static  Callback<Order,Observable[]> extractor()
    {
        return (Order o) -> new Observable[]
        {
         o.saleDateProperty(),o.clientProperty(),o.deliveryDateProperty(),o.stateProperty(),
            o.totalProperty(),o.orderIdProperty()
        };
    }
    
     private void writeObject(ObjectOutputStream oos)
    throws IOException
    {
      oos.defaultWriteObject();
      oos.writeObject(this.getId());
      oos.writeObject(this.getParcel());
      
      oos.writeObject(this.getSaleDate());
      oos.writeObject(this.getDeliveryDate());
      
      oos.writeObject(this.getClient());
      oos.writeObject(this.getTotal());
      oos.writeObject(this.getState());
      
      
      }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException,IOException
    {
        
        // deliveryDate
        // parcels cambiar 
        ois.defaultReadObject();
        this.setId((String)ois.readObject());
        this.setParcels((ArrayList<Parcel>) ois.readObject());
        
        this.setSaleDate((Date)ois.readObject());
        this.setDeliveryDate((Date)ois.readObject());
        
        this.setClient((Client)ois.readObject());
        this.setTotal((Float)ois.readObject());
        this.setState((State)ois.readObject());
    }
    
}