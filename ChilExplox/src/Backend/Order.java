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
    private ArrayList<Parcel> parcels = new ArrayList<>();
    private Date sales_date;
    private Date delivery_date;
    
    private float total_price;
    
    public Order(Date date)
    {
        this.total_price = 0;
    }
    
    public void addParcel(Parcel parcel)
    {
        parcels.add(parcel);
    }
    
    public float calculatePrice()
    {
        return 0;
    }
    
    public void updateStatus()
    {
        
    }
    
    
}
