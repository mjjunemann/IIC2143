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
public class ChilExplox implements java.io.Serializable
{
    private static ChilExplox instance;
    private Map<String,User> users;
    private ArrayList<Address> subsidiaries_addrs;
    private Map<Address,Subsidiary> subsidiaries;
    private Subsidiary current_subsidiary;
    private User current_user;
    public final Messaging messaging;
    int idSubsidiaryCounter = 1000;
    private NotificationCenter center;
    
    public ChilExplox()
    {
        this.messaging = new Messaging();
        this.users = new HashMap();
        this.subsidiaries = new HashMap();
        this.subsidiaries_addrs = new ArrayList<>();
        this.center = new NotificationCenter(this);
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
            System.out.print("Logged-in as: "+ username +" in "+ subsidiary_addrs.getAddress()+" \n");
            return true;
            }
        }
        return false;
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
    public boolean addUser(String username, String name, String password)
    {
        if(!this.users.containsKey(username)){
            User u = new User(username,name,password);
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
    public User getCurrentUser(){
        return this.current_user;
    }
    
    public void startNotifying(iNotificationListener listener){
        this.center.cleanListener();
        this.center.addListener(listener);
    }
    
    public void Exit() 
    {
        System.out.print("Closing Bitches Come Back Tomorrow");
        Loader.saveApp(this);
    }
}
