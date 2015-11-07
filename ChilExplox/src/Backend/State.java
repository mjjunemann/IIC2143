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
    Origin,OnTransit,Destination,Delivered,OriginError;
    
    private String str;
    static {
        Origin.str = "Origin";
        OnTransit.str = "On Transit";
        Destination.str = "Destination";
        Delivered.str = "Delivered";
        OriginError.str = "Origin with Error";
    }
    public String toString(){
        return str;
    }
    public static State lookup(String param)
        {
            for(State state :State.values())
            {
                if (state.toString().equalsIgnoreCase(param.trim()))
                {
                    return state;
                }
            }
            return null;
            
        }
}