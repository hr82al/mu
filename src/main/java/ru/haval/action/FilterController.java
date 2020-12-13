package ru.haval.action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.haval.filter.IFilter;

public class FilterController {

    private IFilter filter;

    @FXML
    TextField sqlText;

    @FXML
    public void initialize() {
        sqlText.setText(filter.getSqlFilter());
//        sqlText.setText(filter.getSqlFilter());
    }

    @FXML
    @SuppressWarnings("unused")
    public void onApply(@SuppressWarnings("unused") ActionEvent actionEvent) {
        filter.setSqlFilter(sqlText.getText());
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }
}
