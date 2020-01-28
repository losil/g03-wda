package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class A13_CreateController {
    private static Logger logger = LogManager.getLogger(A13_CreateController.class);

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
    protected void initialize(){
        // force the field to be numeric only
        UIToolbox.onlyAllowIntegerInTextField(txt_personalnummer);
    }

    @FXML
    private void onSave(){
        if(UIToolbox.checkOperatorParameters(txt_personalnummer,txt_name,txt_vorname,txt_benutzername,pwd_passwort1,pwd_passwort2,lbl_meldung)){
            if(UIToolbox.checkIfUsernameIsUnique(txt_benutzername.getText(),lbl_meldung)){
                try {

                    Operator newOperator = new Operator(Integer.parseInt(txt_personalnummer.getText()), txt_name.getText(), txt_vorname.getText(), txt_benutzername.getText(), pwd_passwort1.getText());

                    logger.info("Create Operator Object: " + newOperator);

                    try {
                        logger.info("trying to load operator to DB");
                        MainViewBorderPaneController.stub.addOperator(newOperator);
                        logger.info("Import successfull!");
                        lbl_meldung.setTextFill(Color.web("#008000"));
                        lbl_meldung.setText("Benutzer erstellt");
                    } catch (Exception e) {
                        lbl_meldung.setTextFill(Color.web("#FF0000"));
                        lbl_meldung.setText("ID bereits vorhanden");
                        logger.info("Import not successfull!");
                    }

                }
                catch (Exception e){
                    lbl_meldung.setTextFill(Color.web("#FF0000"));
                    lbl_meldung.setText("Ungültiger Wert!");
                }
            }
        }
    }
}
