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
public abstract class ITransport 
{
    public String license_plate;
    private ArrayList<Parcel> parcels;
    public State availability;
    private Subsidiary home_sub;
    public abstract boolean loadParcel(Parcel parcel);
    public abstract void send(Address addr);
    public abstract void sendBack();
    public abstract void sendBackError();
    public abstract ArrayList<Parcel> unloadAll();
    public abstract String getPlate();
    protected abstract int checkSpace();
  
}
