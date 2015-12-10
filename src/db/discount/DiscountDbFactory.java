package db.discount;

import java.util.Properties;

import db.DBtypes;

/**
 * 
 * @author Milan Sanders, Vijay
 *
 */
public class DiscountDbFactory {

	private static DiscountDbRepository db = null;

	public DiscountDbRepository createDiscountDb(DBtypes type, Properties properties) {

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
