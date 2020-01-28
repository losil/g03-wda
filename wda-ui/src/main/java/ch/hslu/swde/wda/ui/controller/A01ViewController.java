package ch.hslu.swde.wda.ui.controller;


import ch.hslu.swde.wda.business.control.impl.ControlImpl;
import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.ui.CSVExportable;
import ch.hslu.swde.wda.ui.UIToolbox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.ArrayList;


public class A01ViewController {
    private static Logger logger = LogManager.getLogger(A01ViewController.class);
    private ArrayList<ReturnObject> returnObjectArrayList;
    private ArrayList<City> cityArrayList;

    @FXML
    private TableView<ReturnObject> tbl_content;

    @FXML
    private TableColumn<ReturnObject,String> clm_ortschaft;

    @FXML
    private TableColumn<ReturnObject,Integer> clm_id;

    @FXML
    private Label lbl_status;

    ControlImpl control;


    @FXML
    protected void initialize(){

        try {
            returnObjectArrayList = new ArrayList<>();
            cityArrayList = new ArrayList<>();
            cityArrayList.addAll(MainViewBorderPaneController.stub.findAllCities());

            for(City city : cityArrayList){
                returnObjectArrayList.add(new ReturnObject(city.getName(),city.getId()));
            }

            logger.info ("Load View A01");
            clm_ortschaft.setSortType(TableColumn.SortType.ASCENDING);
            clm_ortschaft.setCellValueFactory(new PropertyValueFactory<>("city"));
            clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            tbl_content.getItems().setAll(returnObjectArrayList);

            tbl_content.getSortOrder().add(clm_ortschaft);

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
        private int id;

        public ReturnObject(String city, int id) {
            this.city = city;
            this.id = id;
        }


        @Override
        public String toCSVString() {
            return city + "," + id + "\n";
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
