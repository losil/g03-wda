package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.WeatherData;

import ch.hslu.swde.wda.ui.CSVExportable;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class A03_10_11_12ViewController {
    private static Logger logger = LogManager.getLogger(A03_10_11_12ViewController.class);
    private Timestamp timestamp_start;
    private Timestamp timestamp_end;
    private ArrayList<WeatherData> weatherDataArrayList;
    private ArrayList<ReturnObject> returnObjectArrayList;

    @FXML
    private DatePicker date_start;

    @FXML
    private DatePicker date_end;

    @FXML
    private MenuButton mbtn_ortschaften;

    @FXML
    private Button btn_abfrage;

    @FXML
    private MenuButton mbtn_export;

    @FXML
    private Label lbl_nichtkomplett;

    @FXML
    private TableView<ReturnObject> tbl_content;

    @FXML
    private TableColumn<ReturnObject,String> clm_ortschaft;

    @FXML
    private TableColumn<ReturnObject, Time> clm_datum;

    @FXML
    private TableColumn<ReturnObject,Float> clm_temperatur;

    @FXML
    private TableColumn<ReturnObject,Float> clm_luftdruck;

    @FXML
    private TableColumn<ReturnObject,Float> clm_feuchtigkeit;

    @FXML
    private Label lbl_status;

    @FXML
    protected void initialize(){
        UIToolbox.createMenuButtonWithCities(mbtn_ortschaften);
    }

    @FXML
    protected void abfrageWetterdaten() {

        try {
            timestamp_start = UIToolbox.convertDatePickerToTimeStamp(date_start);
            timestamp_end = UIToolbox.convertDatePickerToTimeStamp(date_end);
            lbl_nichtkomplett.setVisible(false);
        } catch(Exception e) {
            logger.warn("no datum catch");
            lbl_nichtkomplett.setText("Bitte korrektes Datum eintragen");
            lbl_nichtkomplett.setVisible(true);
        }


        //Daten zu City abfragen
        try {
            weatherDataArrayList = new ArrayList<>();
            returnObjectArrayList = new ArrayList<>();
            logger.info ("Load View A03");
            List<String> selectedItems = UIToolbox.getCheckedItems(mbtn_ortschaften);
            if(!selectedItems.isEmpty()) {
                //lbl_nichtkomplett.setVisible(false);
                for (String ortschaft : selectedItems) {
                    logger.info("Loading data for: " + ortschaft);
                    weatherDataArrayList.addAll(MainViewBorderPaneController.stub.findByDate(timestamp_start, timestamp_end, MainViewBorderPaneController.stub.findCityByName(ortschaft).getId()));
                }
                for (WeatherData data : weatherDataArrayList){
                    returnObjectArrayList.add(new ReturnObject(data.getCity().getName(), data.getTimestamp(), data.getTemperatureInCelsius(), data.getPressure(), data.getHumidity()));
                }

            }else{
                lbl_nichtkomplett.setText("Bitte Ortschaft auswählen");
                lbl_nichtkomplett.setVisible(true);
            }

            //Ortschaft
            clm_ortschaft.setSortType(TableColumn.SortType.ASCENDING);
            clm_ortschaft.setCellValueFactory(new PropertyValueFactory<>("city"));

            //Datum
            clm_datum.setSortType(TableColumn.SortType.ASCENDING);
            clm_datum.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
            //Temperatur
            clm_temperatur.setCellValueFactory(new PropertyValueFactory<>("temperature"));
            //Luftdruck
            clm_luftdruck.setCellValueFactory(new PropertyValueFactory<>("pressure"));
            //Feuchtigkeit
            clm_feuchtigkeit.setCellValueFactory(new PropertyValueFactory<>("humidity"));

            //Content von Array laden
            tbl_content.getItems().setAll(returnObjectArrayList);
            tbl_content.getSortOrder().add(clm_ortschaft);
            tbl_content.getSortOrder().add(clm_datum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExport() throws IOException {
        UIToolbox.csvExport(tbl_content, lbl_status);
    }

    public class ReturnObject implements CSVExportable {
        private String city;
        private Timestamp timestamp;
        private float temperature;
        private int pressure;
        private int humidity;

        @Override
        public String toString() {
            return "ReturnObject{" +
                    "city='" + city + '\'' +
                    ", timestamp=" + timestamp +
                    ", temperature=" + temperature +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }

        public ReturnObject(String city, Timestamp timestamp, float temperature, int pressure, int humidity) {
            this.city = city;
            this.timestamp = timestamp;
            this.temperature = temperature;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        @Override
        public String toCSVString() {
            return city + "," + timestamp + "," + temperature + "," + pressure +"," + humidity + "\n";
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }


}

