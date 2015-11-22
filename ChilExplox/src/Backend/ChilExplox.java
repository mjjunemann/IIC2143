/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.*;

/**
 * @author matia
 */
public class ChilExplox implements java.io.Serializable
{
    private static ChilExplox instance;
    private Map<String,User> users;
    private ArrayList<Address> subsidiaries_addrs;
    private Map<Address,Subsidiary> subsidiaries;
    private Subsidiary current_subsidiary;
    private User current_user;
    private Client current_client;
    public final Messaging messaging;
    int idSubsidiaryCounter = 1000;
    private NotificationCenter center;
    public boolean clientLogged = false;
    
    private iPerson current_logged = null;
    
    public ChilExplox()
    {
        this.messaging = new Messaging();
        this.users = new HashMap();
        this.subsidiaries = new HashMap();
        this.subsidiaries_addrs = new ArrayList<>();
        
        //this.center = new NotificationCenter(this);
        
        
        // Here could be a static class that take cares of loading the information
        //loadSubsidaries
        //loadUsers
        //loadInformation
        //loadTrucks
    }
    /**
     * Validate user information
     *
     * @param username 
     * @param password 
     */
    public boolean login(String username, String password, Address subsidiary_addrs)
    {
        User user = users.get(username);
        if (user != null){

        if( this.subsidiaries_addrs.contains(subsidiary_addrs) 
                && user.getPassword().equals(password)){
            this.current_subsidiary = this.subsidiaries.get(subsidiary_addrs);
            this.current_user = user;
            this.current_logged = user;
            System.out.print("Logged-in as: "+ username +
                    " in "+ subsidiary_addrs.getAddress()+" \n");
            this.clientLogged = false;
            return true;
            }
        }
        return false;
    }
    
    public boolean loginClient(String rut, Address subsidiary_address){
        if (this.subsidiaries_addrs.contains(subsidiary_address)){
            this.current_subsidiary = this.subsidiaries.get(subsidiary_address);
            this.current_client = this.current_subsidiary.getClient(rut);
            if (this.current_client != null){
                this.current_logged = this.current_client;
                System.out.print("Logged-in as: "+ this.current_client.getName() +
                        " in "+ subsidiary_address.getAddress()+" \n");
                this.clientLogged = true;
                return true;
            }
        }
        
        return false;
    }
    
    public void logout(){
        this.current_logged = null;
        this.current_client = null;
        this.current_user = null;
        this.current_subsidiary = null;
    }
    
    /**
     * Returns a the instance of the class if
     * doesn't exist create one
     * @return ChilExplox instance
     */
    public static ChilExplox getInstance()
    {
        if (instance == null)
        {
            instance = Loader.loadApp();
            if (instance == null)
                instance = new ChilExplox();
        }
        return instance;
    }
    
    public void addSubsidary(Address address)
    {
        Subsidiary s = new Subsidiary(address, 
                String.valueOf(idSubsidiaryCounter));
        idSubsidiaryCounter++;
        this.subsidiaries_addrs.add(address);
        this.subsidiaries.put(address,s);
    }
    
    public void addSubsidary(Address address, boolean enabled)
    {
        Subsidiary s = new Subsidiary(address, 
                String.valueOf(idSubsidiaryCounter));
        idSubsidiaryCounter++;
        this.subsidiaries_addrs.add(address);
        this.subsidiaries.put(address,s);
        s.setEnabled(enabled);
    }
    public boolean addUser(String username, String name, String password)
    {
        if(!this.users.containsKey(username)){
            User u = new User(username,name,password);
            this.users.put(username,u);
            return true;
        }
        return false;
    }
    
    public boolean addUser(String username, String name, String password, Role role)
    {
        if(!this.users.containsKey(username)){
            User u = new User(username,name,password, role);
            this.users.put(username,u);
            return true;
        }
        return false;
    }
    
    public ArrayList<Address> getSubsidiariesAddress(){
        return this.subsidiaries_addrs;
    }
    public Subsidiary getSubsidiary(Address address){
        return this.subsidiaries.get(address);
    }
    public Subsidiary getCurrentSubsidiary(){
        return this.current_subsidiary;
    }
    
    public ArrayList<Subsidiary> getAllSubsidiaries(){
        return new ArrayList<Subsidiary>(this.subsidiaries.values());
    }
    
    public ArrayList<Subsidiary> getEnabledSubsidiaries(){
        ArrayList<Subsidiary> enabled_subsidiaries = new ArrayList<Subsidiary>();
        for (Subsidiary subsidiary: this.subsidiaries.values()){
            if (subsidiary.getEnabled()){
                enabled_subsidiaries.add(subsidiary);
            }
        }
        return enabled_subsidiaries;
    }
    
    public User getCurrentUser(){
        return this.current_user;
    }
    
    public Client getCurrentClient(){
        return this.current_client;
    }
    
    public iPerson getCurrentLogged(){
        return this.current_logged;
    }
    
    
    public void startNotifying(iNotificationListener listener){
        this.center.cleanListener();
        this.center.addListener(listener);
    }
    
    public ArrayList<User> getUsers(){
        return new ArrayList<User>(this.users.values());
    }
    
    public void removeUser(User user){
        this.users.remove(user.getUsername());
    }
    
    
    public int administratorCount(){
        int count = 0;
        for (User user: this.users.values()){
            if (user.getRole().equals(Role.Administrator)){
                count++;
            }
        }
        return count;
    }
    
    public void Exit() 
    {
        System.out.print("Closing Bitches Come Back Tomorrow");
        //this.center.stopTimer();
        Loader.saveApp(this);
        
    }
}
