/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Order;
import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterRut implements iFilter<Order,String> {

    String last_param;
    @Override
    public ObservableList Filter(FilteredList<Order> list, String param) {
        last_param = param;
        list.setPredicate(o->
        {
            return param.equals(o.getClient().getRut());
        });
        return list;
    }

    @Override
    public ObservableList Reset(FilteredList<Order> list) {
        list.setPredicate(o->
        {
            return true;
        });
        return list;    
    }
    @Override
    public ObservableList LastFilter(FilteredList<Order> list) {
        if (last_param != null)
        {
        list.setPredicate(o-> 
                
        {
            return last_param.equals(o.getClient().getRut());
        });
        }
        return list;
    }
    @Override
    public String toString()
    {
        return "RUT";
    }

}
