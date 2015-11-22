/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Address;
import Backend.Parcel;
import Backend.iFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterParcelAddress implements iFilter<Parcel,String>{

    private Pattern stringCmp;
            
    Pattern last_param;
    Matcher m;
    @Override
    public ObservableList Filter(FilteredList<Parcel> list, String param) {
        last_param = Pattern.compile(param, 
                    Pattern.CASE_INSENSITIVE);
        list.setPredicate(o->
        {
            m = last_param.matcher(o.getDestination().toString());
            return m.find();
        });
        return list;
    }

    @Override
    public ObservableList Reset(FilteredList<Parcel> list) {
        list.setPredicate(o-> 
                
        {
            return true;
        });
        return list;
    }

    @Override
    public ObservableList LastFilter(FilteredList<Parcel> list) {
        if (last_param != null){
        list.setPredicate(o-> 
                
        {
            m = last_param.matcher(o.getDestination().toString());
            return m.find();
        });
        }
        return list;
    }
    @Override
    public String toString()
    {
        return "DESTINATION";
    }
    
}
