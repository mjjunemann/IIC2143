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
    
    
    public static boolean CheckRut(String rut) throws Exception {
	rut = rut.replace(".", "").toUpperCase();

	Matcher matcher = VALID_RUT_REGEX .matcher(rut);

        String dv = rut.substring(rut.length() - 1);
        
        if (!matcher.find()){
            String messageError = "Debe ingresar un Rut con caracteres válidos";
            throw new Exception(messageError);
        }

	String[] rutTemp = rut.split("-");
     
        int digitAux = Integer.parseInt(rutTemp[0]);
	if (!dv.equals(Digito(digitAux))) {
            String messageError = "Debe ingresar un Rut válido";
            throw new Exception(messageError);
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
    
    public static boolean CheckName(String email) throws Exception {
        Matcher matcher = VALID_NAME_REGEX .matcher(email);
        if (matcher.find()){
            return true;
        }
        else{
            throw new Exception
                ("Debe ingresar caracteres validos [a-zA-Z]");
        }
    }
    
    private static final Pattern VALID_NORMAL_STRING_REGEX = 
            Pattern.compile("^[A-Z0-9._%+-]+$", 
                    Pattern.CASE_INSENSITIVE);
    
    public static boolean CheckNormalString(String text) throws Exception{
        Matcher matcher = VALID_NORMAL_STRING_REGEX .matcher(text);
        if (matcher.find()){
            return true;
        }
        else{
            throw new Exception
                ("Debe ingresar un texto valido");
        }
    }
    
    /*
    Verifies if is a valid email
    */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
                    Pattern.CASE_INSENSITIVE);
    
    public static boolean CheckEmail(String email) throws Exception {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if(!matcher.find()){
            throw new Exception("Debe ingresar un mail válido");
        }
        return true;
    }
    
    /*
    Verifies if is a valid phone number
    */
    public static boolean CheckPhone(String number) throws Exception
    {
        boolean validated = true;
        try {
            if (number.length() == 8){
                int phoneNumber = Integer.parseInt(number);
            }
            else {
                throw new Exception("El largo del número es incorrecto");
            }
        }
        catch (Exception e){
            throw new Exception("Debe ingresar un número");
        }
        return validated;
    }
    
    public static boolean IsNumber(String number) throws Exception{
        boolean validated = true;
        try {
            int theNumber = Integer.parseInt(number);
        }
        catch (Exception e){
            throw new Exception("Debe ingresar un número");
            
        }
        return validated;
    }
    
    public static boolean IsFloat(String number) throws Exception{
        boolean validated = true;
        try {
            float theNumber = Float.parseFloat(number);
        }
        catch (Exception e){
            throw new Exception("Debe ingresar un número");
        }
        return validated;
    }
    
    
}
