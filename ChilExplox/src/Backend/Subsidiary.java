/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.*;

/**
 *
 * @author matia
 */
public class Subsidiary implements java.io.Serializable
{
    public Address subsidiary_address;
    private Map<String,Order> orders;
    private Mailbox mailbox;
    private Map<String,ITransport> transport;
    
    public Subsidiary(Address addr)
    {
        this.subsidiary_address = addr;
        this.mailbox = new Mailbox();
        this.orders = new HashMap();
    }
    
    public String getAddress()
    {
        return subsidiary_address.getAddress();
    }
    public Mailbox getMailbox(){
        return this.mailbox;
    }
    
    public Order newOrder(Client client){
        
        Date da = new Date();
        long d = da.getTime();
        
        Order o = new Order(client, da);
        orders.put(String.valueOf(d), o);
        return o;
    }
    
    public void sendOrder(Order order){
        String rut = order.getClient().getRut();
        Date saleDate = order.getSaleDate();
        String date = String.valueOf(saleDate.getTime());
        
        String id = rut + "-" + date;
        
        this.orders.put(id, order);
    }

}
