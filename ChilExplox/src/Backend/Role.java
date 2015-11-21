/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import static Backend.State.Delivered;
import static Backend.State.Destination;
import static Backend.State.OnTransit;
import static Backend.State.Origin;
import static Backend.State.OriginError;

/**
 *
 * @author guillermofigueroa
 */
public enum Role {
    Administrator, User, Client;
    
    private String str;
    static {
        Administrator.str = "Administrator";
        User.str = "User";
        Client.str = "Client";
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
