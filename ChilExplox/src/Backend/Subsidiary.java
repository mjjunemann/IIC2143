/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author matia
 */
public class Subsidiary 
{
    Address subsidiary_address;
    Map<String,Order> orders;
    
    public Subsidiary(Address addr)
    {
        //this.orders = new Map<String,Order>();
        User a = new User("mjjunemann","Matias Jünemann","papel123");
        this.subsidiary_address = addr;
    }
}
