package db.person;

import java.util.Properties;

import db.DBtypes;

/**
 * 
 * @author Milan Sanders, Wouter Dumoulin, Vijay Sapkota
 *
 */
public class PersonDbFactory {
	private static PersonDbRepository db = null;

	public PersonDbRepository createPersonDb(DBtypes type, Properties properties) {
		switch (type) {
		case SQLDB:
			db = new PersonSQLRepository(properties);
			break;
		case LOCALDB:
			db = new PersonLocalRepository();
			break;
		}
		return db;
	}
}
