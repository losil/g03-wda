package ch.hslu.swde.wda.rest;

import ch.hslu.swde.wda.rest.ws.CityRessource;
import ch.hslu.swde.wda.rest.ws.WeatherDataRessource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        /*
         * Setting TimeZone for correct visualisation for date.
         */
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        /*
        Register the classes as a ResourceClass
         */
        register(CityRessource.class);
        register(WeatherDataRessource.class);
    }
}
