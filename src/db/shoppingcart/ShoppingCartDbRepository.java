package db.shoppingcart;

import java.util.List;

import domain.ShoppingCart;

/**
 * 
 * @author Milan Sanders
 *
 */
public interface ShoppingCartDbRepository {
	ShoppingCart get(int id);
	List<ShoppingCart> getAll();
	void add(ShoppingCart cart);
	void update(ShoppingCart cart);
	void delete(int id);
	int getMaxId();
}
