/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Parcel;
import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterParcelID implements iFilter<Parcel,String>{

    String last_param;
    @Override
    public ObservableList Filter(FilteredList<Parcel> list, String param) {
        last_param = param;
        list.setPredicate(o->
        {
            return param.equals(o.getId());
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
        list.setPredicate(p-> 
                
        {
            return last_param.equals(p.getId());
        });
        }
        return list;
    }
    @Override
    public String toString()
    {
        return "ID";
    }


}
