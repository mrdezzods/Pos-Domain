package domain.shoppingcartproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import db.DBtypes;
import db.shoppingcartproduct.ShoppingCartProductLocalRepository;
import domain.product.Product;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartProductService {
	
	private ShoppingCartProductLocalRepository repo;

	public ShoppingCartProductService(DBtypes type, Properties properties) {
		repo = new ShoppingCartProductLocalRepository();
	}

	public void addToCart(int cartId, Product product, int quantity) {
		ShoppingCartProduct p = new ShoppingCartProduct(product, quantity, cartId);
		repo.add(p);
	}

	public List<ShoppingCartProduct> getProductsFromCart(int cartId) {
		List<ShoppingCartProduct> list = new ArrayList<>();
		for (ShoppingCartProduct product : repo.getAll())
			if (product.getCartId() == cartId)
				list.add(product);
		return list;
	}

	public ShoppingCartProduct getProduct(int productId) {
		return repo.get(productId);
	}

	public void removeProduct(int productId) {
		repo.delete(productId);
	}

	public void alterQty(int productId, int newQuantity) {
		repo.get(productId).setQty(newQuantity);
	}

	public int getQtyFromCart(int cartId) {
		int qty = 0;
		for (ShoppingCartProduct product : getProductsFromCart(cartId))
			qty += product.getQty();
		return qty;
	}

	public double getTotalPriceFromCart(int cartId) {
		double sum = 0;
		for (ShoppingCartProduct product : getProductsFromCart(cartId))
			sum += product.getQty();
		return sum;
	}

}
