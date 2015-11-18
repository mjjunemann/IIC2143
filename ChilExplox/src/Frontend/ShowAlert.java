/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import javafx.scene.control.Alert;

/**
 *
 * @author guillermofigueroa
 */
public class ShowAlert {
    
    public static void alertWithField(Exception e, String field){
        Alert dlg = new Alert(Alert.AlertType.WARNING);
         dlg.setTitle("Warning");
         dlg.setContentText("Error en " + field + ": " + e.getMessage());
         dlg.showAndWait(); 
    }
    
    public static void alertWithFieldAndMessage(String field, String message)
    {
         Alert dlg = new Alert(Alert.AlertType.WARNING);
         dlg.setTitle("Warning");
                  dlg.setContentText("Error en " + field + ": " + message);
         dlg.showAndWait();   
    }
    
    public static void alert(Exception e)
    {
         Alert dlg = new Alert(Alert.AlertType.WARNING);
         dlg.setTitle("Warning");
         dlg.setContentText(e.toString());
         dlg.showAndWait();   
    }
}
