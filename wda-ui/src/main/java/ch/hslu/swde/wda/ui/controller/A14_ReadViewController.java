package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A14_ReadViewController {
    private static Logger logger = LogManager.getLogger(A14_ReadViewController.class);
    @FXML
    private TableView tbl_content;

    @FXML
    private TableColumn clm_id;

    @FXML
    private TableColumn clm_firmenname;

    @FXML
    private TableColumn clm_strasse;

    @FXML
    private TableColumn clm_nummer;

    @FXML
    private TableColumn clm_plz;

    @FXML
    private TableColumn clm_ort;

    @FXML
    private TableColumn clm_ansprechpartner;

    @FXML
    private TableColumn clm_telefon;

    @FXML
    private TableColumn clm_lizenz;

    @FXML
    protected void initialize() {
        try {
            logger.info ("Load A14_ReadView");
            clm_id.setSortType(TableColumn.SortType.ASCENDING);
            clm_id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("kundenNummer"));
            clm_firmenname.setCellValueFactory(new PropertyValueFactory<Customer, String>("firmenname"));
            clm_strasse.setCellValueFactory(new PropertyValueFactory<Customer, String>("strasse"));
            clm_nummer.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("strassenBezeichnung"));
            clm_plz.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("plz"));
            clm_ort.setCellValueFactory(new PropertyValueFactory<Customer, String>("ort"));
            clm_ansprechpartner.setCellValueFactory(new PropertyValueFactory<Customer, String>("ansprechspartner"));
            clm_telefon.setCellValueFactory(new PropertyValueFactory<Customer, String>("telefon"));
            clm_lizenz.setCellValueFactory(new PropertyValueFactory<Customer, String>("lizenzart"));

            tbl_content.getItems().setAll(MainViewBorderPaneController.stub.allCustomer());

            tbl_content.getSortOrder().add(clm_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
