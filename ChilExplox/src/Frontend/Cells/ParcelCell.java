/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Cells;

import Backend.Address;
import Backend.Parcel;
import javafx.scene.control.ListCell;

/**
 *
 * @author matia
 */
public class ParcelCell extends ListCell<Parcel>
{
    @Override
    public void updateItem(Parcel parcel,boolean empty)
    {
        super.updateItem(parcel,empty);
        if(!empty && parcel != null)
        {
            setText(String.format("Parcel - %s",parcel.getId()));
        }
        else
        {
            setText(null);
        }
    }
}
