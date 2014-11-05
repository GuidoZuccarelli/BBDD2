package BBDD2.trabajo.beans;

import org.json.simple.JSONObject;

public class Product {
	private long id;
	private String productId;
	private int quantity;
	private double price;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity > 0 ? quantity : 0;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product() {
		super();
	}
	public Product(String productId, int quantity, double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Product(String productId, double price) {
		super();
		this.productId = productId;
		this.quantity = 0;
		this.price = price;
	}
	
	public JSONObject toJSONObject(){
		JSONObject object = new JSONObject();
		object.put("productid", this.productId);
		object.put("price", String.valueOf(this.price));
		object.put("quantity", String.valueOf(this.quantity));
		return object;
	}
}
