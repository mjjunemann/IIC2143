/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Cells;

import Backend.Address;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

/**
 *
 * @author matia
 */
public class AddressCellTable<S,T> extends TableCell<S,Address> 
{

    @Override
    public void updateItem(Address addr,boolean empty)
    {
        super.updateItem(addr, empty);
        if (empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            setText(addr.getMainStreet());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }
    
}
