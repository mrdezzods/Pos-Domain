package db.shoppingcart;

import java.util.Properties;

import db.DBtypes;
import domain.discount.DiscountService;
import domain.shoppingcartproduct.ShoppingCartProductService;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartDbFactory {
	private final DiscountService discountService;
	private final ShoppingCartProductService shoppingCartProductService;
	
	public ShoppingCartDbFactory(DiscountService discountService, ShoppingCartProductService shoppingCartProductService) {
		this.discountService = discountService;
		this.shoppingCartProductService = shoppingCartProductService;
	}

	public ShoppingCartDbRepository createShoppingCartDb(DBtypes type,
			Properties properties) {
		ShoppingCartDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new ShoppingCartSQLRepository(properties, discountService, shoppingCartProductService);
			break;
		case LOCALDB:
			db = new ShoppingCartLocalRepository();
			break;
		}
		return db;
	}
}
