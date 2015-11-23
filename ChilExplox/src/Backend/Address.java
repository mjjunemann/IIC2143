/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 *
 * @author matia
 */
public class Address implements java.io.Serializable
{
    private transient StringProperty city;
    private transient StringProperty neighborhood;
    private transient StringProperty street;
    private transient IntegerProperty number;
    
    public Address(String street, Integer number, String neighborhood, String city)
    {
        setCity(city);
        setNeighborhood(neighborhood);
        setStreet(street);
        setNumber(number);
    }
    
    public Address()
    {
        setCity("");
        setNeighborhood("");
        setStreet("");
        setNumber(0);
    }
    public final IntegerProperty numberProperty()
    {
        if (number == null)
        {
            number = new SimpleIntegerProperty();
        }
        return number;
    }
    public final void setNumber(int number)
    {
        numberProperty().set(number);
    }
    
    public final int getNumber()
    {
        return numberProperty().get();
    }
    public final StringProperty streetProperty()
    {
        if (street == null)
        {
            street = new SimpleStringProperty();
        }
        return street;
    }
    
    public final void setStreet(String street)
    {
        streetProperty().set(street);
    }
    
    public final String getStreet()
    {
        return streetProperty().get();
    }
    
    public final StringProperty neighborhoodProperty()
    {
        if(neighborhood == null)
        {
            neighborhood = new SimpleStringProperty();
        }
        return neighborhood;
    }
    
    public final void setNeighborhood(String neighborhood)
    {
        neighborhoodProperty().set(neighborhood);
    }
    
    public final String getNeighborhood()
    {
        return neighborhoodProperty().get();
    }
    
    public final StringProperty cityProperty()
    {
        if(city == null)
        {
            city = new SimpleStringProperty();
        }
        return city;
    }
    
    public final void setCity(String city)
    {
        cityProperty().set(city);
    }
    
    public final String getCity()
    {
        return cityProperty().get();
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
        return getStreet()+getNumber();
    }
    public String getAddress()
    {
        return String.format("%1$s %2$s, %3$s, %4$s",getStreet(),getNumber(),getNeighborhood(),getCity());
    }
    public String stringValue()
    {
        return String.format("%1$s %2$s, %3$s, %4$s",getStreet(),getNumber(),getNeighborhood(),getCity());
    }
    
    @Override
    public String toString()
    {
        return getMainStreet();
    }
    
    public static Callback<Address,Observable[]> extractor()
    {
        return (Address addr) -> new Observable[]{addr.cityProperty(),addr.neighborhoodProperty(),addr.numberProperty(),addr.streetProperty()};
    }

    
    private void writeObject(ObjectOutputStream oos)
    throws IOException
    {
      oos.defaultWriteObject();
      List params = new ArrayList<>();
      params.add(getStreet());
      params.add(getNumber());
      params.add(getNeighborhood());
      params.add(getCity());
      oos.writeObject(params);
      }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException,IOException
    {
        ois.defaultReadObject();
        List params = (List)ois.readObject();
        this.setStreet((String) params.get(0));
        this.setNumber((int) params.get(1));
        this.setNeighborhood((String) params.get(2));
        this.setCity((String) params.get(3));
     
    }
}
