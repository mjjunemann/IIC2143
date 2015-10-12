/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author matia
 */
public class BudgetCalculator 
{
    /**
     * Given a list of parcels return the total price  
     * @param parcels 
     * @return total price 
     */
    public static float calculateTotal(ArrayList<Parcel> parcels)
    {
        float total = 0;
        for (Parcel parcel: parcels)
        {
            total+= BudgetCalculator.calculateParcel(parcel);
        }
        System.out.print("All parcels costs: "+ total + " \n");
        return total;
    }
    
    
    /**
     * Calculate the individual price of a parcel
     * @param parcel
     * @return price of a parcel
     */
    public static float calculateParcel(Parcel parcel)
    {
        float total = parcel.getVolume()*10 + parcel.getWeight()*20;
        System.out.print("Parcel costs: "+ total + " \n");
        return total;
    }
}
