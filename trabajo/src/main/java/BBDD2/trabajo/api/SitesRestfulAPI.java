package BBDD2.trabajo.api;

import java.util.NoSuchElementException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import service.MasterService;
import BBDD2.trabajo.singleton.ServiceFactory;


@Path("/sites")
public class SitesRestfulAPI {

	private MasterService service;
	
	public SitesRestfulAPI(){
		this.service = ServiceFactory.getMasterService();
	}
	
	@POST
	@Path("/{sitename}")
	public Response addSite(@PathParam("sitename") String name){
		if (this.service.addSite(name))
			return Response.status(201).build();
		return Response.status(412).entity("Sitename already exists").build();
	}
	
	@POST
	@Path("/{sitetoken}/carts/{userid}")
	public Response addCart(@PathParam("sitetoken") String sitetoken, @PathParam("userid") String userId){
		try{
			return Response.status(201).entity(this.service.addCart(sitetoken, userId)).build();
		}catch (NoSuchElementException e){
			return Response.status(412).entity("Site token doesn't exists").build();
		}
	}
	
	@GET
	@Path("/{sitename}")
	public Response getSite(@PathParam("sitename") String name){
		try {
			return Response.ok(this.service.getSite(name)).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Sitename not found").build();
		}
	}
	
	@DELETE
	@Path("/{sitetoken}")
	public Response deleteSite(@PathParam("sitetoken") String token){
		try {
			this.service.deleteSite(token);
			return Response.ok().build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Site not found").build();
		}
	}
}
