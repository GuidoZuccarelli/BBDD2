package BBDD2.trabajo.model;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Cart {

	private long id;
	private String userId;
	private String token;
	private long creationTime;
	private Set<Product> products;
	private Site site;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Site getSite() {
		return site;
	}

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

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Cart(String userId, Site site) {
		super();
		this.userId = userId;
		this.token = UUID.randomUUID().toString();
		this.creationTime = System.currentTimeMillis();
		this.products = new HashSet<Product>();
		this.site = site;
	}

	public Cart() {
		super();
		this.token = UUID.randomUUID().toString();
		this.creationTime = System.currentTimeMillis();
		this.products = new HashSet<Product>();
	}

	public boolean addProduct(String productId, double price) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId))
				return false;
		products.add(new Product(productId, 1, price));
		return true;
	}

	public void addProducts(Set<Product> products) {
		this.products = products;
	}

	public Product getProduct(String productId) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId))
				return product;
		throw new NoSuchElementException();
	}

	public void deleteProduct(String productId) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId)) {
				this.products.remove(product);
				return;
			}
		throw new NoSuchElementException();
	}

	public void removeProduct(String productId, int quantity) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId)) {
				product.setQuantity(product.getQuantity() - quantity);
				return;
			}
		throw new NoSuchElementException();
	}

	public void updateProduct(String productId, int quantity, double price) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId)) {
				product.setQuantity(product.getQuantity() + quantity);
				product.setPrice(price);
				return;
			}
		throw new NoSuchElementException();
	}

	public void updateProduct(String productId, int quantity) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId)) {
				product.setQuantity(product.getQuantity() + quantity);
				return;
			}
		throw new NoSuchElementException();
	}

	public void updateProduct(String productId, double price) {
		for (Product product : this.products)
			if (product.getProductId().equals(productId)) {
				product.setPrice(price);
				return;
			}
		throw new NoSuchElementException();
	}

	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		object.put("userid", this.userId);
		object.put("siteurl", this.site.getName());
		JSONArray products = new JSONArray();
		double sum = 0;
		for (Product product : this.products) {
			products.add(product.toJSONObject());
			sum += product.getPrice();
		}
		object.put("products", products);
		object.put("total", String.valueOf(sum));
		return object;
	}
}