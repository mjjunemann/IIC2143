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
    private final Messaging messaging;
    
    protected ChilExplox()
    {
        this.messaging = new Messaging();
        this.users = new HashMap();
        this.subsidiaries = new HashMap();
        this.subsidiaries_addrs = new ArrayList<>();
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
        if( this.subsidiaries_addrs.contains(subsidiary_addrs) 
                && users.get(username).getPassword() == password){
            this.current_subsidiary = this.subsidiaries.get(subsidiary_addrs);
            return true;
            }
        return false;
    }
    
    /**
     * Returns a the instance of the class if
     * doesn't exist create one
     * @return ChilExplox instance
     */
    public ChilExplox getInstance()
    {
        if (instance != null)
        {
            instance = new ChilExplox();
        }
        return instance;
    }
    
    public void addSubsidary(Address address)
    {
        Subsidiary s = new Subsidiary(address);
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
}
