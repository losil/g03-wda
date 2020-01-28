package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class A13_DeleteViewController {
    @FXML
    private TextField txt_id;

    @FXML
    private Label lbl_status;

    @FXML
    protected void initialize(){
        // force the field to be numeric only
        UIToolbox.onlyAllowIntegerInTextField(txt_id);
    }

    @FXML
    private void onDelete(){
        if (checkValues()) {
        try {
                Operator operator = MainViewBorderPaneController.stub.findOperatorByID(Integer.parseInt(txt_id.getText()));

                if (MainViewBorderPaneController.stub.deleteOperator(operator)) {
                    lbl_status.setTextFill(Color.web("#008000"));
                    lbl_status.setText("Benutzer gelöscht");
                } else {
                    lbl_status.setTextFill(Color.web("#FF0000"));
                    lbl_status.setText("Fehler aufgetreten!");
                }

            } catch(Exception e){
                lbl_status.setTextFill(Color.web("#FF0000"));
                lbl_status.setText("Benutzer nicht gefunden!");
            }
        }
    }

    private boolean checkValues() {
        if (!txt_id.getText().equals("")) {
            return true;
        }
        else{
            lbl_status.setText("Personalnummer fehlt!");
            lbl_status.setTextFill(Color.web("#FF0000"));
            return false;
        }
    }
}
