package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import domain.product.Product;
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

	/**
	 * 
	 * @param productPosition The position of the product in the list of products
	 * @param newQuantity The new quantity it will be
	 */
	public void alterProduct(int productPosition, int newQuantity) {
		ShoppingCartProduct product = products.get(productPosition);
		if (newQuantity == 0) {
			products.remove(productPosition);
		} else {
			product.setQty(newQuantity);
		}
	}
	
	/**
	 * 
	 * @param productId The id of the ShoppingCqrtProduct to delete
	 * @param newQuantity
	 */
	public void alterProductWithId(int productId, int newQuantity) {
		ShoppingCartProduct product = null;
		for (ShoppingCartProduct p : products)
			if (p.getId() == productId)
				product = p;
		if (product == null)
			throw new IllegalArgumentException("No product with this ID exists");
		if (newQuantity == 0) {
			products.remove(product);
		} else {
			product.setQty(newQuantity);
		}
	}

	public void reportChanges() {
		setChanged();
		notifyObservers();
	}

	public void alterProduct(Product product, int quantity) {
		alterProduct(products.indexOf(product), quantity);
	}
}
