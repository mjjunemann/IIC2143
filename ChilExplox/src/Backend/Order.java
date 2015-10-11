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
    /*this method update the status of the order, if all the parcels are 
        delivered change the status to delivered, if one is on transit or
        some are at origin and others at delivered, the hole order is on transit
        finally, if all parcels are at origin, the order as well.*/
    public void updateStatus()
    {
        Boolean origin = true;
        Boolean delivered = true;
        for( Parcel p : this.parcels){
            if ( p.state == State.OnTransit){
                delivered = false;
                origin = false;
                break;
            }
            else if (p.state == State.Delivered){
                origin = false;
            }
            else{
                delivered = false;
            }
        }
        if( !origin && !delivered){
            this.state = State.OnTransit;
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
    
    
}
