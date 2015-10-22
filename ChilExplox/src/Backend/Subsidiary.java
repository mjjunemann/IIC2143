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
    private ArrayList<ITransport> arrived;
    private NotificationCenter notification_center;
    
    public Subsidiary(Address addr){
        this.subsidiary_address = addr;
        this.mailbox = new Mailbox();
        this.orders = new HashMap();
        this.transport = new HashMap();
        this.arrived = new ArrayList<>(); 
        this.notification_center = new NotificationCenter();
    }
    
    public void addVehicle(ITransport v){
        transport.put(v.getPlate(),v);
    }
    
    public Map<String,ITransport> getVehicles(){
        return this.transport;
    }
    
    public String getAddress(){
        return subsidiary_address.getAddress();
    }
    
    public Mailbox getMailbox(){
        return this.mailbox;
    }
    
    public Order newOrder(){
        Order o = new Order();
        return o;
    }
    
    public float setOrder(Order order, Client client){
        order.setClient(client);
        order.setDate();
        String rut = order.getClient().getRut();
        Date saleDate = order.getSaleDate();
        String date = String.valueOf(saleDate.getTime());
        
        String id = rut + "-" + date;
        
        this.orders.put(id, order);
        this.notification_center.addOrderNotification(id,order);
        return order.getTotal();
    }
    public void editParcel(Parcel parcel,Address origin,Address destination){
        parcel.setDestination(destination);
        parcel.setOrigin(origin);
    }
    
    public void sendsVehicle(ITransport v, Subsidiary S){
        v.send(S.subsidiary_address);
        S.receivesVehicle(v);
    }
    
    public void receivesVehicle(ITransport v){
        this.arrived.add(v);
    }
    
    public void receivesParcels(ITransport from){
        from.unload();
    }
    
    public void sendBack(ITransport v){
        this.arrived.remove(v);
        v.sendBack();
    }
    
    public Address getAddr()
    {
        return this.subsidiary_address;
    }
}
