/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author matia
 */
public class Loader
{   
    static private final String path ="./data/";
    
   
    
    
    /**
     * Save the state of the subsidiary and all his elements
     * @param subsidiary 
     */
    static public void saveSubsidiary(Subsidiary subsidiary)
    {
        try
        {
            FileOutputStream fileOut =
         new FileOutputStream(String.format(path+"%s",subsidiary.subsidiary_address.getMainStreet()));
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(subsidiary);
        }catch(IOException i)
                {
                    i.printStackTrace();
                }
    }
    
    
    
    /**
     * Receives the address of the subsidiary and load the information
     * @param subsidiary
     * @return 
     */
    static public Subsidiary loadSubsidary(String subsidiary)
    {   Subsidiary tmp = null;
    
        try
        {
            FileInputStream fileIn = new FileInputStream(String.format(path+"%s",subsidiary));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tmp = (Subsidiary)in.readObject();
            return tmp;
            
        }catch(IOException i)
        {
            i.printStackTrace();
            return tmp;
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            return tmp;
        }
    }
    
    static public void  saveApp(ChilExplox app)
    {
        try
        {
            FileOutputStream fileOut =
         new FileOutputStream(String.format(path+"%s","Application"));
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(app);
        }catch(IOException i)
                {
                    i.printStackTrace();
                }
    }
    
    static public ChilExplox loadApp()
    {
        ChilExplox tmp = null;
    
        try
        {
            FileInputStream fileIn = new FileInputStream(String.format(path+"%s","Application"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tmp = (ChilExplox)in.readObject();
            return tmp;
            
        }catch(IOException i)
        {
            i.printStackTrace();
            return tmp;
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            return tmp;
        }
    }
    
    /* load subsidiary from json
    static void loadSubsidiary(String path)
    {
        JSONParser obj = new JSONParser();
        
    }
    */
    
    
}
