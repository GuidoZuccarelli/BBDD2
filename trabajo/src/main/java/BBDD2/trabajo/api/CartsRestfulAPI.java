package BBDD2.trabajo.api;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import BBDD2.trabajo.model.Cart;
import BBDD2.trabajo.singleton.MasterContainer;

@Path("/carts")
public class CartsRestfulAPI {

	MasterContainer container;
	
	public CartsRestfulAPI(){
		this.container = MasterContainer.getInstance();
	}

	@POST
	@Path("/{carttoken}/products/{productid}")
	public Response addCart(@PathParam("carttoken") String cartToken, @PathParam("productId") String productId, @QueryParam("price") Long price){
		Cart cart;
		try {
			cart = this.container.getMaster().getCart(cartToken);
		}catch (NoSuchElementException e){
			return Response.status(402).entity("Cart token doesn't exists").build();
		}
		if (price != null){
			if (price < 0)
				return Response.status(401).entity("Price cannot be negative").build();
			else
				if (cart.addProduct(productId, price))
					return Response.ok().build();
				else 
					return Response.status(412).entity("Product already exists").build();
		}
		return Response.status(400).entity("The price of the product is needed").build();
	}
	
	@PUT
	@Path("/{carttoken}/products/{productid}")
	public Response updateProduct(@PathParam("carttoken") String cartToken, @PathParam("productId") String productId, @QueryParam("price") Long price, @QueryParam("quantity") Integer quantity){
		Cart cart;
		try {
			cart = this.container.getMaster().getCart(cartToken);
		}catch (NoSuchElementException e){
			return Response.status(402).entity("Cart not found").build();
		}
		if (price != null){
			if (price < 0)
				return Response.status(401).entity("Price cannot be negative").build();
			else 
				if(quantity != null)
					try{
						cart.updateProduct(productId, price);
						return Response.ok().build();
					}catch(NoSuchElementException e){
						return Response.status(400).entity("Product not found").build();
					}
				else
					try{
						cart.updateProduct(productId, quantity, price);
						return Response.ok().build();
					}catch(NoSuchElementException e){
						return Response.status(400).entity("Product not found").build();
					}
		}
		return Response.status(400).entity("The price of the product is needed").build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{carttoken}")
	public Response getCart(@PathParam("carttoken") String cartToken){
		try{
			return Response.ok().entity(this.container.getMaster().getCart(cartToken).toJSONObject().toJSONString()).build();
		}catch(NoSuchElementException e){
			return Response.status(400).entity("Product not found").build();
		}
	}
}