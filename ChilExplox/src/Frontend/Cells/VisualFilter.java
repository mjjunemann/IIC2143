/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Cells;

import Backend.iFilter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author matia
 */
public class VisualFilter extends ToggleButton{
    
    public iFilter filter;
    private FilteredList list;
    public VisualFilter me;
    private ArrayList<VisualFilter> selected;
    public VisualFilter(iFilter filter,FilteredList list,ArrayList<VisualFilter> selected)
    {
        this.setText(filter.getClass().getSimpleName());
        this.setWidth(120);
        this.setPrefWidth(Double.MAX_VALUE);
        this.setHeight(60);
        this.filter = filter;
        this.list = list;
        this.selected = selected;
        this.me = this;
        this.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                if (me.isSelected())
                {
                    selected.add(me);
                    createDialog();
                    e.consume();
            
                }
                else
                {
                  selected.remove(me);
                  filter.Reset(list);
                  for(VisualFilter v : selected)
                  {
                      v.filter.LastFilter(list);
                  }
                }
            }
        });
    }
    
    
    private void createDialog()
    {
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(filter.getClass().getName());
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText(filter.toString());
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            this.filter.Filter(this.list,result.get());
        else
        {
            this.setSelected(false);
            selected.remove(me);
        }
    }
    
    
}
