/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
//import static org.junit.Assert.*;

import Backend.Address;
import Backend.User;

/**
 *
 * @author matia
 */
public class Test 
{

   
    public void compareAddress()
    {
        /*Aqui debe ir un assert para checkear si falla o no*/
        Address a = new Address("Amapolas",1500,"Providencia","Santiago");
        Address b = new Address("amapolas",1500, "providencia","santiago");
        Address c = new Address("Hernando Aguirre",1133,"Providencia", "Santiago");
        
        boolean d =  a.equals(b);   
    }
    
    public void Users()
    {
        User a = new User("mjjunemann","Matias Jünemann","papel123");
        User b = new User("gafigueroa","Guillermo Figueroa","cacapipimoco");
    }
}
