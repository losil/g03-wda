package ch.hslu.swde.wda.ui;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.ui.controller.MainViewBorderPaneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


public class UIToolbox {
    public static void onlyAllowIntegerInTextField(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static boolean checkOperatorParameters(TextField txt_personalnummer, TextField txt_name, TextField txt_vorname, TextField txt_benutzername, PasswordField pwd_passwort1, PasswordField pwd_passwort2, Label lbl_meldung) {
        if (!txt_personalnummer.getText().equals("")) {
            if (!txt_name.getText().equals("")) {
                if (!txt_vorname.getText().equals("")) {
                    if (!txt_benutzername.getText().equals("")) {
                        if (!pwd_passwort1.getText().equals("")) {
                            if (pwd_passwort1.getText().equals(pwd_passwort2.getText())) {
                                return true;
                            } else {
                                lbl_meldung.setTextFill(Color.web("#FF0000"));
                                lbl_meldung.setText("Passwörter stimmen nicht überein!");
                                return false;
                            }
                        } else {
                            lbl_meldung.setText("Passwort fehlt!");
                            lbl_meldung.setTextFill(Color.web("#FF0000"));
                            return false;
                        }
                    } else {
                        lbl_meldung.setText("Benutzername fehlt!");
                        lbl_meldung.setTextFill(Color.web("#FF0000"));
                        return false;
                    }
                } else {
                    lbl_meldung.setText("Vorname fehlt!");
                    lbl_meldung.setTextFill(Color.web("#FF0000"));
                    return false;
                }
            } else {
                lbl_meldung.setText("Name fehlt!");
                lbl_meldung.setTextFill(Color.web("#FF0000"));
                return false;
            }
        } else {
            lbl_meldung.setText("Personalnummer fehlt!");
            lbl_meldung.setTextFill(Color.web("#FF0000"));
            return false;
        }
    }

    public static boolean checkCustomerParameters(TextField txt_kundennummer, TextField txt_firmenname, TextField txt_strasse, TextField txt_nummer, TextField txt_plz, TextField txt_ort, TextField txt_ansprechpartner, TextField txt_tel, TextField txt_lizenz, Label lbl_meldung) {
        if (!txt_kundennummer.getText().equals("") && !txt_firmenname.getText().equals("") && !txt_strasse.getText().equals("") && !txt_nummer.getText().equals("") && !txt_plz.getText().equals("") && !txt_ort.getText().equals("") && !txt_ansprechpartner.getText().equals("") && !txt_tel.getText().equals("") && !txt_lizenz.getText().equals("") ) {

            return true;
        } else {
            lbl_meldung.setText("Informationen nicht vollständig!");
            lbl_meldung.setTextFill(Color.web("#FF0000"));
            return false;
        }
    }



    public static boolean checkIfUsernameIsUnique(String Username, Label lbl_meldung){
            try {
                MainViewBorderPaneController.stub.findOperatorByUsername(Username);
                lbl_meldung.setTextFill(Color.web("#FF0000"));
                lbl_meldung.setText("Benutzername bereits vorhanden!");
                return false;
            } catch (Exception e1) {
                return true;
            }

    }

    public static boolean checkIfKundennummerIsUnique(Integer kundennummer, Label lbl_status){
        try {
            MainViewBorderPaneController.stub.findCustomerByID(kundennummer);
            lbl_status.setTextFill(Color.web("#FF0000"));
            lbl_status.setText("Kundennummer bereits vorhanden!");
            return false;
        } catch (Exception e1) {
            return true;
        }
    }

    public static List<String> getCheckedItems(MenuButton mbtn_ortschaften){
        return mbtn_ortschaften.getItems().stream()
                .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());
    }

    public static void createMenuButtonWithCities(MenuButton mbtn_ortschaften){
        try {
            List<City> cityList = MainViewBorderPaneController.stub.findAllCities();
            for (int i = 0; i < cityList.size(); i++) {
                //erstellt pro City ein Neues CheckMenuItem.
                CheckMenuItem menuitem1 = new CheckMenuItem(cityList.get(i).getName());
                menuitem1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                    }
                });
                mbtn_ortschaften.getItems().add(menuitem1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Timestamp convertDatePickerToTimeStamp(DatePicker datepicker){
            return Timestamp.valueOf(datepicker.getValue().atStartOfDay());
    }

    public static void  csvExport(TableView tbl_content, Label lbl_status) throws IOException {
        /* erstellt Save as Dialogfenster */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("CSV speichern");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        Writer writer = null;
        writer = new BufferedWriter(new FileWriter(file));
        if ( file != null ) {

            try {
                ObservableList data = tbl_content.getItems();

                for (int i = 0; i < data.size(); i++) {
                    String title = ((CSVExportable)data.get(i)).toCSVString();

                    writer.write(title);
                    lbl_status.setText("Export erfolgreich.");
                    lbl_status.setTextFill(Color.web("#008000"));
                }

            } catch (Exception e) {
                e.printStackTrace();
                lbl_status.setText("Export nicht erfolgreich.");
                lbl_status.setTextFill(Color.web("#FF0000"));
            }

            finally {
                writer.flush();
                writer.close();
            }
        }
    }
}
