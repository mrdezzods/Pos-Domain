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
			db = PersonSQLRepository.instance(properties);
			break;
		case LOCALDB:
			db = PersonLocalRepository.instance(properties);
			break;
		}
		return db;
	}
}
