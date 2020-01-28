package ch.hslu.swde.wda.ui.controller;

import ch.hslu.swde.wda.business.control.api.Control;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.Naming;

public class MainViewBorderPaneController {
    private static Logger logger = LogManager.getLogger(MainViewBorderPaneController.class);



    private static String policyFileName;
    public static Control stub;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_benutzername;

    @FXML
    private Label lbl_errormessage;

    @FXML
    private TextField txt_serverIP;

    @FXML
    private PasswordField pwd_passwort;


    @FXML
    public void onLogin() throws Exception{

       if(connect(txt_serverIP.getText())) {

           try {

               String user = stub.findOperatorByUsername(txt_benutzername.getText()).getUsername();
               String password = stub.findOperatorByUsername(txt_benutzername.getText()).getPasswort();

               logger.info("user from Interface: " + txt_benutzername.getText() + " pw from Interface: "  + pwd_passwort.getText());
               logger.info("user from DB: " + user + " pw from DB:" + password);
               if(txt_benutzername.getText().equals(user)){

                   logger.info("try to login with: " + user);
                   if(pwd_passwort.getText().equals(password)){
                       logger.info("login successfull with: " + user);
                       switchView();
                   }else{
                       logger.warn("Wrong password with user: " + user);
                       lbl_errormessage.setText("Falsches Passwort!");
                   }
               }
               else{
                   logger.warn("Wrong username: " + user);
                   lbl_errormessage.setText("Benutzername falsch!");
               }
           }catch(Exception e){
               lbl_errormessage.setText("Benutzername oder Passwort falsch!");
               logger.error("unable to find user or pw on Server");
               logger.error(e);
           }

         }else{

           logger.warn("RMI client was not able to connect!");
           lbl_errormessage.setText("Verbindung fehlgeschlagen!");

       }


    }

    private  boolean connect(String serverIP){
        try {
            if (System.getSecurityManager() != null) {
                System.setProperty("java.security.policy", "wda.policy");
                System.setSecurityManager(new SecurityManager());
            }

            // read the ip address and the port number from the UI (if desired)
            String rmiServerIp = serverIP.trim();
            logger.info("RMI IP: " + rmiServerIp);

            // Create RMI url
            int rmiPort = 1099;
            String url = "rmi://" + rmiServerIp + ":" + rmiPort + "/" + Control.RO_NAME;
            logger.info("RMI URL: " + url);
            stub = (Control) Naming.lookup(url);
            logger.info("stub received");
            logger.info (stub.findAllCities().get(0).getName());

            return true;
        }
        catch (Exception e) {
        logger.error(e.getMessage(), e);
        //txt_errormsg.setText("Verbindungsversucht fehlgeschlagen!");
        return false;
        }
    }


    private void switchView(){
        try {
            AnchorPane menu = FXMLLoader.load(getClass().getResource("/ch/hslu/swde/wda/ui/view/MenuBar.fxml"));
            BorderPane root = (BorderPane) Stage.getWindows().get(0).getScene().getRoot();
            root.setTop(menu);

            AnchorPane center = FXMLLoader.load(getClass().getResource("/ch/hslu/swde/wda/ui/view/Welcome.fxml"));
            root.setCenter(center);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
