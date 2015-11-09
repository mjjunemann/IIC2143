/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Filter;

import Backend.Order;
import Backend.iFilter;
import java.util.regex.Matcher;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.regex.Pattern;

/**
 *
 * @author matia
 */
public class FilterOrderTotal implements iFilter<Order,String>{
    String pattern = "(([<=>]|<=|>=)\\s?(\\d+)(?![.])\\b)";
    Pattern regex = Pattern.compile(pattern);
    String last_param;
    Matcher m;
    @Override   
    public ObservableList Filter(FilteredList<Order> list, String param) {
            
            m = regex.matcher(param);
            if (m.matches())
            {
                Float amount = Float.parseFloat(m.group(3));
                list.setPredicate( o->
                {
                    switch(m.group(2))
                    {
                        case ">":
                            return o.getTotalValue() > amount;
                        case "<":
                            return o.getTotalValue() < amount;
                        case "=":
                            return o.getTotalValue() == amount;
                        case "<=":
                            return o.getTotalValue() <= amount;
                        case ">=":
                            return o.getTotalValue() >= amount;
                        default:
                            return true;
                    }
                });
            }
            return list;
    }

    @Override
    public ObservableList LastFilter(FilteredList<Order> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList Reset(FilteredList<Order> list) {
            list.setPredicate(o->
            {
                return true;
            });
            return list;
    }
    
}
