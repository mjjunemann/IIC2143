/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Order;
import Backend.State;
import Backend.iFilter;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;


/**
 *
 * @author matia
 */
public class FilterOrderState implements iFilter<Order,String> {

    State last_param;
    @Override
    public ObservableList Filter(FilteredList<Order> list, String param) {
        State state = State.lookup(param);
        System.out.print(state);
        if (state != null)
        {
            last_param = state;
            list.setPredicate(o->
            {
                return o.getState().equals(state);
            });
        }
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
                return last_param.equals(o.getState());
            });
        }
        return list;
    }

    @Override
    public String toString()
    {
        return "STATE";
    }

    
}
