package db.shoppingcart;

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
import domain.discount.DiscountService;
import domain.shoppingcart.ShoppingCart;
import domain.shoppingcartproduct.ShoppingCartProductService;

/**
 * 
 * @author Milan Sanders
 *
 */
public class ShoppingCartSQLRepository extends SQLrepository implements
		ShoppingCartDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.shoppingcart";
	private static final String ID_FIELD = "id";
	private static final String EMAIL_FIELD = "email";
	private static final String DISCOUNTCODE_FIELD = "discount_code";

	private final DiscountService discountService;
	private final ShoppingCartProductService shoppingCartProductService;

	public ShoppingCartSQLRepository(Properties properties,
			DiscountService discountService, ShoppingCartProductService shoppingCartProductService) {
		super(properties);
		this.discountService = discountService;
		this.shoppingCartProductService = shoppingCartProductService;
	}

	@Override
	public ShoppingCart get(int id) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		ShoppingCart shoppingCart = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String userid = result.getString(EMAIL_FIELD);
				String discountcode = result.getString(DISCOUNTCODE_FIELD);
				shoppingCart = new ShoppingCart(id, userid,
						discountService.getDiscount(discountcode), shoppingCartProductService);
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
		return shoppingCart;
	}

	@Override
	public List<ShoppingCart> getAll() {
		Connection connection = createConnection();
		Statement statement = null;
		List<ShoppingCart> list = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM "
					+ TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt(ID_FIELD);
				String userid = result.getString(EMAIL_FIELD);
				String discountcode = result.getString(DISCOUNTCODE_FIELD);
				list.add(new ShoppingCart(id, userid, discountService
						.getDiscount(discountcode), shoppingCartProductService));
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
	public void add(ShoppingCart cart) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO " + TABLE_NAME + " (" + ID_FIELD + ", "
				+ EMAIL_FIELD + ", " + DISCOUNTCODE_FIELD
				+ ") VALUES (?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cart.getId());
			statement.setString(2, cart.getUserId());
			statement.setString(3, cart.getDiscountCode());
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
	public void update(ShoppingCart cart) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "UPDATE " + TABLE_NAME + " SET " + EMAIL_FIELD + " = ?, "
				+ DISCOUNTCODE_FIELD + " = ? WHERE " + ID_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, cart.getUserId());
			statement.setString(2, cart.getDiscountCode());
			statement.setInt(3, cart.getId());
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
	public void delete(int id) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
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
	public int getMaxId() {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		int max = 0;
		String sql = "SELECT max(" + ID_FIELD + ") FROM " + TABLE_NAME;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				max = result.getInt(1);
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
		return max;
	}

}
