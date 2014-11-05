package BBDD2.trabajo.service;

import java.util.HashSet;

import javax.naming.TimeLimitExceededException;

import BBDD2.trabajo.dao.MasterDAO;
import BBDD2.trabajo.model.Cart;
import BBDD2.trabajo.model.Site;
import BBDD2.trabajo.singleton.DAOFactory;
import BBDD2.trabajo.singleton.MasterContainer;

public class MasterService {

	private MasterContainer container;
	private MasterDAO masterDao;

	public MasterService() {
		this.container = MasterContainer.getInstance();
		this.masterDao = DAOFactory.getMasterDAO();
	}

	public String addCart(String siteToken, String userId) {
		String cart = this.container.getMaster().addCart(siteToken, userId);
		update();
		return cart;
	}

	public String getCart(String cartToken) throws TimeLimitExceededException {
		return this.container.getMaster().getCart(cartToken).toJSONObject()
				.toJSONString();
	}

	public void removeCart(String cartToken) {
		this.container.getMaster().removeCart(cartToken);
		update();
	}

	public String getProduct(String cartToken, String productId)
			throws TimeLimitExceededException {
		return this.container.getMaster().getCart(cartToken)
				.getProduct(productId).toJSONObject().toJSONString();
	}

	public boolean addProduct(String cartToken, String productId, Double price)
			throws TimeLimitExceededException {
		if (this.container.getMaster().getCart(cartToken)
				.addProduct(productId, price)) {
			update();
			return true;
		}
		return false;
	}

	public void updateProduct(String cartToken, String productId, Double price)
			throws TimeLimitExceededException {
		this.container.getMaster().getCart(cartToken)
				.updateProduct(productId, price);
		update();
	}

	public void updateProduct(String cartToken, String productId, Double price,
			int quantity) throws TimeLimitExceededException {
		this.container.getMaster().getCart(cartToken)
				.updateProduct(productId, quantity, price);
		update();
	}

	public void updateProduct(String cartToken, String productId, int quantity)
			throws TimeLimitExceededException {
		this.container.getMaster().getCart(cartToken)
				.updateProduct(productId, quantity);
		update();
	}

	public void deleteProduct(String cartToken, String productId)
			throws TimeLimitExceededException {
		this.container.getMaster().getCart(cartToken).deleteProduct(productId);
		update();
	}

	public boolean addSite(String sitename) {
		if (this.container.getMaster().addSite(sitename)) {
			update();
			return true;
		}
		return false;
	}

	public String getSite(String sitename) {
		return this.container.getMaster().getSiteToken(sitename);
	}

	public void deleteSite(String siteToken) {
		this.container.getMaster().removeSite(siteToken);
		update();
	}

	public void clear() {
		this.container.getMaster().setSites(new HashSet<Site>());
		this.container.getMaster().setCarts(new HashSet<Cart>());
		update();
	}

	public void setTime(Long millis) {
		this.container.getMaster().setTimeLimit(millis);
		update();
	}

	private void update() {
		this.masterDao.update(this.container.getMaster());
	}
}