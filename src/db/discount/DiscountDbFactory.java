package db.discount;

import java.util.Properties;

import db.DBtypes;

/**
 * 
 * @author Milan Sanders
 *
 */
public class DiscountDbFactory {
	private static DiscountDbFactory factory = new DiscountDbFactory();

	public static DiscountDbFactory getDiscountDbFactory() {
		return factory;
	}

	public DiscountDbRepository createDiscountDb(DBtypes type, Properties properties) {
		DiscountDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new DiscountSQLRepository(properties);
			break;
		case LOCALDB:
			db = new DiscountLocalRepository();
			break;
		}
		return db;
	}
}
