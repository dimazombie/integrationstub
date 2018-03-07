package com.netcracker.edu.sat.stub.rest;

import com.netcracker.edu.sat.stub.helpers.DemoHelper;
import com.netcracker.edu.sat.stub.model.DemoInObject;
import com.netcracker.edu.sat.stub.model.DemoOutObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemoResource {
    @POST
    public DemoOutObject handle(DemoInObject in) {
        return new DemoOutObject(in.getRequest_id(),
                in.getTransaction_type(),
                DemoHelper.DateTimeFormatter.get().format(new Date()),
                in.getTarget(),
                in.getSource(),
                in.getOrder_number());
    }
}
