/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author matia
 */
public interface iFilter<T,E> {
    
    public ObservableList Filter(FilteredList<T> list,E param);
    public ObservableList LastFilter(FilteredList<T> list);
    public ObservableList Reset(FilteredList<T> list);
}
