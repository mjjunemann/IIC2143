/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Parcel;
import Backend.Type;
import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterParcelType implements iFilter<Parcel,String>{
    Type last_param;
    @Override
    public ObservableList Filter(FilteredList<Parcel> list, String param) {
        Type state = Type.lookup(param);
        System.out.print(state);
        if (state != null)
        {
            last_param = state;
            list.setPredicate(o->
            {
                return o.getType().equals(state);
            });
        }
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
        if (last_param != null)
        {
            list.setPredicate(o->
            {
                return last_param.equals(o.getType());
            });
        }
        return list;
    }

    @Override
    public String toString()
    {
        return "TYPE";
    }
}
