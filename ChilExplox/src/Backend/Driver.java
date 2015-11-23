/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author guillermofigueroa
 */
public class Driver extends iPerson implements java.io.Serializable {

    
    
    String name;
    String username;
    String password;
    boolean available;
    

    
    public Driver(String name, String username, String password){
        this.name = name;
        this.password = password;
        this.username = username;

        this.available = true;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public String getUsername(){
        return this.username;
    }
    
    @Override
    public String getPassword(){
        return this.password;
    }
    
    @Override
    public void setPassword(String password){
        this.password = password;
    }
    
    @Override
    public void setName(String name){
        this.name = name;
    }

    
}
