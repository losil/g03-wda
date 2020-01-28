package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class A14_DeleteViewController {
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
                Customer customer = MainViewBorderPaneController.stub.findCustomerByID(Integer.parseInt(txt_id.getText()));

                if (MainViewBorderPaneController.stub.deleteCustomer(customer)) {
                    lbl_status.setTextFill(Color.web("#008000"));
                    lbl_status.setText("Kunde gelöscht");
                } else {
                    lbl_status.setTextFill(Color.web("#FF0000"));
                    lbl_status.setText("Fehler aufgetreten!");
                }

            } catch(Exception e){
                lbl_status.setTextFill(Color.web("#FF0000"));
                lbl_status.setText("Kunde wurde nicht gefunden!");
            }
        }
    }

    private boolean checkValues() {
        if (!txt_id.getText().equals("")) {
            return true;
        }
        else{
            lbl_status.setText("Kundennummer fehlt!");
            lbl_status.setTextFill(Color.web("#FF0000"));
            return false;
        }
    }
}
