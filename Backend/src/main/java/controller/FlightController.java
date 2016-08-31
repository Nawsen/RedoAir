package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("Flight")
public class FlightController {

    @GET
    public String getStringTest(){
        return "mathias is gaan kakken0";
    }

}
