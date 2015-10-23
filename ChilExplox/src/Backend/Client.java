/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Set;

/**
 *
 * @author matia
 */
public class Client implements java.io.Serializable {
    
    private String name;
    private String address;
    private String rut;
    private String phone_number;
    
    public Client(String name,String address,String rut,String phone_number)
    {
     this.setName(name);
     this.setAddress(address);
     this.setRut(rut);
     this.setPhone(phone_number);
    }

    public Client() {
    }

    
   // <editor-fold desc="Setters & Getters">
    /**
    * Returns client name
    * @return the client name
    */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Set the name of the client
     * @param name  new name of the client
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the client address
     * @return the address of the client 
     */
    public String getAddress()
    {
        return this.address;
    }
    /**
     * Set de client address
     * @param address new address of the client
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    /**
     * Returns the clients contact number.
     * @return contact number.
     */
    public String getPhone()
    {
        return this.phone_number;
    }
    /**
     * Set the client phone number.
     * @param number contact number of the client.
     */
    public void setPhone(String number)
    {
        this.phone_number = number;   
    }
    /**
     * Returns the national identification number of the client.
     * @return the national identification number.
     */
    public String getRut()
    {
        return this.rut;
    }
    /**
     * Set the national identification number of the client
     * @param rut national identification number of the client
     */
    public void setRut(String rut)
    {
        this.rut = rut;
    }
    //</editor-fold>
}
