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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class A05ViewController {
    private static Logger logger = LogManager.getLogger(A04ViewController.class);

    private Timestamp timestamp_start;
    private Timestamp timestamp_end;
    private float maxTemp;
    private float minTemp;

    private ArrayList<ReturnObject> returnObjectArrayList;

    @FXML
    private Label lbl_nichtkomplett;

    @FXML
    private DatePicker date_start;

    @FXML
    private DatePicker date_end;

    @FXML
    private MenuButton mbtn_ortschaften;

    @FXML
    private TableView<ReturnObject> tbl_content;

    @FXML
    private TableColumn<ReturnObject,Float> clm_maxTemp;

    @FXML
    private TableColumn<ReturnObject,Float> clm_minTemp;

    @FXML
    private TableColumn<ReturnObject,String> clm_ortschaft;

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

            returnObjectArrayList = new ArrayList<>();
            logger.info ("Load View A05");
            List<String> selectedItems = UIToolbox.getCheckedItems(mbtn_ortschaften);
            if(!selectedItems.isEmpty()) {
                //lbl_nichtkomplett.setVisible(false);
                for (String ortschaft : selectedItems) {
                    logger.info("Loading data for: " + ortschaft);
                    int count = 0;
                    List<WeatherData> dataList = MainViewBorderPaneController.stub.findByDate(timestamp_start, timestamp_end, MainViewBorderPaneController.stub.findCityByName(ortschaft).getId());
                    for(WeatherData data : dataList){
                        if(count == 0 || data.getTemperatureInCelsius() > maxTemp){
                            maxTemp = data.getTemperatureInCelsius();
                        }
                        if(count == 0 || data.getTemperatureInCelsius() < minTemp){
                            minTemp = data.getTemperatureInCelsius();
                        }
                        count++;
                    }
                    ReturnObject object = new ReturnObject(ortschaft,maxTemp,minTemp);
                    returnObjectArrayList.add(object);
                    logger.info("Adding to object List: " + object);
                }
            }else{
                lbl_nichtkomplett.setText("Bitte Ortschaft auswählen");
                lbl_nichtkomplett.setVisible(true);
            }

            //Ortschaft
            clm_ortschaft.setSortType(TableColumn.SortType.ASCENDING);

            clm_ortschaft.setCellValueFactory(new PropertyValueFactory<>("city"));

            //Durchschnittstemperatur
            clm_maxTemp.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));

            //Durchschnittstemperatur
            clm_minTemp.setCellValueFactory(new PropertyValueFactory<>("minTemp"));

            //Content von Array laden
            tbl_content.getItems().setAll(returnObjectArrayList);
            tbl_content.getSortOrder().add(clm_ortschaft);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class ReturnObject implements CSVExportable {
        private String city;
        private float maxTemp;
        private float minTemp;

        public ReturnObject(String city, float maxTemp, float minTemp) {
            this.city = city;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        @Override
        public String toString() {
            return "ReturnObject{" +
                    "city='" + city + '\'' +
                    ", maxTemp=" + maxTemp +
                    ", minTemp=" + minTemp +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReturnObject that = (ReturnObject) o;
            return Float.compare(that.maxTemp, maxTemp) == 0 &&
                    Float.compare(that.minTemp, minTemp) == 0 &&
                    Objects.equals(city, that.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city, maxTemp, minTemp);
        }

        @Override
        public String toCSVString() {
            return city + "," + maxTemp + "," + minTemp + "\n";
        }
    }

    @FXML
    private void onExport() throws IOException {
        UIToolbox.csvExport(tbl_content, lbl_status);
    }
}
