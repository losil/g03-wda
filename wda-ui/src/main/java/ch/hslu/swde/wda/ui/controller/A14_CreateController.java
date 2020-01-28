package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class A14_CreateController {
    private static Logger logger = LogManager.getLogger(A14_CreateController.class);

    @FXML
    private TextField txt_kundennummer;

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
        UIToolbox.onlyAllowIntegerInTextField(txt_kundennummer);
        UIToolbox.onlyAllowIntegerInTextField(txt_plz);
        UIToolbox.onlyAllowIntegerInTextField(txt_tel);
    }

    @FXML
    private void onSave() {
        if (UIToolbox.checkCustomerParameters(txt_kundennummer, txt_firmenname, txt_strasse, txt_nummer, txt_plz, txt_ort, txt_ansprechpartner, txt_tel, txt_lizenz, lbl_status)) {

            try{
                if(MainViewBorderPaneController.stub.findCustomerByID(Integer.parseInt(txt_kundennummer.getText())) == null){
                    try {
                        Customer newCustomer = new Customer(Integer.parseInt(txt_kundennummer.getText()), txt_firmenname.getText(), txt_strasse.getText(), txt_nummer.getText(), Integer.parseInt(txt_plz.getText()), txt_ort.getText(), txt_ansprechpartner.getText(), txt_tel.getText(), txt_lizenz.getText());
                        logger.info("Create Customer Object: " + newCustomer);

                        try {
                            logger.info("trying to load Customer to DB");
                            MainViewBorderPaneController.stub.addCustomer(newCustomer);
                            logger.info("Import successful!");
                            lbl_status.setTextFill(Color.web("#008000"));
                            lbl_status.setText("Kunde erfolgreich hinzugefügt!");
                        } catch (Exception e) {
                            lbl_status.setTextFill(Color.web("#FF0000"));
                            lbl_status.setText("ID bereits vorhanden");
                            logger.info("Import not successful!");
                        }

                    } catch (Exception e) {
                        lbl_status.setTextFill(Color.web("#FF0000"));
                        lbl_status.setText("Ungültiger Wert!");
                    }

                }else{
                    lbl_status.setTextFill(Color.web("#FF0000"));
                    lbl_status.setText("ID bereits vorhanden!");
                }
            }catch(Exception e){

            }

        }
    }
        }
