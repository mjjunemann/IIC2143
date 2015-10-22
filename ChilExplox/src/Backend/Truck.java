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
        availability = State.Origin; /* Truck starts at origin.*/
        home_sub = sub;
    }
    
    public String getPlate(){
        return this.license_plate;
    }
    
    public State getAvaibility(){
        return this.availability;
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
            p.updateStatus();/* On transit.*/
            p.updateStatus();/* Arrives immediately. Destination.*/
        }
        this.availability = State.OnTransit;/* Truck on transit.*/
        this.availability = State.Destination;/* Arrives immediately.*/
    }
    @Override
    public ArrayList<Parcel> unload(){
        ArrayList<Parcel> arrived = new ArrayList<>();
        for(Parcel p: this.parcels){
            p.updateStatus();/* Delivered.*/
            arrived.add(p);
        }
        this.parcels.clear();
        this.availability = State.Delivered; /*When unloads, it deliveres.*/
        return arrived;
    }
    @Override
    public void sendBack(){
        this.availability = State.Origin; /*Truck goes back to origin */
    }
    @Override
    public int checkSpace(){
        return max_parcels - parcels.size();
    }
    
    public State getState(){
        return this.availability;
    }
    public ArrayList<Parcel> getParcels(){
        return this.parcels;
    }
}
