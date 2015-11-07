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
    private Map<String,Client> clients;
    private NotificationCenter notification_center;
    private String subsidiaryId;
    private int orderIdCounter = 1000;
    
    public Subsidiary(Address addr, String id){
        this.subsidiaryId = id;
        this.subsidiary_address = addr;
        this.mailbox = new Mailbox();
        this.mailbox.setSubsidaryAddress(addr);
        this.orders = new HashMap();
        this.clients = new HashMap();
        this.transport = new HashMap();
        this.arrived = new ArrayList<>(); 
        //this.notification_center = new NotificationCenter();
    }
    
    public void addVehicle(ITransport v){
        transport.put(v.getPlate(),v);
    }
    
    public Map<String,ITransport> getVehicles(){
        return this.transport;
    }
    public ArrayList<ITransport> getArrivedVehicles(){
        return this.arrived;
    }
    
    public String getAddress(){
        return subsidiary_address.getAddress();
    }
    
    public Mailbox getMailbox(){
        return this.mailbox;
    }
    
    public Order newOrder(){
        String id = subsidiaryId + String.valueOf(orderIdCounter);
        orderIdCounter++;
        Date date = new Date();
        Order o = new Order(date,id);
        return o;
    }
    
    public float setOrder(Order order, Client client){
        order.setClient(client);
        String rut = order.getClient().getRut();
        Date saleDate = order.getSaleDate();
        String date = String.valueOf(saleDate.getTime());
        this.addClient(client);
        this.orders.put(order.getId(), order);
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
        from.unloadAll();
    }
    
    public void sendBack(ITransport v){
        this.arrived.remove(v);
        v.sendBack();
    }
    public void sendBackError(ITransport v){
        this.arrived.remove(v);
        v.sendBackError();
    }
    
    public Map<String,Order> getOrders(){
        return this.orders;
    }
    
    public Address getAddr()
    {
        return this.subsidiary_address;
    }
    
    public String getId(){
         return this.subsidiaryId;
     }
    
    public Map<String,Client> getClients()
    {
        return this.clients;
    }
    public Client getClient(String rut)
    {
        return this.clients.get(rut);
    }

    public boolean addClient(Client c)
    {
        if (!this.clients.containsKey(c.getRut()))
        {     
            this.clients.put(c.getRut(),c);
            return true;
        }
        return false;
    }
}
