/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;


import Backend.Parcel;
import Backend.State;
import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterParcelState implements iFilter<Parcel,String> {

    State last_param;
    @Override
    public ObservableList Filter(FilteredList<Parcel> list, String param) {
        State state = State.lookup(param);
        if (state != null)
        {
            last_param = state;
            list.setPredicate(o->
            {
                return state.equals(o.getState());
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
                return last_param.equals(o.getState());
            });
        }
        return list;
    }
    
}
