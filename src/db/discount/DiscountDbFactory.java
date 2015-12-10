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
			db = DiscountSQLRepository.instance(properties);
			break;
		case LOCALDB:
			db = DiscountLocalRepository.instance(properties);
			break;
		}
		return db;
	}
}
