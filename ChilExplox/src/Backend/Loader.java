/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author matia
 */
public class Loader
{
 
    static private void readJson(String path)
    {
        JSONParser rdr = new JSONParser();
        
        try{
            
        JSONObject obj = (JSONObject)rdr.parse(new FileReader(path));
        }
        catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
    }
    
        
        
        
        
    static void loadSubsidiary(String path)
    {
        JSONParser obj = new JSONParser();
        
    }
    
    
}
