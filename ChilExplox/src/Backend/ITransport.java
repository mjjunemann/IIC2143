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
    private ArrayList<Parcel> parcels;
    public String dispatch_status;
    public abstract void loadParcel(Parcel parcel);
    public abstract void send(Address addr);
    public abstract void sendBack();
    public abstract int checkSpace();
  
}
