package db.shoppingcart;

import java.util.Properties;

import db.DBtypes;
import domain.discount.DiscountService;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartDbFactory {
	private final DiscountService discountService;
	
	public ShoppingCartDbFactory(DiscountService discountService) {
		this.discountService = discountService;
	}

	public ShoppingCartDbRepository createShoppingCartDb(DBtypes type,
			Properties properties) {
		ShoppingCartDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new ShoppingCartSQLRepository(properties, discountService);
			break;
		case LOCALDB:
			db = new ShoppingCartLocalRepository();
			break;
		}
		return db;
	}
}
