/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author matia
 */
enum State {Origin,OnTransit,Delivered}
public class Parcel implements java.io.Serializable
{
    private float weight;
    private float volume;
    private int priority;
    public final Address origin;
    public Address destination;
    private State state;
    
    /**
     * 
     * @param weight 
     * @param volume
     * @param priority
     * @param origin
     * @param destination 
     */
    public Parcel(float weight,float volume,int priority,Address origin,Address destination)
    {
        this.origin = origin;
        this.state = State.Origin;
        
        this.destination = destination;
        this.volume = volume; 
        this.weight = weight;
    }
    
    public void updateStatus()
    {
        
    }
    //<editor-fold desc="Setter&Getters">
     public Address getDestination()
     {
         return this.destination;
     }
     public void setDestination(Address subsidiary)
     {
         this.destination = subsidiary;
     }
    //</editor-fold>
    
}
