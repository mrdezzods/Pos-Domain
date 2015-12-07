package domain.shoppingcartproduct;

import java.util.List;
import java.util.Properties;

import db.DBtypes;
import domain.product.Product;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartProductService {

	public ShoppingCartProductService(DBtypes type, Properties properties) {
		// TODO Auto-generated constructor stub
	}

	public void addToCart(int cartId, Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	public List<ShoppingCartProduct> getProductsFromCart(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeFromCart(int cartId, int prodctId) {
		// TODO Auto-generated method stub
		
	}

	public ShoppingCartProduct getProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeProduct(int productId) {
		// TODO Auto-generated method stub
		
	}

	public void alterQty(int productId, int newQuantity) {
		// TODO Auto-generated method stub
		
	}

	public int getQtyFromCart(int cartId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalPriceFromCart(int cartId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
