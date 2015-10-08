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
public class ChilExplox 
{
    private static ChilExplox instance;
    private Subsidiary current_subsidiary;
    private final Messaging messaging;
    
    protected ChilExplox()
    {
        messaging = new Messaging();
        
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
    public void login(String username, String password)
    {
        
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
}
