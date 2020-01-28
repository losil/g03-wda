package ch.hslu.swde.wda.rest.ws;


import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.business.control.impl.ControlImpl;
import ch.hslu.swde.wda.domain.WeatherData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("weatherdata/")
public class WeatherDataRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{city}")
    public Response weatherDataByCity(@PathParam("city") String city) {
        Control control = null;
        try {
            control = new ControlImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        /*
        Return NOT FOUND if city is not found
         */
        try {
            if (control.findCityByName(city) == null) {
                return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        Generating List with WeatherData of City
         */
        List<WeatherData> weatherDataList = null;

        try {
            //weatherDatalist =
            weatherDataList = control.getLast24HoursData(control.findCityByName(city).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        Sorting the returning list with an inline comparator for WeatherData
         */
        Collections.sort(weatherDataList, new Comparator<WeatherData>() {
            @Override
            public int compare(WeatherData o1, WeatherData o2) {
                if (o1.getId() == o2.getId()) {
                    return 0;
                } else if (o1.getId() > o2.getId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        GenericEntity<List<WeatherData>> entity = new GenericEntity<List<WeatherData>>(weatherDataList) {
        };
        return Response.ok(entity).build();
    }

}
