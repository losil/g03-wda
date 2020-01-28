package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A13_UpdateViewController {
    private static Logger logger = LogManager.getLogger(A13_CreateController.class);
    Operator orgOperator;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_vorname;

    @FXML
    private TextField txt_personalnummer;

    @FXML
    private TextField txt_benutzername;

    @FXML
    private PasswordField pwd_passwort1;

    @FXML
    private PasswordField pwd_passwort2;

    @FXML
    private Label lbl_meldung;

    @FXML
    private Button btn_search;

    @FXML
    private Label lbl_personalnummer;

    @FXML
    protected void initialize(){
        // force the field to be numeric only
        UIToolbox.onlyAllowIntegerInTextField(txt_personalnummer);

    }

    @FXML
    private void onSearch(){
        try {
            orgOperator = MainViewBorderPaneController.stub.findOperatorByID(Integer.parseInt(txt_personalnummer.getText()));
            txt_personalnummer.setVisible(false);
            btn_search.setVisible(false);
            lbl_personalnummer.setVisible(false);
            txt_personalnummer.setText(Integer.toString(orgOperator.getPersonalnummer()));
            txt_name.setText(orgOperator.getName());
            txt_vorname.setText(orgOperator.getVorname());
            txt_benutzername.setText(orgOperator.getUsername());
            pwd_passwort1.setText(orgOperator.getPasswort());
            pwd_passwort2.setText(orgOperator.getPasswort());

        } catch (Exception e) {
            lbl_meldung.setText("Benutzer nicht gefunden, bitte ID prüfen!");
            lbl_meldung.setTextFill(Color.web("#FF0000"));
        }
    }

    @FXML
    private void onUpdate(){
        if(UIToolbox.checkOperatorParameters(txt_personalnummer,txt_name,txt_vorname,txt_benutzername,pwd_passwort1,pwd_passwort2,lbl_meldung)) {
            try {
                Operator updatedOperator = new Operator(Integer.parseInt(txt_personalnummer.getText()), txt_name.getText(), txt_vorname.getText(), txt_benutzername.getText(), pwd_passwort1.getText());
                logger.info("Create Operator Object: " + updatedOperator);

                if(orgOperator.getUsername().equals(updatedOperator.getUsername()) || UIToolbox.checkIfUsernameIsUnique(txt_benutzername.getText(),lbl_meldung)){
                    try {
                        logger.info("trying to update Operator");
                        MainViewBorderPaneController.stub.updateOperator(updatedOperator);
                        logger.info("Update successfull!");
                        lbl_meldung.setTextFill(Color.web("#008000"));
                        lbl_meldung.setText("Benutzer aktuallisiert");
                        txt_personalnummer.setVisible(true);
                        btn_search.setVisible(true);
                        lbl_personalnummer.setVisible(true);
                        txt_personalnummer.clear();
                        txt_personalnummer.clear();
                        txt_name.clear();
                        txt_vorname.clear();
                        txt_benutzername.clear();
                        pwd_passwort1.clear();
                        pwd_passwort2.clear();

                    } catch (Exception e) {
                        lbl_meldung.setTextFill(Color.web("#FF0000"));
                        lbl_meldung.setText("Update fehlgeschlagen");
                        logger.info("Update not successfull!");
                    }
                }

            } catch (Exception e) {
                lbl_meldung.setTextFill(Color.web("#FF0000"));
                lbl_meldung.setText("Ungültiger Wert!");
            }
        }
    }

}
