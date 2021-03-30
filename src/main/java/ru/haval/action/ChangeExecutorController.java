package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ru.haval.application.conn_connector;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

import java.util.HashSet;
import java.util.Set;

public class ChangeExecutorController {
    @FXML
    ComboBox otv;

    @FXML
    JFXButton upd_oft_ok, upd_oft_cancel;

    _query qr = new _query();
    s_class sclass = new s_class();

    @SuppressWarnings({"unchecked"})
    @FXML
    public void  initialize() {
        ObservableList<String> otvs = FXCollections.observableArrayList();
        otvs.add("need select");
        otvs.addAll(qr._select_fio_for_ap(2, sclass.parser_str(apwr_controller.SHOP_NAME, 0)));
        otv.setItems(otvs);
        otv.getSelectionModel().select(0);
        sclass._style(upd_oft_ok);
        sclass._style(upd_oft_cancel);
        if (conn_connector.USER_ROLE.equals("Technics")) {
            upd_oft_ok.setDisable(true);
        } else {
            upd_oft_ok.setDisable(false);
        }
    }


    public void changeOFTs(ActionEvent actionEvent) {
        String newOTV = otv.getValue().toString().split(" ")[0];
        Stage stage = (Stage) otv.getScene().getWindow();
        stage.close();
        String tmp = otv.getSelectionModel().getSelectedItem().toString().split(" ")[0];
        final String NEW_USER_ID = tmp.equals("need") ? "need select" : tmp;
        if (stage.getUserData() instanceof  TableView) {

            TableView tableView = (TableView) stage.getUserData();
            if (tableView.getItems().get(0) instanceof hmmr_wp_model) {
                for (hmmr_wp_model i : apwr_controller.getInstance().table_wp.getSelectionModel().getSelectedItems()) {
                    if (i.getOFT().equals(apwr_controller.USER_S)) {
                        qr.updatePmExecutor(i.getPM_Num(), newOTV);
                        qr.changeWpOtv(i.getId().substring(2), newOTV);
                    }
                }
                apwr_controller.getInstance().setWPItems(qr._select_data_wp(apwr_controller.USER_S));
            } else if (tableView.getItems().get(0) instanceof hmmr_ap_model) {
                for (hmmr_ap_model i : apwr_controller.getInstance().table_ap.getSelectionModel().getSelectedItems()) {
                    if (i.getOFT().equals(apwr_controller.USER_S)) {
                        qr.updatePmExecutor(i.getPM_Num(), newOTV);
                        qr.changeApOtv(i.getId().substring(2), newOTV);
                    }
                }
                apwr_controller.getInstance().updateAPTable();
            }
        } else if (stage.getUserData() instanceof Stuff_Controller) {
            Stuff_Controller stuff_controller = (Stuff_Controller) stage.getUserData();
            if (stuff_controller.table_staff.getSelectionModel().getSelectedItem() != null) {
                final String USER_ID = stuff_controller.table_staff.getSelectionModel().getSelectedItem().ID.get();
                if (USER_ID != null && NEW_USER_ID != null) {
                    qr.changePmRespPmExecutorIDs(USER_ID, NEW_USER_ID);
                }
            }
        } else if (stage.getUserData() instanceof pm_controller) {
            pm_controller pmController = (pm_controller) stage.getUserData();
            if (pmController.table_pm.getSelectionModel().getSelectedItems().size() > 1) {
                Set<String> PMGroups = new HashSet<>();
                for (hmmr_pm_model item : pmController.table_pm.getSelectionModel().getSelectedItems()) {
                    PMGroups.add(item.getGroup_PM());
                    if (pmController.responsible) {
                        qr.updateResponsible(item.getId(), NEW_USER_ID);
                    } else {
                        qr.updatePmExecutor(item.getId(), NEW_USER_ID);
                    }
                }
                for (String group : PMGroups) {
                    qr.changeResponsible(group, NEW_USER_ID);
                }
                pmController.setPmItems(qr._select_data_pm2());
            }
        }
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) upd_oft_cancel.getScene().getWindow();
        stage.close();
    }
}
