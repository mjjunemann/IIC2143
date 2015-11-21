/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author matia
 */
public class User extends iPerson implements java.io.Serializable
{
    public final String username;
    private String password;
    protected String name;
    Role role;
    
    public User(String username,String name,String password)
    {
        this.username = username;
        this.name = name;
        this.setPassword(password);
        this.role = Role.User;
    }
    
    public User(String username,String name,String password, Role role)
    {
        this.username = username;
        this.name = name;
        this.setPassword(password);
        this.role = role;
    }

    //<editor-fold desc="Setter & Getters">
    /**
     * Get the users password
     * @return password
     */
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
    protected final void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * This method set the role of this user
     * @param user : this param is a permission needed to change the param role
     * @param user
     * @param role 
     */
    public void setRole(User user, Role role){
        if (user.role.equals(Role.Administrator)){
            this.role = role;
        }
    }
    
    @Override
    public Role getRole(){
        return this.role;
    }
    
    /**
     * This method takes a user and changes the role of the user
     * @param user
     * @param role 
     */
    public void changeRole(User user, Role role){
        user.setRole(this, role);
    }
    
    //</editor-fold>

}