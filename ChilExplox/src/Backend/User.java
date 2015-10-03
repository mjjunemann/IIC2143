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
public class User 
{
    public final String username;
    private String password;
    protected String name;
    
    public User(String username,String name,String password)
    {
        this.username = username;
        this.name = name;
        this.setPassword(password);
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
    /**
     * Changes the user password
     * @param password receives the user new password
     */
    protected final void setPassword(String password)
    {
        this.password = password;
    }
    
    //</editor-fold>

}