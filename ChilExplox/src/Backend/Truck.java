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
public class Truck extends ITransport implements java.io.Serializable{
    String license_plate;
    int max_parcels ;
    State availability;
    ArrayList<Parcel> parcels;
    Subsidiary home_sub;
    
    public Truck (String S, int max, Subsidiary sub) {
        license_plate = S;
        max_parcels = max;
        parcels = new ArrayList<>();
        availability = State.Origin;
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
        for(Parcel p: this.parcels){
            p.updateStatus();
        }
        this.availability = State.OnTransit;
        this.availability = State.Destination;
    }
    @Override
    public ArrayList<Parcel> unload(){
        ArrayList<Parcel> arrived = new ArrayList<>();
        for(Parcel p: this.parcels){
            p.updateStatus();
            arrived.add(p);
        }
        this.parcels.clear();
        this.availability = State.Delivered;
        return arrived;
    }
    @Override
    public void sendBack(){
        this.availability = State.Origin;
    }
    @Override
    protected int checkSpace(){
        return max_parcels - parcels.size();
    }
    
    public State getState(){
        return this.availability;
    }
    public ArrayList<Parcel> getParcels(){
        return this.parcels;
    }
}
