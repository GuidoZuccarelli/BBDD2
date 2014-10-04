package modelo;

import java.util.Timer;

public class Cart {
	
	private long id;
	private String userId;
	private String token;
	private Timer creationTime;
	private Product products;
	private Site site;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timer getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timer creationTime) {
		this.creationTime = creationTime;
	}
	
	public Cart(){
		products = new Product();
		site = new Site();
	}
	
	  /**
	   * Creates a cart with a user and a site assigned.
	   * The cart will have an unique token.
	   */
	
	  public Cart(String user, Site site) {
		    this();
		    this.userId = user;
		    this.site = site;
	  }
	  
	  
	
}
