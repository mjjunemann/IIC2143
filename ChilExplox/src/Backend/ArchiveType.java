/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Fernando
 */

public enum ArchiveType {
    Sale,Error, Delivery;
    
    private String str;
    static {
        Sale.str = "Sle";
        Error.str = "Error";
        Delivery.str = "Delivery";
    }
    public String toString(){
        return str;
    }
}
