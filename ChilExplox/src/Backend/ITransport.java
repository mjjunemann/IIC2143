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
    public State dispatch_status;
    private Subsidiary home_sub;
    public abstract boolean loadParcel(Parcel parcel);
    public abstract void send(Address addr);
    public abstract void sendBack();
    public abstract ArrayList<Parcel> unload();
    protected abstract int checkSpace();
  
}
