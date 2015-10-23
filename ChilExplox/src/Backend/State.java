/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Fernando
 */
public enum State {
    Origin,OnTransit,Destination,Delivered;
    
    private String str;
    static {
        Origin.str = "Origin";
        OnTransit.str = "On Transit";
        Destination.str = "Destination";
        Delivered.str = "Delivered";
    }
    public String toString(){
        return str;
    }

}