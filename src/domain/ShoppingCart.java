package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import domain.product.ShoppingCartProduct;

public class ShoppingCart extends Observable {

	private final String userId; // can be null
	private final int id;
	private List<ShoppingCartProduct> products;

	public ShoppingCart(int id, String userid) {
		this.userId = userid;
		this.id = id;
		products = new ArrayList<>();

	}

	public String getUserId() {
		return this.userId;
	}

	public void addProduct(ShoppingCartProduct product) {
		products.add(product);
		this.setChanged();
		this.notifyObservers();
	}

	public double getTotalPrice() {
		double sum = 0.0;
		for (ShoppingCartProduct product : this.products) {
			sum += product.getTotal();
		}
		return sum;
	}

	public int getTotalQty() {
		int count = 0;
		for (ShoppingCartProduct product : this.products) {
			count += product.getQty();
		}
		return count;
	}

	public int getId() {
		return this.id;
	}

	public List<ShoppingCartProduct> getProducts() {
		return this.products;
	}
}
