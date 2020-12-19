package ru.haval.action;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ru.haval.application.conn_connector;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

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
        otv.setItems(qr._select_fio_for_ap(2, sclass.parser_str(apwr_controller.SHOP_NAME, 0)));
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
        for (hmmr_wp_model i : apwr_controller.getInstance().table_wp.getSelectionModel().getSelectedItems()) {
            if (i.getOFT().equals(apwr_controller.USER_S)) {
                qr.changeOtv(i.getId().substring(2), newOTV);
            }
        }
        apwr_controller.getInstance().setWPItems(qr._select_data_wp(apwr_controller.USER_S));
    }
}
