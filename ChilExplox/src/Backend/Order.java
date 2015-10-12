/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author matia
 */
public class Order implements java.io.Serializable
{
    private ArrayList<Parcel> parcels;
    private Date sales_date;
    private Date delivery_date;
    private boolean calculated;
    private float total_price;
    private Client client;
    private State state;
    /*constructor para testear */
    public Order()
    {
        this.parcels = new ArrayList<>();
        this.calculated = false;
        this.total_price = 0;
        this.state = State.Origin;
    }
    
    /**
     * Constructor receive a date.
     * @param date of the sale
     */
    public Order(Date date)
    {
        this.parcels = new ArrayList<>();
        this.calculated = false;
        this.total_price = 0;
        this.sales_date = date;
    }
    
    
    /**
     * Constructor receive a date.
     * @param date of the sale
     */
    public Order(Client client, Date date)
    {
        this.parcels = new ArrayList<>();
        this.calculated = false;
        this.total_price = 0;
        this.sales_date = date;
        this.client = client;
    }
    
    /**
     * Add a parcel to the order.
     * @param parcel 
     */
    public void addParcel(Parcel parcel)
    {
        this.calculated = false;
        this.parcels.add(parcel);
    }
    /**
     * Get the total price of the order
     * @return total price.
     */
    public float getTotal()
    {
        if (!this.calculated)
        {
            this.total_price = BudgetCalculator.calculateTotal(this.parcels);
            this.calculated = true;
        }
        return this.total_price;
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
            this.state = State.OnTransit;
        }else if (destination){
            this.state = State.Destination;
        }else if (delivered){
            this.state = State.Delivered;
        }else{
            this.state = State.Origin;
        }
    }
    
    /**
     * Get the client who made the order
     * @return client
     */
    public Client getClient(){
        return this.client;
    }
    
    /**
     * Get the date of the sale
     * @return 
     */
    public Date getSaleDate(){
        return this.sales_date;
    }
    
    public State getState(){
        return this.state;
    }
    
    public ArrayList<Parcel> getParcel(){
        return this.parcels;
    }
    
    
}
