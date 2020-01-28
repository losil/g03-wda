package ch.hslu.swde.wda.rest.ws;

import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.business.control.impl.ControlImpl;
import ch.hslu.swde.wda.domain.City;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("weatherdata/cities")
public class CityRessource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response allCities() {
        Control control = null;
        try {
            control = new ControlImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        List<City> cityList = null;
        try {
            cityList = control.findAllCities();
            /*
            Inline Comparator for sorting
             */
            Collections.sort(cityList, new Comparator<City>() {
                @Override
                public int compare(City o1, City o2) {
                    if (o1.getId() == o2.getId()) {
                        return 0;
                    } else if (o1.getId() > o2.getId()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        GenericEntity<List<City>> entity = new GenericEntity<List<City>>(cityList) {
        };
        /*
        This founction should always return some values, so a response.ok() can be sent
         */
        return Response.ok(entity).build();
    }
}
