/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Address;
import Backend.Parcel;
import Backend.iFilter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public class FilterParcelAddress implements iFilter<Parcel,Address>{

    @Override
    public ObservableList Filter(FilteredList<Parcel> list, Address param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList LastFilter(FilteredList<Parcel> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList Reset(FilteredList<Parcel> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
