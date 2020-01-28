package ch.hslu.swde.wda.ui;





import ch.hslu.swde.wda.business.control.api.Control;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.TimeZone;


public class WdaUI extends Application {

    private static final Logger logger = LogManager.getLogger(WdaUI.class);



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws java.io.IOException {

        try {

            stage.setTitle("WDA App - g03");
            stage.setResizable(false);


            BorderPane root =  FXMLLoader.load(getClass().getResource("/ch/hslu/swde/wda/ui/view/MainViewBorderPane.fxml"));
            Scene scene = new Scene(root, 950, 500);
            scene.getStylesheets().add(getClass().getResource("/ch/hslu/swde/wda/ui/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            logger.error(">> Fehler: ", e);
            System.out.println("Die Applikation wurde aus einem unbekannten Grund heruntergefahren.");

        }
    }

}
