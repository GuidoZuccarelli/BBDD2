package BBDD2.trabajo.api;

import java.util.HashSet;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import BBDD2.trabajo.model.Cart;
import BBDD2.trabajo.model.Site;
import BBDD2.trabajo.singleton.MasterContainer;

@Path("/utils")
public class UtilsAPI {

	MasterContainer container;
	
	public UtilsAPI(){
		this.container = MasterContainer.getInstance();
	}

	@POST
	public Response setUp(){
		this.container.getMaster().addSite("exampleSite");
		return Response.accepted().build();
	}
	
	@DELETE
	public Response tearDown(){
		this.container.getMaster().setSites(new HashSet<Site>());
		this.container.getMaster().setCarts(new HashSet<Cart>());
		return Response.accepted().build();
	}
}
