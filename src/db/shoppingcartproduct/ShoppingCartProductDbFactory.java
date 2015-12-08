package db.shoppingcartproduct;

import java.util.Properties;

import db.DBtypes;
import domain.product.ProductService;

public class ShoppingCartProductDbFactory {
	
	private final ProductService productService;
	
	public ShoppingCartProductDbFactory(ProductService productService) {
		this.productService = productService;
	}

	public ShoppingCartProductDbRepository createShoppingCartProductDb(DBtypes type, Properties properties) {
		ShoppingCartProductDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new ShoppingCartProductSQLRepository(properties, productService);
			break;
		case LOCALDB:
			db = new ShoppingCartProductLocalRepository();
			break;
		}
		return db;
	}
}
