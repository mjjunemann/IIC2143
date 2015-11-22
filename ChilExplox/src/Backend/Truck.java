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
    Address destiny;
    Type type;
    String driver_rut;
    String driver_name;
    
    public Truck (String S, int max, Type t ,Subsidiary sub) {
        license_plate = S;
        max_parcels = max;
        parcels = new ArrayList<>();
        availability = State.Origin; /* Truck starts at origin.*/
        home_sub = sub;
        destiny = null;
        type = t;
        this.driver_rut = "12345678-9";
        this.driver_name = "Juan";
    }
    
    public Truck (String S, int max, Type t ,Subsidiary sub, String rut, String name){
        license_plate = S;
        max_parcels = max;
        parcels = new ArrayList<>();
        availability = State.Origin; /* Truck starts at origin.*/
        home_sub = sub;
        destiny = null;
        type = t;
        this.driver_rut = rut;
        this.driver_name = name;
    }
    
    public String getDriver(){
        return this.driver_rut;
    }
    
    public String getPlate(){
        return this.license_plate;
    }
    public State getAvaibility(){
        return this.availability;
    }
    public Address getDestiny(){
        return this.destiny;
    }
    public Address getHomeAddress(){
        return home_sub.getAddr();
    }
    public String getDestinyString(){
        if (destiny == null) {
            return "-";
        }else{
            return destiny.toString();
        }
    }
    public Type getType(){
        return type;
    }
    public boolean canParcelLoad(Parcel parcel){
        if (destiny == null) {
            return type.equals(parcel.getType());
        }else if(checkSpace() > 0){
            return destiny.equals(parcel.getDestination()) && type.equals(parcel.getType());
        }else{
            return false;
        }
    }
    public boolean unload(Parcel parcel){
        if (parcels.contains(parcel)) {
            parcels.remove(parcel);
            if(parcels.size()==0){
                destiny = null;
                if (availability==State.OriginError) {
                    fix();
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public boolean loadParcel(Parcel parcel){
        parcels.add(parcel);
        if (destiny == null) {
            destiny = parcel.getDestination();
        }
        return true;
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
    public Parcel unloadArrived(Parcel p){
        if (parcels.contains(p)) {
            p.updateStatus();
            parcels.remove(p);
            if (parcels.size()==0) {
                this.availability = State.Delivered;
                this.destiny = null;
            }
            return p;
        }
        return null;
    }
    @Override
    public ArrayList<Parcel> unloadAll(){
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
    public void sendBackError(){
        this.availability = State.OriginError; /*Truck goes back to origin with error */
    }
    public void fix(){
        if (this.availability==State.OriginError) {
            this.availability = State.Origin;
        }
    }
    @Override
    public int checkSpace(){
        return max_parcels - parcels.size();
    }
    
    public State getState(){
        return this.availability;
    }
    public int getMaxParcels(){
        return this.max_parcels;
    }
    public ArrayList<Parcel> getParcels(){
        return this.parcels;
    }
}
