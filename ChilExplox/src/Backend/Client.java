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
import java.util.Date;
import java.util.Set;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 *
 * @author matia
 */
public class Client extends iPerson implements java.io.Serializable {
    
    private transient StringProperty name;
    private transient StringProperty lastname;
    private transient StringProperty address;
    private transient StringProperty rut;
    private transient StringProperty phone_number;
    private transient StringProperty email;
    Role role;
    
    public Client(String name,String address,String rut,String phone_number)
    {
     this.setName(name);
     this.setAddress(address);
     this.setRut(rut);
     this.setPhone(phone_number);
     this.role = Role.Client;
    }

    public Client() {
        this.role = Role.Client;
    }

    
   // <editor-fold desc="Setters & Getters">
    /**
    * Returns client name
    * @return the client name
    */
    public String getName()
    {
        return nameProperty().get();
    }
    
    /**
     * Set the name of the client
     * @param name  new name of the client
     */
    public void setName(String name)
    {
        nameProperty().set(name);
    }
    
    /**
     * Returns the client address
     * @return the address of the client 
     */
    public String getAddress()
    {
        return addressProperty().get();
    }
    /**
     * Set de client address
     * @param address new address of the client
     */
    public void setAddress(String address)
    {
        addressProperty().set(address);
    }
    
    /**
     * Returns the clients contact number.
     * @return contact number.
     */
    public String getPhone()
    {
        return numberProperty().get();
    }
    /**
     * Set the client phone number.
     * @param number contact number of the client.
     */
    public void setPhone(String number)
    {
        numberProperty().set(number);   
    }
    /**
     * Returns the national identification number of the client.
     * @return the national identification number.
     */
    public String getRut()
    {
        return rutProperty().get();
    }
    /**
     * Set the national identification number of the client
     * @param rut national identification number of the client
     */
    public void setRut(String rut)
    {
        rutProperty().set(rut);
    }
    
    public String getEmail()
    {
        return emailProperty().get();
    }
    public void setEmail(String email)
    {
        emailProperty().set(email);
    }
    
    @Override
    public Role getRole(){
        return this.role;
    }
    
    public String getLastname()
    {
        return lastnameProperty().get();
    }
    public void setLastname(String lastname)
    {
        lastnameProperty().set(lastname);
    }
    //</editor-fold>
    
    
    
    //<editor-fold desc="Properties">
    public final StringProperty nameProperty()
    {
        if (name == null)
        {
            name = new SimpleStringProperty();
        }
        return name;
    }
    
    public final StringProperty rutProperty()
    {
        if (rut == null)
        {
            rut = new SimpleStringProperty();
        }
        return rut;
    }
    public final StringProperty numberProperty()
    {
        if (phone_number == null)
        {
            phone_number = new SimpleStringProperty();
        }
        return phone_number;
    }
    public final StringProperty addressProperty()
    {
        if (address == null)
        {
            address = new SimpleStringProperty();
        }
        return address;
    }
    public final StringProperty lastnameProperty()
    {
        if (lastname == null)
        {
            lastname = new SimpleStringProperty();
        }
        return lastname;
    }
    public final StringProperty emailProperty()
    {
        if (email == null)
        {
            email = new SimpleStringProperty();
        }
        return email;
    }
    //</editor-fold>
    
    public static Callback<Client,Observable[]> extractor()
    {
        return (Client c ) -> new Observable[]{c.nameProperty(),c.numberProperty(),
            c.rutProperty(),c.addressProperty(),c.lastnameProperty(),c.emailProperty()};
    }
    
    
    private void writeObject(ObjectOutputStream oos)
    throws IOException
    {
      oos.defaultWriteObject();
      oos.writeObject(this.getName());
      oos.writeObject(this.getLastname());
      oos.writeObject(this.getPhone());
      oos.writeObject(this.getRole());
      oos.writeObject(this.getEmail());
      oos.writeObject(this.getAddress());
      oos.writeObject(this.getRut());
           
      
    }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException,IOException
    {
        
        // deliveryDate
        // parcels cambiar 
        ois.defaultReadObject();
        this.setName((String)ois.readObject());
        this.setLastname((String)ois.readObject());
        this.setPhone((String) ois.readObject());
        this.setRole((Role) ois.readObject());
        this.setEmail((String)ois.readObject());
        this.setAddress((String)ois.readObject());
        this.setRut((String)ois.readObject());
    }

}
