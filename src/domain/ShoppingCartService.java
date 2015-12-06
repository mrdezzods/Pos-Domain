package domain;

import java.util.HashMap;
import java.util.Map;

import domain.discount.Discount;
import domain.product.Product;
import domain.product.ShoppingCartProduct;

/**
 * Manages shopping carts that belong to actual people. Anonymous carts are
 * volatile and should be kept by the object that asks for their creation
 */
public class ShoppingCartService {

	private static int counter = 0;

	private static int nextNumber() {
		return counter++;
	}

	private Map<Integer, ShoppingCart> carts; // maps userIds to carts

	public ShoppingCartService() {
		carts = new HashMap<>();
	}

	public ShoppingCart createCart(String userId) {
		ShoppingCart cart = new ShoppingCart(nextNumber(), userId);
		int cartId = cart.getId();

		carts.put(cartId, cart);

		return cart;
	}

	public void removeCart(int cartId) {

		carts.remove(cartId);
	}

	public ShoppingCart getCart(int cartId) {
		return carts.get(cartId);
	}

	public ShoppingCart getCartFromUser(String userId) {
		for (ShoppingCart cart : carts.values()) {
			if (cart.getUserId().equals(userId))
				return cart;
		}
		return null;
	}

	public double getTotalPrice(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null) throw new IllegalArgumentException("A cart with this ID doesn't exist");
		return cart.getTotalPrice();
	}

	public void addProduct(int cartId, ShoppingCartProduct prd) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null) throw new IllegalArgumentException("A cart with this ID doesn't exist");
		cart.addProduct(prd);
	}

	public void addProductToCartFromUser(String userId, ShoppingCartProduct prd) {
		ShoppingCart cart = getCartFromUser(userId);
		if (cart == null) throw new IllegalArgumentException("This user doesn't have a cart");
		cart.addProduct(prd);
	}

	public int getTotalQty(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null) throw new IllegalArgumentException("A cart with this ID doesn't exist");
		return cart.getTotalQty();
	}

	public int getTotalQtyFromUser(String userId) {
		ShoppingCart cart = getCartFromUser(userId);
		if (cart == null) throw new IllegalArgumentException("This user doesn't have a cart");
		return cart.getTotalQty();
	}

	public void setDiscount(int cartId, Discount discount) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null) throw new IllegalArgumentException("A cart with this ID doesn't exist");
		cart.setDiscount(discount);
	}

	public String getDiscountCode(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null) throw new IllegalArgumentException("A cart with this ID doesn't exist");
		return cart.getDiscountCode();
	}
}
