/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Cells;

import Backend.Address;
import javafx.scene.control.ListCell;

/**
 *
 * @author matia
 */
public class AddressCell extends ListCell<Address>
{
    @Override
    public void updateItem(Address addr,boolean empty)
    {
        super.updateItem(addr,empty);
        if(!empty && addr != null)
        {
            setText(String.format("%s",addr.stringValue()));
        }
        else
        {
            setText(null);
        }
    }
}
