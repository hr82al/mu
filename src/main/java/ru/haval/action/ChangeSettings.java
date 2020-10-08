package ru.haval.action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.config.Config;

public class ChangeSettings {
    @FXML
    private TextField address, dbName, dbUser, dbParams;

    @FXML
    private Spinner<Integer> port;

    @FXML
    private PasswordField dbPassword;
    @FXML
    private Button cancel;

    @FXML
    public void initialize(){
        address.setText(Config.getInstance().getAddress());
        dbName.setText(Config.getInstance().getDbName());
        dbUser.setText(Config.getInstance().getDbUser());
        dbParams.setText(Config.getInstance().getDbParams());
        dbPassword.setText(Config.getInstance().getDbPassword());
        port.getValueFactory().setValue(Config.getInstance().getPort());
    }


    public void saveParameters(ActionEvent actionEvent) {
        Config.getInstance().setAddress(address.getText());
        Config.getInstance().setDbName(dbName.getText());
        Config.getInstance().setDbUser(dbUser.getText());
        Config.getInstance().setDbParams(dbParams.getText());
        Config.getInstance().setDbPassword(dbPassword.getText());
        Config.getInstance().setPort(port.getValue());
        closeWindow();
    }

    public void cancel(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
