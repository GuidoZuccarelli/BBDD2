package BBDD2.trabajo.model;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Master {

private long id;
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public void setCarts(Set<Cart> carts) {
	this.carts = carts;
}

public void setSites(Set<Site> sites) {
	this.sites = sites;
}

private Set<Cart> carts;
private Set<Site> sites;

public Master(){
    this.carts = new HashSet<Cart>();
    this.sites = new HashSet<Site>();
}

public Set<Cart> getCarts() {
    return carts;
  }

  public void addCart(Cart cart) {
    getCarts().add(cart);
  }
  
  public void addCarts(HashSet<Cart> carts) {
	    this.carts = carts;
  }
  
  public void removeCart(Cart cart) {
    getCarts().remove(cart);
  }
  
  public Set<Site> getSites() {
    return sites;
  }
  
  public boolean addSite(String name) {
	  for (Site site : this.sites)
		  if(site.getName().equals(name))
			  return false;
	  sites.add(new Site(name));
	  return true;
  }
  public void addSites(HashSet<Site> sites) {
	    this.sites = sites;
}
  
  public String getSiteToken(String name){
	  for (Site site : this.sites)
		  if(site.getName().equals(name)){
			  return site.getToken();
		  }
	  throw new NoSuchElementException();
  }
  
  public void removeSite(String token) {
	  for (Site site : this.sites)
		  if(site.getToken().equals(token)){
			  this.sites.remove(site);
			  return;
		  }
	  throw new NoSuchElementException();
  }

  public Site getSite(String token){
	  for (Site site : this.sites)
		  if(site.getToken().equals(token))
			  return site;
	  throw new NoSuchElementException();
  }
  
  public String addCart(String token, String userId){
	  Cart cart = new Cart(userId, this.getSite(token));
	  this.carts.add(cart);
	  return cart.getToken();
  }
  
  
  public Cart getCart(String token){
	  for (Cart cart : this.carts)
		  if(cart.getToken().equals(token))
			  return cart;
	  throw new NoSuchElementException();
  }
  
  public void removeCart(String token) {
	  for (Cart cart : this.carts)
		  if(cart.getToken().equals(token)){
			  this.carts.remove(cart);
			  return;
		  }
	  throw new NoSuchElementException();
  }
}
