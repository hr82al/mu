package ru.haval.action;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

public class ChangeExecutorController {
    @FXML
    ComboBox otv;

    _query qr = new _query();
    s_class sclass = new s_class();

    @SuppressWarnings({"unchecked"})
    @FXML
    public void  initialize() {
        otv.setItems(qr._select_fio_for_ap(2, sclass.parser_str(apwr_controller.SHOP_NAME, 0)));
    }
}
