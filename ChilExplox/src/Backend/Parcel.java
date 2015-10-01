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
public class Parcel 
{
    private float weight;
    private float volume;
    private int priority;
    private final int origin;
    private int destination;
    private State state;
    
    public Parcel(float weight,float volume,int priority,int origin,int destination)
    {
        this.origin = origin;
        this.state = State.Origin;
        
        this.weight = weight;
    }
    
    public void updateStatus()
    {
        
    }
    //<editor-fold desc="Setter&Getters>
     public int getDestination()
     {
         return this.destination;
     }
     public void setDestination(int subsidiary)
     {
         this.destination = subsidiary;
     }
    //</editor-fold>
    
}
