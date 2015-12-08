package db.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.shoppingcart.ShoppingCart;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartLocalRepository implements ShoppingCartDbRepository {
	
	private Map<Integer, ShoppingCart> carts = new HashMap<>();

	@Override
	public ShoppingCart get(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0");
		}
		return carts.get(id);
	}

	@Override
	public List<ShoppingCart> getAll() {
		return new ArrayList<ShoppingCart>(carts.values());
	}

	@Override
	public void add(ShoppingCart cart) {
		if (cart == null) {
			throw new IllegalArgumentException("No cart given");
		}
		if (carts.containsKey(cart.getId())) {
			throw new IllegalArgumentException("Cart already exists");
		}
		carts.put(cart.getId(), cart);
	}

	@Override
	public void update(ShoppingCart cart) {

		if (cart == null) {
			throw new IllegalArgumentException("No cart given");
		}
		carts.put(cart.getId(), cart);
	}

	@Override
	public void delete(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0");
		}
		carts.remove(id);
	}

	@Override
	public int getMaxId() {
		int max = 0;
		for (int i : carts.keySet())
			if (i > max)
				max = i;
		return max;
	}

}
