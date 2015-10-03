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
    
    public static float calculateTotal(ArrayList<Parcel> parcels)
    {
        float total = 0;
        for (Parcel parcel: parcels)
        {
            total+= BudgetCalculator.calculateParcel(parcel);
        }
        return total;
    }
    
    public static float calculateParcel(Parcel parcel)
    {
        float a = 10;
        return a;
    }
}
