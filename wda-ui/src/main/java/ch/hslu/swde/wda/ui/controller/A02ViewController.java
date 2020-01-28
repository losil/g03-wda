package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.WeatherData;
import ch.hslu.swde.wda.ui.CSVExportable;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class A02ViewController {
    private static Logger logger = LogManager.getLogger(A02ViewController.class);
    private ArrayList<WeatherData> weatherDataArrayList;
    private ArrayList<ReturnObject> returnObjectArrayList;

    @FXML
    private MenuButton mbtn_ortschaften;

    @FXML
    private Button btn_abfrage;

    @FXML
    private MenuButton mbtn_export;

    @FXML
    private TableView<ReturnObject> tbl_content;

    @FXML
    private TableColumn<ReturnObject,String> clm_ortschaft;

    @FXML
    private TableColumn<ReturnObject,Timestamp> clm_datum;

    @FXML
    private TableColumn<ReturnObject,Float> clm_temperatur;

    @FXML
    private TableColumn<ReturnObject,Integer> clm_luftdruck;

    @FXML
    private TableColumn<ReturnObject,Integer> clm_feuchtigkeit;

    @FXML
    private TableColumn<ReturnObject,Float> clm_windgeschwindigkeit;

    @FXML
    private TableColumn<ReturnObject,String> clm_windrichtung;

    @FXML
    private TableColumn<ReturnObject,String> clm_wetterbeschreibung;

    @FXML
    private TableColumn<ReturnObject,String> clm_zusammenfassung;

    @FXML
    private Label lbl_nichtkomplett;

    @FXML
    private Label lbl_status;

    @FXML
    private void onExport() throws IOException {
        UIToolbox.csvExport(tbl_content, lbl_status);
    }

    @FXML
    protected void initialize(){
        UIToolbox.createMenuButtonWithCities(mbtn_ortschaften);
    }

    @FXML
    protected void abfrageWetterdaten() {
        //Daten zu City abfragen
        try {
            weatherDataArrayList = new ArrayList<>();
            returnObjectArrayList = new ArrayList<>();
            logger.info ("Load View A02");
            List<String> selectedItems = UIToolbox.getCheckedItems(mbtn_ortschaften);
            if(!selectedItems.isEmpty()) {
                //lbl_nichtkomplett.setVisible(false);
                for (String ortschaft : selectedItems) {
                    logger.info("Loading data for: " + ortschaft);
                    weatherDataArrayList.addAll(MainViewBorderPaneController.stub.getLast24HoursData(MainViewBorderPaneController.stub.findCityByName(ortschaft).getId()));
                }
                for (WeatherData data : weatherDataArrayList){
                    returnObjectArrayList.add(new ReturnObject(data.getCity().getName(), data.getTimestamp(), data.getTemperatureInCelsius(), data.getWindSpeed(), data.getWindDirection(),data.getPressure(),data.getHumidity(),data.getWeatherDescription(),data.getWeatherSummary()));
                }
            }else{
                lbl_nichtkomplett.setText("Bitte Ortschaft auswählen");
                lbl_nichtkomplett.setVisible(true);
            }


            clm_ortschaft.setSortType(TableColumn.SortType.ASCENDING);
            clm_ortschaft.setCellValueFactory(new PropertyValueFactory<>("city"));
            clm_datum.setSortType(TableColumn.SortType.ASCENDING);
            clm_datum.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
            clm_temperatur.setCellValueFactory(new PropertyValueFactory<>("temperatureInCelsius"));
            clm_feuchtigkeit.setCellValueFactory(new PropertyValueFactory<>("humidity"));
            clm_windgeschwindigkeit.setCellValueFactory(new PropertyValueFactory<>("windSpeed"));
            clm_windrichtung.setCellValueFactory(new PropertyValueFactory<>("windDirection"));
            clm_wetterbeschreibung.setCellValueFactory(new PropertyValueFactory<>("weatherDescription"));
            clm_zusammenfassung.setCellValueFactory(new PropertyValueFactory<>("weatherSummary"));


            //Content von Array laden
            tbl_content.getItems().setAll(returnObjectArrayList);
            tbl_content.getSortOrder().add(clm_ortschaft);
            tbl_content.getSortOrder().add(clm_datum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class ReturnObject implements CSVExportable {
        private String city;
        private Timestamp timestamp;
        private float temperatureInCelsius;
        private float windSpeed;
        private String windDirection;
        private int pressure;
        private int humidity;
        private String weatherDescription;
        private String weatherSummary;

        public ReturnObject(String city, Timestamp timestamp, float temperatureInCelsius, float windSpeed, String windDirection, int pressure, int humidity, String weatherDescription, String weatherSummary) {
            this.city = city;
            this.timestamp = timestamp;
            this.temperatureInCelsius = temperatureInCelsius;
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
            this.pressure = pressure;
            this.humidity = humidity;
            this.weatherDescription = weatherDescription;
            this.weatherSummary = weatherSummary;
        }


        @Override
        public String toCSVString() {
            return city + "," + timestamp + "," + temperatureInCelsius + "," + windSpeed +"," + windDirection + "," + pressure + "," + humidity + "," + weatherDescription + "," + weatherSummary +"\n";
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

        public float getTemperatureInCelsius() {
            return temperatureInCelsius;
        }

        public void setTemperatureInCelsius(float temperatureInCelsius) {
            this.temperatureInCelsius = temperatureInCelsius;
        }

        public float getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(float windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public String getWeatherDescription() {
            return weatherDescription;
        }

        public void setWeatherDescription(String weatherDescription) {
            this.weatherDescription = weatherDescription;
        }

        public String getWeatherSummary() {
            return weatherSummary;
        }

        public void setWeatherSummary(String weatherSummary) {
            this.weatherSummary = weatherSummary;
        }

        @Override
        public String toString() {
            return "ReturnObject{" +
                    "city='" + city + '\'' +
                    ", timestamp=" + timestamp +
                    ", temperatureInCelsius=" + temperatureInCelsius +
                    ", windSpeed=" + windSpeed +
                    ", windDirection='" + windDirection + '\'' +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", weatherDescription='" + weatherDescription + '\'' +
                    ", weatherSummary='" + weatherSummary + '\'' +
                    '}';
        }
    }

}
