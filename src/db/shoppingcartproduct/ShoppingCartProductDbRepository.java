package db.shoppingcartproduct;

import java.util.List;

import domain.shoppingcartproduct.ShoppingCartProduct;

/**
 * 
 * @author Milan Sanders
 *
 */
public interface ShoppingCartProductDbRepository {
	ShoppingCartProduct get(int id);
	List<ShoppingCartProduct> getAll();
	void add(ShoppingCartProduct product);
	void update(ShoppingCartProduct product);
	void delete(int id);
	int getMaxId();	
}
