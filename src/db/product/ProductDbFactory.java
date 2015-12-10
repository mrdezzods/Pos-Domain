package db.product;

import java.util.Properties;

import db.DBtypes;

/**
 * 
 * @author Milan Sanders, Wouter Dumoulin, Vijay Sapkota
 *
 */
public class ProductDbFactory {
	private static ProductDbRepository db = null;

	public ProductDbRepository createProductDb(DBtypes type, Properties properties) {
		switch (type) {
		case SQLDB:
			db = new ProductSQLRepository(properties);
			break;
		case LOCALDB:
			db = new ProductLocalRepository();
			break;
		}
		return db;
	}
}
