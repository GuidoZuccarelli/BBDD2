package BBDD2.trabajo.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import BBDD2.trabajo.service.MasterService;
import BBDD2.trabajo.singleton.ServiceFactory;

@Path("/utils")
public class UtilsRestfulAPI {

	MasterService service;

	public UtilsRestfulAPI() {
		this.service = ServiceFactory.getMasterService();
	}

	@POST
	public Response setUp() {
		this.service.addSite("exampleSite");
		return Response.accepted().build();
	}

	@DELETE
	public Response tearDown() {
		this.service.clear();
		return Response.accepted().build();
	}

	@PUT
	@Path("/utils/time/{timeLimit}")
	public Response setTime(@PathParam("timeLimit") Long millis) {
		this.service.setTime(millis);
		return Response.accepted().build();
	}
}