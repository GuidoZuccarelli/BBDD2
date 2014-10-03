package modelo;

import java.util.Timer;

public class Cart {
	private long id;
	private int userId;
	private String token;
	private Timer creationTime;
	private Product products;
	private Site site;
	
	public Cart(){
		products = new Product();
		site = new Site();
	}
}
