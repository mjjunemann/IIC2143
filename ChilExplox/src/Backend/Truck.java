/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Truck extends ITransport{
    String license_plate;
    int max_parcels ;
    String dispatch_status;
    ArrayList<Parcel> parcels;
    Subsidiary home_sub;
    
    public Truck (String S, int max, Subsidiary sub) {
        license_plate = S;
        max_parcels = max;
        parcels = new ArrayList<Parcel>();
        dispatch_status = "Waiting Parcels";
        home_sub = sub;
    }
    @Override
    public boolean loadParcel(Parcel parcel){
        if ( checkSpace() > 0){
            parcels.add(parcel);
            return true;
        }
        return false;
    }
    @Override
    public void send(Address addr){
        
    }
    @Override
    public void sendBack(){
        
    }
    @Override
    protected int checkSpace(){
        return max_parcels - parcels.size();
    }
  
    
}
