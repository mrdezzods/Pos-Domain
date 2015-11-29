package domain;

import java.util.HashMap;
import java.util.Map;

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
}
