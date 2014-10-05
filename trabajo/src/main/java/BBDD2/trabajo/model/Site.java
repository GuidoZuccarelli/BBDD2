package BBDD2.trabajo.model;

import java.util.UUID;

public class Site {
	private long id;
	private String name;
	private String token;
	
	
	public Site(String name) {
		super();
		this.name = name;
		this.token = UUID.randomUUID().toString();
	}

	
	
	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
