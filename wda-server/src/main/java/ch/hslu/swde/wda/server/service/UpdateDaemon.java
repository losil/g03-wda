package ch.hslu.swde.wda.server.service;

import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.business.control.impl.ControlImpl;
import ch.hslu.swde.wda.business.init.DataParser;
import ch.hslu.swde.wda.business.init.RestClient;
import ch.hslu.swde.wda.domain.RestWdaData;
import ch.hslu.swde.wda.domain.WeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

public class UpdateDaemon extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(UpdateDaemon.class);
    private final String url;

    public UpdateDaemon(final String url) {
        this.url = url;
        setDaemon(true);
        setName("UpdateDaemon-Thread");
    }

    public void run() {
        Control control = null;
        try {
            control = new ControlImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        RestClient restClient = new RestClient(this.url);
        DataParser dataParser = new DataParser();

        while (true) {

            try {
                // Updating data every 15 minutes
                sleep(1000 * 60 * 15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (RestWdaData r : restClient.getDayWeatherData()
            ) {
                try {
                    WeatherData w = dataParser.parseRestWdaData(r);
                    control.addWeatherData(w);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info("Weather Data was updated!");
        }
    }
}
