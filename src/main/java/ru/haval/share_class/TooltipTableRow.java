package ru.haval.share_class;

import java.util.function.Function;

import ru.haval.action.hmmr_ap_model;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip; 

public class TooltipTableRow<T> extends TableRow<T> { 

    private Function<T, String> toolTipStringFunction; 

    public TooltipTableRow(Function<T, String> toolTipStringFunction) { 
    	this.toolTipStringFunction = toolTipStringFunction; 
    } 
    
    public TooltipTableRow()
    {
    	
    }

    public void _double_click_row(TableView<hmmr_ap_model> table)
    {
    	table.setRowFactory(tv -> {
            TableRow<hmmr_ap_model> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    hmmr_ap_model rowData = row.getItem();
                    System.out.println("Double click on: "+rowData.getId());
                }
            });
            return row ;
        });
    }
    
	protected void updateItem(T item, boolean empty) { 
	    super.updateItem(item, empty); 
	    if(item == null) { 
	    	setTooltip(null); 
	    } else { 
		     Tooltip tooltip = new Tooltip(toolTipStringFunction.apply(item)); 
		     tooltip.setStyle("-fx-font-size: 14px");
		     setTooltip(tooltip); 
	    } 
    } 
} 