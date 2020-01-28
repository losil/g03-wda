package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.domain.Operator;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class A13_ReadViewController {
    private static Logger logger = LogManager.getLogger(A13_ReadViewController.class);
    @FXML
    private TableView tbl_content;

    @FXML
    private TableColumn clm_ortschaft;

    @FXML
    private TableColumn clm_id;

    @FXML
    private TableColumn clm_name;

    @FXML
    private TableColumn clm_vorname;

    @FXML
    private TableColumn clm_benutzername;



    @FXML
    protected void initialize() {
        try {
            logger.info ("Load A13_ReadView");
            clm_id.setSortType(TableColumn.SortType.ASCENDING);
            clm_id.setCellValueFactory(new PropertyValueFactory<Operator, Integer>("personalnummer"));
            clm_name.setCellValueFactory(new PropertyValueFactory<Operator, String>("name"));
            clm_vorname.setCellValueFactory(new PropertyValueFactory<Operator, String>("vorname"));
            clm_benutzername.setCellValueFactory(new PropertyValueFactory<Operator, String>("username"));

            tbl_content.getItems().setAll(MainViewBorderPaneController.stub.allOperator());

            tbl_content.getSortOrder().add(clm_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
