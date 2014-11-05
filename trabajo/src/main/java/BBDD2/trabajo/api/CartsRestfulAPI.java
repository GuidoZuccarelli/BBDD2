package BBDD2.trabajo.api;

import java.util.NoSuchElementException;

import javax.naming.TimeLimitExceededException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.MasterService;
import BBDD2.trabajo.singleton.ServiceFactory;

@Path("/carts")
public class CartsRestfulAPI {

	private MasterService service;
	
	public CartsRestfulAPI(){
		this.service = ServiceFactory.getMasterService();
	}
	
	@POST
	@Path("/{carttoken}/products/{productid}")
	public Response addProduct(@PathParam("carttoken") String cartToken, @PathParam("productid") String productId, @QueryParam("price") Double price){
		try {
			if (price != null){
				if (price < 0)
					return Response.status(401).entity("Price cannot be negative").build();
				else
					if (this.service.addProduct(cartToken, productId, price))
						return Response.status(201).build();
					else 
						return Response.status(412).entity("Product already exists").build();
			}
			return Response.status(400).entity("The price of the product is needed").build();
		}catch (NoSuchElementException e){
			return Response.status(402).entity("Cart token doesn't exists").build();
		}catch (TimeLimitExceededException g){
			return Response.status(403).entity("Cart time limit exceed").build();
		}
	}
	
	@PUT
	@Path("/{carttoken}/products/{productid}")
	public Response updateProduct(@PathParam("carttoken") String cartToken, @PathParam("productid") String productId, @QueryParam("price") Double price, @QueryParam("quantity") Integer quantity){
		try {
			if (price != null){
				if (price < 0)
					return Response.status(401).entity("Price cannot be negative").build();
				else 
					if(quantity == null){
							this.service.updateProduct(cartToken,productId, price);
							return Response.ok().build();
					}
					else{
							this.service.updateProduct(cartToken, productId, price, quantity);
							return Response.ok().build();
						}
			}
			if(quantity == null)
				quantity = 1;
			this.service.updateProduct(cartToken, productId, quantity);
			return Response.ok().build();
		}catch (NoSuchElementException e){
			return Response.status(402).entity("Cart not found").build();
		}catch (TimeLimitExceededException g){
			return Response.status(403).entity("Cart time limit exceed").build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{carttoken}")
	public Response getCart(@PathParam("carttoken") String cartToken){
		try{
			return Response.ok().entity(this.service.getCart(cartToken)).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Cart not found").build();
		}catch (TimeLimitExceededException g){
			return Response.status(403).entity("Cart time limit exceed").build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{carttoken}/products/{productid}")
	public Response getCart(@PathParam("carttoken") String cartToken, @PathParam("productid") String productId){
		try{
			return Response.ok().entity(this.service.getProduct(cartToken, productId)).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Product not found").build();
		}catch (TimeLimitExceededException g){
			return Response.status(403).entity("Cart time limit exceed").build();
		}
	}
	
	@DELETE
	@Path("/{carttoken}")
	public Response deleteCart(@PathParam("carttoken") String cartToken){
		try{
			this.service.removeCart(cartToken);
			return Response.status(204).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Cart not found").build();
		}
	}
	
	@DELETE
	@Path("/{carttoken}/products/{productid}")
	public Response deleteProduct(@PathParam("carttoken") String cartToken, @PathParam("productid") String productId){
		try{
			this.service.deleteProduct(cartToken, productId);
			return Response.status(204).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Product not found").build();
		}catch (TimeLimitExceededException g){
			return Response.status(403).entity("Cart time limit exceed").build();
		}
	}
}