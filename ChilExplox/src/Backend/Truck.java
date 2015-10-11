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
    State dispatch_status;
    ArrayList<Parcel> parcels;
    Subsidiary home_sub;
    
    public Truck (String S, int max, Subsidiary sub) {
        license_plate = S;
        max_parcels = max;
        parcels = new ArrayList<>();
        dispatch_status = State.Origin;
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
        this.dispatch_status = State.OnTransit;
    }
    @Override
    public ArrayList<Parcel> unload(){
        ArrayList<Parcel> p = (ArrayList<Parcel>)this.parcels.clone();
        this.parcels = new ArrayList<>();
        return p;
    }
    @Override
    public void sendBack(){
        this.dispatch_status = State.Origin;
    }
    @Override
    protected int checkSpace(){
        return max_parcels - parcels.size();
    }
    
}
