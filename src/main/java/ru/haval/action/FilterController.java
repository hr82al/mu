package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import ru.haval.db._query;
import ru.haval.filter.IFilter;

public class FilterController {

    private IFilter filter;

    @FXML
    TextArea sqlText;

    @FXML
    JFXButton filter_apply;

    @FXML
    ComboBox filter_name;

    private _query qr = new _query();

    @FXML
    public void initialize() {
        sqlText.setText(filter.getSqlFilter());
        sqlText.setWrapText(true);

        filter_name.setItems(qr.getFiltersNames());
        filter_apply.setOnAction((ActionEvent event) -> {
            filter.setSqlFilter(sqlText.getText());
        });

        filter_name.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {

            if (filter_name.getSelectionModel().getSelectedItem() != null) {
                final String name = filter_name.getSelectionModel().getSelectedItem().toString();
                final String query = qr.getFilterByName(name);
                sqlText.setText(query);
//                filter.setSqlFilter(sqlText.getText());
            }
        });
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }
}
