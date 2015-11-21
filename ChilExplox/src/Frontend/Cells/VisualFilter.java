/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Cells;

import Backend.Filter.FilterReset;
import Backend.iFilter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.BreadCrumbBar;
import org.controlsfx.control.ToggleSwitch;
import org.controlsfx.control.textfield.TextFields;
/**
 *
 * @author matia
 */
public class VisualFilter extends ToggleSwitch{
    
    public final iFilter filter;
    private final FilteredList list;
    public VisualFilter me;
    private ArrayList<VisualFilter> selected;
    private BreadCrumbBar bar;
    private TreeItem crumb;
    public VisualFilter(iFilter filter,FilteredList list,ArrayList<VisualFilter> selected,BreadCrumbBar bar)
    {
        super(filter.getClass().getSimpleName());
        
        this.bar = bar;
        this.setPrefWidth(Double.MAX_VALUE);
        this.filter = filter;
        this.list = list;
        this.selected = selected;
        this.me = this; 
        this.crumb = new TreeItem<VisualFilter>(this.me)
        {
            @Override
            public String toString()
            {
                return this.getValue().toString();
            }
        };
        
        this.setOnMouseReleased(event ->
        {
            fire();
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
            RemoveCrumb();
        }
    }
    
    @Override
    public String toString()
    {
        return filter.toString();
    }
    
    @Override
    public void fire()
    {
        if (!isDisabled())
        {
            if (isSelected())
            {
               selected.add(me);
               addCrumb();
               createDialog();
            }
            else
            {
                selected.remove(me);
                filterSelected();
            }
            
            //setSelected(!isSelected());
        }
    }
    
    public void filterSelected()
    {
        filter.Reset(list);
        for(VisualFilter v: selected)
        {
            v.filter.LastFilter(list);
        }
    }
    
    public void filterSelectedCrumb(TreeItem<VisualFilter> item)
    {
        filter.Reset(list);
        while (item != null)
        {
            item.getValue().filter.LastFilter(list);
            item = (item.getParent().getValue().filter.getClass().equals(FilterReset.class))?
                    null:item.getParent();
        }
    }
    
    private void addCrumb()
    {   
        this.bar.getSelectedCrumb().getChildren().add(crumb);
        this.bar.setSelectedCrumb(crumb);
        
    }
    public void ResetFilter()
    {
        filter.Reset(list);
    }
    private void RemoveCrumb()
    {
        TreeItem tmp = this.bar.getSelectedCrumb().getParent();
        this.bar.setSelectedCrumb(tmp);
    }
    
    protected void resetToDeactivate()
    {
        this.selectedProperty().set(false);    
    }
}   
