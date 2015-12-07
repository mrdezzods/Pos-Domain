package domain;

import java.util.Properties;

import db.DBtypes;
import db.shoppingcart.ShoppingCartDbFactory;
import db.shoppingcart.ShoppingCartDbRepository;
import domain.discount.Discount;
import domain.discount.DiscountService;
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

	private ShoppingCartDbRepository repo;

	public ShoppingCartService(DBtypes type, Properties properties, DiscountService discountService) {
		ShoppingCartDbFactory factory = new ShoppingCartDbFactory(discountService);
		repo = factory.createShoppingCartDb(type, properties);
		counter = repo.getMaxId() + 1;
	}

	public ShoppingCart createCart(String userId) {
		ShoppingCart cart = new ShoppingCart(nextNumber(), userId);
		repo.add(cart);
		return cart;
	}

	public void removeCart(int cartId) {
		repo.delete(cartId);
	}

	public ShoppingCart getCart(int cartId) {
		return repo.get(cartId);
	}

	public ShoppingCart getCartFromUser(String userId) {
		for (ShoppingCart cart : repo.getAll()) {
			if (cart.getUserId().equals(userId))
				return cart;
		}
		return null;
	}

	public double getTotalPrice(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null)
			throw new IllegalArgumentException(
					"A cart with this ID doesn't exist");
		return cart.getTotalPrice();
	}

	public void addProduct(int cartId, ShoppingCartProduct prd) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null)
			throw new IllegalArgumentException(
					"A cart with this ID doesn't exist");
		cart.addProduct(prd);
	}

	public void addProductToCartFromUser(String userId, ShoppingCartProduct prd) {
		ShoppingCart cart = getCartFromUser(userId);
		if (cart == null)
			throw new IllegalArgumentException("This user doesn't have a cart");
		cart.addProduct(prd);
	}

	public int getTotalQty(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null)
			throw new IllegalArgumentException(
					"A cart with this ID doesn't exist");
		return cart.getTotalQty();
	}

	public int getTotalQtyFromUser(String userId) {
		ShoppingCart cart = getCartFromUser(userId);
		if (cart == null)
			throw new IllegalArgumentException("This user doesn't have a cart");
		return cart.getTotalQty();
	}

	public void setDiscount(int cartId, Discount discount) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null)
			throw new IllegalArgumentException(
					"A cart with this ID doesn't exist");
		cart.setDiscount(discount);
	}

	public String getDiscountCode(int cartId) {
		ShoppingCart cart = getCart(cartId);
		if (cart == null)
			throw new IllegalArgumentException(
					"A cart with this ID doesn't exist");
		return cart.getDiscountCode();
	}
}
