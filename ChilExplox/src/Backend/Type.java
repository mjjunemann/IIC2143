/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author matia
 */
public enum Type {
    Normal,Radioactive,Fragile,Refrigerated;
    
    private String str;
    static {
        Normal.str = "Normal";
        Radioactive.str = "Radioactive";
        Fragile.str = "Fragile";
        Refrigerated.str = "Refrigerated";
    }
    public String toString(){
        return str;
    }
    
    public static Type lookup(String param)
    {
        for(Type type :Type.values())
        {
            if (type.str.toLowerCase().equals(param.toLowerCase()))
            {
                return type;
            }
        }
        return null;
    }

}

