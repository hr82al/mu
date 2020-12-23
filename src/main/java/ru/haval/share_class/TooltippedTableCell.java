package ru.haval.share_class;

import javafx.beans.value.ObservableValue;
import ru.haval.action.hmmr_pm_model;
import  ru.haval.db._query;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

/**
 * Just like a normal table cell, but each table cell has a tooltip that will display its contents. This makes
 * it easier for the user: they can read the contents without having to expand the table cell.
 * <p>
 * Look it's easy:
 * <code>
 * someColumn.setCellFactory(TooltippedTableCell.forTableColumn());
 * </code>
 */
public class TooltippedTableCell<S, T> extends TableCell<S, T> {
	_query qr = new _query();
	public static int flag_ui = 1;
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
        return forTableColumn(new DefaultStringConverter());
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(final StringConverter<T> converter) {
        return list -> new TooltippedTableCell<>(converter);
    }

    private static <T> String getItemText(Cell<T> cell, StringConverter<T> converter) {
        return converter == null ? cell.getItem() == null ? "" : cell.getItem()
                .toString() : converter.toString(cell.getItem());
    }

    public static Callback<TableColumn.CellDataFeatures<hmmr_pm_model, String>, ObservableValue<String>> showNextDate() {
        return null;
    }

    private void updateItem(final Cell<T> cell, final StringConverter<T> converter) {

        if (cell.isEmpty()) {
            cell.setText(null);
            cell.setTooltip(null);

        } else {
            cell.setText(getItemText(cell, converter));

            //Add text as tooltip so that user can read text without editing it.
            Tooltip tooltip = new Tooltip(getItemText(cell, converter));
            tooltip.setWrapText(true);
            tooltip.setStyle("-fx-font-size: 14px");
            tooltip.prefWidthProperty().bind(cell.widthProperty());
            cell.setTooltip(tooltip);
          
        }
    }

    private ObjectProperty<StringConverter<T>> converter = new SimpleObjectProperty<>(this, "converter");

    /**
     * The easiest way to get this working is to call this class's static forTableColumn() method:
     * <code>
     * someColumn.setCellFactory(TooltippedTableCell.forTableColumn());
     * </code>
     */
    public TooltippedTableCell() {
        this(null);
    }

    public TooltippedTableCell(StringConverter<T> converter) {
        this.getStyleClass().add("tooltipped-table-cell");
        setConverter(converter);
    }

    public final ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }

    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }

    //Вызываем подсказку для формы таблицы в справочнике Группа-Период
    private void updateItem_gc(final Cell<T> cell_gc, final StringConverter<T> converter_gc) {

        if (cell_gc.isEmpty()) {
            cell_gc.setText(null);
            cell_gc.setTooltip(null);

        } else {
            cell_gc.setText(getItemText(cell_gc, converter_gc));

            Tooltip tooltip = new Tooltip(qr._select_recArrStr("hmmr_pm", "Instruction_num", "del_rec", "PM_Group", cell_gc.getText()).toString());
           // tooltip.setWrapText(true);
            tooltip.setMinWidth(700.0);
            //tooltip.setMinHeight(300.0);
            tooltip.setStyle("-fx-font-size: 14px");
            tooltip.prefWidthProperty().bind(cell_gc.widthProperty());
            tooltip.setWrapText(true);
            cell_gc.setTooltip(tooltip);
          
        }
    }


    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if(flag_ui == 1)
        	updateItem(this, getConverter());
        else
        	updateItem_gc(this, getConverter());
    }
}
