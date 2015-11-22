/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author matia
 */
public class User extends iPerson implements java.io.Serializable
{
    public final String username;
    private String password;
    protected String name;
    private ArrayList<Record> records;
    Role role;
    
    public User(String username,String name,String password)
    {
        this.username = username;
        this.name = name;
        this.setPassword(password);
        this.role = Role.User;
        this.records = new ArrayList<>();
    }
    
    public User(String username,String name,String password, Role role)
    {
        this.username = username;
        this.name = name;
        this.setPassword(password);
        this.role = role;
        this.records = new ArrayList<>();
    }

    //<editor-fold desc="Setter & Getters">
    /**
     * Get the users password
     * @return password
     */
    @Override
    public String getPassword()
    {
        return this.password;
    }
    
    public String getName(){
        return this.name;
    }
    
    /**
     * Changes the user password
     * @param password receives the user new password
     */
    @Override
    public final void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * This method set the role of this user
     * @param user : this param is a permission needed to change the param role
     * @param user
     * @param role 
     */
    
    @Override
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public void setRole(Role role){
        this.role = role;
    }
    
    @Override
    public Role getRole(){
        return this.role;
    }
    
    @Override
    public String getUsername(){
        return this.username;
    }
    
    public ArrayList<Record> getRecords(){
        return this.records;
    }
    
    public void addRecord(Record r){
        if (this.records == null) {
            this.records = new ArrayList<>();
        }
       this.records.add(r);
    }

    
    //</editor-fold>

}