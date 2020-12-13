package ru.haval.filter;

import javafx.beans.property.SimpleStringProperty;

public abstract class APWRFilter {
    protected SimpleStringProperty sqlFilter = new SimpleStringProperty("");

    public String getSqlFilter() {
        return sqlFilter.get();
    }

    public SimpleStringProperty sqlFilterProperty() {
        return sqlFilter;
    }

    public void setSqlFilter(String sqlFilter) {
        this.sqlFilter.set(sqlFilter);
    }
}
