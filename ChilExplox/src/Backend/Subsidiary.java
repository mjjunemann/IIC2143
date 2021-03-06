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
    private boolean enabled;
    private ArrayList<Record> records;
    
    public Subsidiary(Address addr, String id){
        this.subsidiaryId = id;
        this.subsidiary_address = addr;
        this.mailbox = new Mailbox();
        this.mailbox.setSubsidaryAddress(addr);
        this.orders = new HashMap();
        this.clients = new HashMap();
        this.transport = new HashMap();
        this.arrived = new ArrayList<>(); 
        this.records = new ArrayList<>();
        this.enabled = true;
        //this.notification_center = new NotificationCenter();
    }
    
    public boolean addVehicle(ITransport v){
        if (!transport.containsKey(v.getPlate())){
            transport.put(v.getPlate(),v);
            return true;
        }
        return false;
        
    }
    
    public boolean getEnabled(){
        return this.enabled;
    }
    
    @Override
    public String toString(){
        return this.getAddress();
    }
    
    public void setEnabled(boolean bool){
        this.enabled = bool;
    }
    
    public Map<String,ITransport> getVehicles(){
        return this.transport;
    }
    public ArrayList<ITransport> getArrivedVehicles(){
        return this.arrived;
    }
    
    public ArrayList<ITransport> getTrucks(){
        return new ArrayList<ITransport>(this.transport.values());
    }
    
    public String getAddress(){
        return subsidiary_address.getAddress();
    }
    
    public Mailbox getMailbox(){
        return this.mailbox;
    }
    public boolean deleteOrder(Order o)
    {
        if (getOrders().containsKey(o.getId()))
        {
            getOrders().remove(o.getId());
            return true;
        }
                    
        return false;
    }
    
    public void removeTruck(Truck truck){
        this.transport.remove(truck.getPlate());
    }
    
    public Order newOrder(User current_user){
        String id = subsidiaryId + String.valueOf(orderIdCounter);
        orderIdCounter++;
        Date date = new Date();
        Order o = new Order(date,id,current_user);
        return o;
    }
    
    public float setOrder(Order order, Client client){
        order.setClient(client);
        String rut = order.getClient().getRut();
        Date saleDate = order.getSaleDate();
        String date = String.valueOf(saleDate.getTime());
        this.addClient(client);
        this.orders.put(order.getId(), order);
        for (Parcel p: order.getParcels()) {
            Record r = new Record(ArchiveType.Sale,"Parcel "+p.getId()+", amount:"
                    + String.valueOf(BudgetCalculator.calculateParcel(p)),
            order.getResponsable(),p);
            this.addRecord(r);
        }
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
    public void addRecord(Record r){
        this.records.add(r);
    }
    
    public ArrayList<Record> getDaySaleRecords(){
        ArrayList<Record> rec = new ArrayList<>();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(date);
        for (Record r: this.records) {
            cal2.setTime(r.getDate());
            if ( (cal.get(Calendar.DAY_OF_YEAR)==  cal2.get(Calendar.DAY_OF_YEAR))
             && (cal.get(Calendar.YEAR)==  cal2.get(Calendar.YEAR) )) {
                rec.add(r);
            }
        }
        return rec;
    }
    public ArrayList<Record> getWeekSaleRecords(){
        ArrayList<Record> rec = new ArrayList<>();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(date);
        for (Record r: this.records) {
            cal2.setTime(r.getDate());
            if ( (cal.get(Calendar.WEEK_OF_YEAR)==  cal2.get(Calendar.WEEK_OF_YEAR))
             && (cal.get(Calendar.YEAR)==  cal2.get(Calendar.YEAR) )) {
                rec.add(r);
            }
        }
        return rec;
    }
    public ArrayList<Record> getMonthSaleRecords(){
        ArrayList<Record> rec = new ArrayList<>();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(date);
        for (Record r: this.records) {
            cal2.setTime(r.getDate());
            if ( (cal.get(Calendar.MONTH)==  cal2.get(Calendar.MONTH))
             && (cal.get(Calendar.YEAR)==  cal2.get(Calendar.YEAR) )) {
                rec.add(r);
            }
        }
        return rec;
    }
}
