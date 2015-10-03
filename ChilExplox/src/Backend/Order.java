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
public class Order 
{
    private ArrayList<Parcel> parcels;
    private Date sales_date;
    private Date delivery_date;
    private boolean calculated;
    private float total_price;
    
    /*constructor para testear */
    public Order()
    {
        this.parcels = new ArrayList<>();
        this.calculated = false;
        this.total_price = 0;
    }
    public Order(Date date)
    {
        this.parcels = new ArrayList<>();
        this.calculated = false;
        this.total_price = 0;
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
        
    }
    
    
}
