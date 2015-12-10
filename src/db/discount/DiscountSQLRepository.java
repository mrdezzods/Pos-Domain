package db.discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import db.SQLrepository;
import domain.DbException;
import domain.discount.Discount;
import domain.discount.DiscountFactory;
import domain.discount.DiscountType;

/**
 * 
 * @author Milan Sanders
 *
 */
public class DiscountSQLRepository extends SQLrepository implements DiscountDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.discount";
	private static final String CODE_FIELD = "code";
	private static final String AMOUNT_FIELD = "amount";
	private static final String THRESHOLD_FIELD = "threshold";
	private static final String TYPE_FIELD = "type";
	private static final String PRODUCT_ID_FIELD = "product_id";
	private static DiscountDbRepository repo = null;

	private DiscountFactory factory;

	private DiscountSQLRepository(Properties properties) {
		super(properties);
		factory = new DiscountFactory();
	}

	@Override
	public Discount get(String code) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CODE_FIELD + " = ?";
		Discount discount = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				DiscountType type = DiscountType.valueOf(result.getString(TYPE_FIELD));
				int amount = result.getInt(AMOUNT_FIELD);
				Double threshold = result.getDouble(THRESHOLD_FIELD);
				Integer productId = result.getInt(PRODUCT_ID_FIELD);
				discount = factory.createDiscount(type, code, amount, threshold, productId);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		return discount;
	}

	@Override
	public List<Discount> getAll() {
		Connection connection = createConnection();
		Statement statement = null;
		List<Discount> list = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				String code = result.getString(CODE_FIELD);
				DiscountType type = DiscountType.valueOf(result.getString(TYPE_FIELD));
				int amount = result.getInt(AMOUNT_FIELD);
				Double threshold = result.getDouble(THRESHOLD_FIELD);
				Integer productId = result.getInt(PRODUCT_ID_FIELD);
				list.add(factory.createDiscount(type, code, amount, threshold, productId));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		return list;
	}

	@Override
	public void add(Discount discount) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO " + TABLE_NAME + " (" + TYPE_FIELD + " = ?, " + CODE_FIELD + " = ?, " + AMOUNT_FIELD
				+ " = ?, " + THRESHOLD_FIELD + " = ?, " + PRODUCT_ID_FIELD + " = ?) " + "VALUES (?, ?, ?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, discount.getType().toString());
			statement.setString(2, discount.getCode());
			statement.setDouble(3, discount.getAmount());
			statement.setDouble(4, discount.getThreshold());
			statement.setInt(5, discount.getProductId());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void update(Discount discount) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "UPDATE " + TABLE_NAME + " SET " + TYPE_FIELD + " = ?, " + AMOUNT_FIELD + " = ?, "
				+ THRESHOLD_FIELD + " = ?, " + PRODUCT_ID_FIELD + " = ? WHERE " + CODE_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, discount.getType().toString());
			statement.setDouble(2, discount.getAmount());
			statement.setDouble(3, discount.getThreshold());
			statement.setInt(4, discount.getProductId());
			statement.setString(5, discount.getCode());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void delete(String code) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + CODE_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, code);
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}

	public static DiscountDbRepository instance(Properties properties) {
		if (repo == null) {
			repo = new DiscountSQLRepository(properties);
		}
		return repo;
	}

}
