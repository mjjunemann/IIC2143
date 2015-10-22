/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Objects;

/**
 *
 * @author matia
 */
public class Address implements java.io.Serializable
{
    private final String city;
    private final String neighborhood;
    private final String street;
    private final Integer number;
    
    public Address(String street, Integer number, String neighborhood, String city)
    {
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }
    
    
    /**
     * Check if two addresses are equal
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Address)
        {
            Address addr = (Address)obj;
            return this.getAddress().toLowerCase().equals(addr.getAddress().toLowerCase());
        }
        // throw new Exception "Not the same class to compare";
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.neighborhood);
        hash = 41 * hash + Objects.hashCode(this.street);
        hash = 41 * hash + Objects.hashCode(this.number);
        return hash;
    }
    
    public String getMainStreet()
    {
        return this.street+this.number;
    }
    public String getAddress()
    {
        return String.format("%1$s %2$s,%3$s,%4$s",street,number,neighborhood,city);
    }
    
            
 }
