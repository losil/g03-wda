package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A14_UpdateViewController {
    private static Logger logger = LogManager.getLogger(A14_UpdateViewController.class);

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_firmenname;

    @FXML
    private TextField txt_strasse;

    @FXML
    private TextField txt_nummer;

    @FXML
    private TextField txt_plz;

    @FXML
    private TextField txt_ort;

    @FXML
    private TextField txt_ansprechpartner;

    @FXML
    private TextField txt_tel;

    @FXML
    private TextField txt_lizenz;

    @FXML
    private Label lbl_status;

    @FXML
    protected void initialize(){
        // force the field to be numeric only
        UIToolbox.onlyAllowIntegerInTextField(txt_id);
        UIToolbox.onlyAllowIntegerInTextField(txt_plz);

    }

    @FXML
    private void onSearch(){
        try {
            Customer customer = MainViewBorderPaneController.stub.findCustomerByID(Integer.parseInt(txt_id.getText()));
            txt_firmenname.setText(customer.getFirmenname());
            txt_strasse.setText(customer.getStrasse());
            txt_nummer.setText(customer.getStrassenBezeichnung());
            txt_plz.setText(Integer.toString(customer.getPlz()));
            txt_ort.setText(customer.getOrt());
            txt_ansprechpartner.setText(customer.getAnsprechspartner());
            txt_tel.setText(customer.getTelefon());
            txt_lizenz.setText(customer.getLizenzart());

        } catch (Exception e) {
            lbl_status.setText("Kunde nicht gefunden, bitte ID prüfen!");
            lbl_status.setTextFill(Color.web("#FF0000"));
        }
    }

    @FXML
    private void onUpdate() {
        if (UIToolbox.checkCustomerParameters(txt_id, txt_firmenname, txt_strasse, txt_nummer, txt_plz, txt_ort, txt_ansprechpartner, txt_tel, txt_lizenz, lbl_status)) {
            try {
                Customer uCustomer = new Customer(Integer.parseInt(txt_id.getText()), txt_firmenname.getText(), txt_strasse.getText(), txt_nummer.getText(), Integer.parseInt(txt_plz.getText()), txt_ort.getText(), txt_ansprechpartner.getText(), txt_tel.getText(), txt_lizenz.getText());
                logger.info("Create Customer Object: " + uCustomer);

                try {
                    logger.info("trying to update Customer");
                    MainViewBorderPaneController.stub.updateCustomer(uCustomer);
                    logger.info("Update successful!");
                    lbl_status.setTextFill(Color.web("#008000"));
                    lbl_status.setText("Kunde aktualisiert");
                } catch (Exception e) {
                    lbl_status.setTextFill(Color.web("#FF0000"));
                    lbl_status.setText("Update fehlgeschlagen");
                    logger.info("Update not successful!");
                }
            } catch (Exception e) {
                lbl_status.setTextFill(Color.web("#FF0000"));
                lbl_status.setText("Ungültiger Wert!");
            }
        }
    }
}
