package ru.haval.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.apache.commons.lang3.StringUtils;
import ru.haval.action.apwr_controller;
import ru.haval.action.hmmr_wp_model;
import ru.haval.action.pm_controller;

import java.util.HashSet;

import static com.mysql.cj.protocol.a.MysqlTextValueDecoder.isDate;

public class DynamicFilter {
    TableView<WrTable> table;

    private final HashSet<String> exactValues = new HashSet<>();
    private final ObservableList<WrTable> pmDbRows = FXCollections.observableArrayList();

    public DynamicFilter(TableView<? extends WrTable> table) {
            this.table = (TableView<WrTable>) table;
    }

    public void change(String newValue) {
        showTable(newValue);
    }

    private void showTable(String pmRows) {
        synchronized (pm_controller.class) {
            if (pmRows.length() != 0) {
                ObservableList<WrTable> searchedRows = FXCollections.observableArrayList();
                ObservableList<WrTable> tmpSearch = FXCollections.observableArrayList();
                tmpSearch.addAll(pmDbRows);
                String[] searches = pmRows.split(",");

                for (String search : searches) {
                    //If wpSearch one upper letter search by shop
                    if (search.length() == 1 && Character.isUpperCase(search.charAt(0))) {
                        for (WrTable i : tmpSearch) {
                            if (i.getEquip().charAt(0) == search.charAt(0)) {
                                searchedRows.add(i);
                            }
                        }
                        //If the search is OTV
                    } else if (exactValues.contains(search)) {
                        for (WrTable i : tmpSearch) {

                            if (i.getOtv() != null && i.getOtv().equals(search)
                                    || i.getOtv_Isp() != null && i.getOtv_Isp().equals(search)
                                    || i.getPMC() != null && i.getPMC().equals(search)
                                    || i.getGroup_PM() != null && i.getGroup_PM().equals(search)) {
                                searchedRows.add(i);
                            }
                        }
                    } else {
                        for (WrTable i : tmpSearch) {
                            if ((i.getEquip() != null && i.getEquip().contains(search))
                                    || (i.getnum_inst() != null && i.getnum_inst().contains(search))
                                    || i.getap_num() != null && i.getap_num().contains(search)
                                    || i.getId() != null && i.getId().contains(search)
                                    || i.getdata() != null && i.getdata().contains(search)
                                    || i.getshift_report() != null && i.getshift_report().contains(search)) {
                                searchedRows.add(i);
                            }
                        }
                    }
                    tmpSearch.clear();
                    tmpSearch.addAll(searchedRows);
                    searchedRows.clear();
                }
                table.setItems(tmpSearch);
            } else {
                table.setItems(pmDbRows);
            }
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
        }
    }

    public void update(ObservableList<? extends WrTable> data, String newFilter) {
        pmDbRows.clear();
        pmDbRows.addAll(data);
        initExactValues();
        showTable(newFilter);
    }

    private void initExactValues() {
        exactValues.clear();
        for (WrTable i : pmDbRows) {
            exactValues.add(i.getOtv());
            exactValues.add(i.getOtv_Isp());
            exactValues.add(i.getPMC());
            exactValues.add(i.getGroup_PM());
        }
        exactValues.remove(null);
    }
}
