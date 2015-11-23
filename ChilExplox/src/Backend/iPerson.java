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
public abstract class iPerson {
    public Role role;

    public Role getRole(){
        return role;
    }
    
    public String getName(){
        return "";
    }
    
    public String getUsername(){
        return "";
    }
    
    public String getPassword(){
        return "";
    }
    
    public void setPassword(String password){}
    
    public void setName(String name){}
    
    public void setRole(Role role){}
    
    
}
