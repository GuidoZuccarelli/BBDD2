package modelo;

import java.util.Timer;

public class Cart {
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
