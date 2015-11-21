/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterReset implements iFilter{
    String last_param;

    @Override
    public ObservableList Reset(FilteredList list) {
        list.setPredicate(o-> 
                
        {
            return true;
        });
        return list;
    }

    @Override
    public ObservableList LastFilter(FilteredList list) {
         list.setPredicate(o-> 
                
        {
            return true;
        });
        return list;
    }
    @Override
    public String toString()
    {
        return "ALL";
    }

    @Override
    public ObservableList Filter(FilteredList list, Object param) {
        list.setPredicate(o-> 
                
        {
            return true;
        });
        return list;
    }
}
