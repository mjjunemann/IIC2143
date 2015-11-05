/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;
import java.util.regex.*;
/**
 *
 * @author matia
 * @author guille
 */
public class InputValidator {
      
    /*
    RUT Validation
    */
    private static final Pattern VALID_RUT_REGEX = 
            Pattern.compile("^([0-9]+-[0-9K])$", 
                    Pattern.CASE_INSENSITIVE);
    
    
    public static boolean CheckRut(String rut) {
	rut = rut.replace(".", "").toUpperCase();

	Matcher matcher = VALID_RUT_REGEX .matcher(rut);

        String dv = rut.substring(rut.length() - 1);
        
        if (!matcher.find()){
            return false;
        }

	String[] rutTemp = rut.split("-");
     
        int digitAux = Integer.parseInt(rutTemp[0]);
	if (!dv.equals(Digito(digitAux))) {
		return false;
	}
	return true;
    }
    
    private static String Digito(int rut) {
	int suma = 0;
	int multiplicador = 1;
	while (rut != 0) {
		multiplicador++;
		if (multiplicador == 8)
		multiplicador = 2;
		suma += (rut % 10) * multiplicador;
		rut = rut / 10;
	}
	suma = 11 - (suma % 11);
	if (suma == 11)	{
		return "0";
	} else if (suma == 10) {
		return "K";
	} else {
		return String.valueOf(suma);
	}
    }

    /*
        Verifies if is a real name
    */
    private static final Pattern VALID_NAME_REGEX = 
            Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$", 
                    Pattern.CASE_INSENSITIVE);
    
    public static boolean CheckName(String email) {
        Matcher matcher = VALID_NAME_REGEX .matcher(email);
        return matcher.find();
    }
    
    /*
    Verifies if is a valid email
    */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
                    Pattern.CASE_INSENSITIVE);
    
    public static boolean CheckEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    
    /*
    Verifies if is a valid phone number
    */
    public static boolean CheckPhone(String number)
    {
        boolean validated = true;
        try {
            if (number.length() == 8){
                int phoneNumber = Integer.parseInt(number);
            }
            else {
                validated = false;
            }
        }
        catch (Exception e){
            validated = false;
        }
        return validated;
    }
    
    public static boolean IsNumber(String number){
        boolean validated = true;
        try {
            int theNumber = Integer.parseInt(number);
        }
        catch (Exception e){
            validated = false;
            
        }
        return validated;
    }
    
    public static boolean IsFloat(String number){
        boolean validated = true;
        try {
            float theNumber = Float.parseFloat(number);
        }
        catch (Exception e){
            validated = false;
        }
        return validated;
    }
    
    
}
