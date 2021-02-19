package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.db._query;
import ru.haval.filter.IFilter;
import ru.haval.share_class.s_class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterController {

    private IFilter filter;

    @FXML
    TextArea sqlText;

    @FXML
    TextField var_value;

    @FXML
    JFXButton filter_apply, save_filter, del_filter, cancel_ot;

    @FXML
    ComboBox filter_name, variables;

    private _query qr = new _query();
    private HashMap<String, String> variableValues = new HashMap<>();
    s_class sclass = new s_class();

    @FXML
    public void initialize() {
        sclass._style(filter_apply);
        sclass._style(save_filter);
        sclass._style(del_filter);
        sclass._style(cancel_ot);

        sqlText.setText(filter.getSqlFilter());
        sqlText.setWrapText(true);

        filter_name.setItems(qr.getFiltersNames());

        filter_apply.setOnAction((ActionEvent event) -> {
            filter.setSqlFilter(sqlText.getText());
            Stage stage = (Stage) filter_name.getScene().getWindow();
            stage.close();
        });

        save_filter.setOnAction((ActionEvent event) -> {
            if (filter_name.getSelectionModel().getSelectedItem() != null) {
                final String NAME = filter_name.getSelectionModel().getSelectedItem().toString();
                final String FILTER = sqlText.getText();
                qr.saveSqlFilter(NAME, FILTER);
                filter_name.setItems(qr.getFiltersNames());
            }
        });

        del_filter.setOnAction((ActionEvent event) -> {
            if (filter_name.getSelectionModel().getSelectedItem() != null) {
                qr.deleteFilterByName(filter_name.getSelectionModel().getSelectedItem().toString());
                filter_name.setItems(qr.getFiltersNames());
            }
        });

        cancel_ot.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) filter_name.getScene().getWindow();
            stage.close();
        });

        filter_name.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (filter_name.getSelectionModel().getSelectedItem() != null) {
                final String name = filter_name.getSelectionModel().getSelectedItem().toString();
                final String query = qr.getFilterByName(name);
                sqlText.setText(query);
                updateSql();
            }
        });

        variables.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (variables.getSelectionModel().getSelectedItem() != null) {
                var_value.setText(variableValues.get(variables.getSelectionModel().getSelectedItem().toString()));
            }
        });

        sqlText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateSql();
            }
        });

        var_value.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(variables.getSelectionModel().getSelectedItem().toString());
                System.out.println("new value");
                System.out.println(newValue);
//                changeVariableValue(variables.getSelectionModel().getSelectedItem().toString(), newValue);
//                updateSql();
            }
        });
    }

    private void updateSql() {
        updateVars();
    }

    private void updateVars() {
        variables.setItems(getVarNamesFromString(sqlText.getText()));
        updateVariableValues();
    }

    private void updateVariableValues() {
        variableValues.clear();
        String s = sqlText.getText();
        Pattern pattern = Pattern.compile("set(.+);");
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        String tmp = matcher.group(1);
        pattern = Pattern.compile("@[^=\\s]+");
        Pattern pattern2  = Pattern.compile("=\\W*(\\w+)");
        matcher = pattern.matcher(tmp);
        Matcher matcher2 = pattern2.matcher(tmp);
        while (matcher.find()) {
            matcher2.find();
            variableValues.put(matcher.group(0).substring(1), matcher2.group(1));
        }
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }

    private ObservableList<String> getVarNamesFromString(String s) {
        ObservableList<String> vars = FXCollections.observableArrayList();
        Pattern pattern = Pattern.compile("set(.+);");
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        String tmp = matcher.group(1);
        pattern = Pattern.compile("@[^=\\s]+");
        matcher = pattern.matcher(tmp);
        while (matcher.find()) {
            vars.add(matcher.group(0).substring(1));
        }
        return vars;
    }
}
