/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author matia
 */
public class Order implements java.io.Serializable
{
    private ArrayList<Parcel> parcels;
    private SimpleObjectProperty<Date> sales_date;
    private SimpleObjectProperty<Date> delivery_date;
    private boolean calculated;
    private SimpleFloatProperty total_price;
    private SimpleObjectProperty<Client> client;
    private SimpleObjectProperty<State> state;
    private SimpleStringProperty orderId;
    
    private int parcelIdCounter = 100;
    /*constructor para testear */
    public Order(String id)
    {
        setId(id);
        this.parcels = new ArrayList<>();
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
        this.parcels = new ArrayList<>();
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
        this.parcels = new ArrayList<>();
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
        String id = orderId + String.valueOf(parcelIdCounter);
        parcelIdCounter++;
        Parcel parcel = new Parcel(weight, volume, priority, origin, destination, this, id);
        this.parcels.add(parcel);
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
        for( Parcel p : this.parcels){
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

    public final ArrayList<Parcel> getParcel()
    {
        return this.parcels;
    }
    /*
    We define - for now - the prioriy of an order as the max priority of the 
    parcels that make up the order.
    */
    public int getPriority(){
        int max = this.parcels.get(0).getPriority();
        int n;
        for( Parcel p : this.parcels){
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
        return this.orderId + ", price: " + this.total_price;
    }
    //<editor-fold desc="Properties">
    /*
    public final ObjectProperty<ArrayList<Parcel>> parcelProperty()
    {
        if(parcels == null)
        {
            parcels = new SimpleObjectProperty<ArrayList<Parcel>>();
        }
        return parcels;
    }
    */
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
    
}