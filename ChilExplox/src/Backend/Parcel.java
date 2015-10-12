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
enum State {Origin,OnTransit,Destination,Delivered}

public class Parcel implements java.io.Serializable
{
    private float weight;
    private float volume;
    private int priority;
    public final Address origin;
    public Address destination;
    private State state;
    private Order order;
    
    /**
     * 
     * @param weight 
     * @param volume
     * @param priority
     * @param origin
     * @param destination 
     */
    public Parcel(float weight,float volume,int priority,Address origin,Address destination,Order order)
    {
        this.origin = origin;
        this.state = State.Origin;
        
        this.destination = destination;
        this.volume = volume; 
        this.weight = weight;
        this.order = order;
    }
    
    public void updateStatus()
    {/*This method will only be called when status must change and it does
        so without checking correctness, must be carefull when called.*/
        if (this.state == State.Origin){
            this.state = State.OnTransit;
        }else if (this.state == state.OnTransit){
            this.state = State.Destination;
        }else{
            this.state = State.Delivered;
        }
        /*Every time a parcel changes status, the whole order may change
        status. Example: one parcel was missing from being delivered, once is 
        delivered, the whole order is delivered.
        So It calls the update Status from Order.
        */
        order.updateStatus();
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
     public State getState()
     {
         return this.state;
     }
    //</editor-fold>
    
}
