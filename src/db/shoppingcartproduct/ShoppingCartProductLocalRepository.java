package db.shoppingcartproduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.shoppingcartproduct.ShoppingCartProduct;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartProductLocalRepository implements ShoppingCartProductDbRepository {
	private Map<Integer, ShoppingCartProduct> products;

	public ShoppingCartProductLocalRepository() {
		products = new HashMap<>();
	}

	public ShoppingCartProduct get(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0");
		}
		return products.get(id);
	}

	public List<ShoppingCartProduct> getAll() {
		return new ArrayList<ShoppingCartProduct>(products.values());
	}

	public void add(ShoppingCartProduct product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		if (products.containsKey(product.getId())) {
			throw new IllegalArgumentException("Product already exists");
		}
		products.put(product.getId(), product);
	}

	public void update(ShoppingCartProduct product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		products.put(product.getId(), product);
	}

	public void delete(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0");
		}
		products.remove(id);
	}

	@Override
	public int getMaxId() {
		int max = 0;
		for (int i : products.keySet())
			if (i > max)
				max = i;
		return max;
	}
}
