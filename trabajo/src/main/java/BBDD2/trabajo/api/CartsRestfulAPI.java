package BBDD2.trabajo.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/carts")
public class CartsRestfulAPI {

	public CartsRestfulAPI(){
		//initialize
	}

	@POST
	@Path("/{sitename}")
	public Response addSite(@PathParam("sitename") String name){
		return null;
		
	}
}
