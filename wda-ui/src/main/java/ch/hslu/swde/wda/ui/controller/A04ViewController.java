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


public class A04ViewController {
    private static Logger logger = LogManager.getLogger(A04ViewController.class);
    private Timestamp timestamp_start;
    private Timestamp timestamp_end;
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
    private TableColumn<ReturnObject,Float> clm_avgTemp;

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
            logger.info ("Load View A03");
            List<String> selectedItems = UIToolbox.getCheckedItems(mbtn_ortschaften);
            if(!selectedItems.isEmpty()) {
                //lbl_nichtkomplett.setVisible(false);
                for (String ortschaft : selectedItems) {
                    logger.info("Loading data for: " + ortschaft);
                    //weatherDataArrayList.addAll(MainViewBorderPaneController.stub.findByDate(timestamp_start, timestamp_end, MainViewBorderPaneController.stub.findCityByName(ortschaft).getId()));
                    int count = 0;
                    float avgTemp = 0;
                    List<WeatherData> dataList = MainViewBorderPaneController.stub.findByDate(timestamp_start, timestamp_end, MainViewBorderPaneController.stub.findCityByName(ortschaft).getId());
                    for(WeatherData data : dataList){
                        avgTemp = avgTemp + data.getTemperatureInCelsius();
                    }
                    avgTemp = avgTemp / dataList.size();
                    ReturnObject object = new ReturnObject(ortschaft,avgTemp);
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
            clm_avgTemp.setCellValueFactory(new PropertyValueFactory<>("avgtemp"));

            //Content von Array laden
            tbl_content.getItems().setAll(returnObjectArrayList);
            tbl_content.getSortOrder().add(clm_ortschaft);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class ReturnObject implements CSVExportable {
        private String city;
        private float avgtemp;

        public ReturnObject(String city, float avgtemp){
            this.city = city;
            this.avgtemp = avgtemp;
        }


        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public float getAvgtemp() {
            return avgtemp;
        }

        public void setAvgtemp(float avgtemp) {
            this.avgtemp = avgtemp;
        }

        @Override
        public String toString() {
            return "ReturnObject{" +
                    "city='" + city + '\'' +
                    ", avgtemp=" + avgtemp +
                    '}';
        }

        @Override
        public String toCSVString() {
            return city + "," + avgtemp + "\n";
        }
    }
    @FXML
    private void onExport() throws IOException {
        UIToolbox.csvExport(tbl_content, lbl_status);
    }

}

