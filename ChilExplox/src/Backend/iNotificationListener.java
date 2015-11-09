/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.EventListener;
import javafx.event.Event;

/**
 *
 * @author guillermofigueroa
 */
public interface iNotificationListener extends EventListener {
    void showNotification(Parcel parcel);
}
