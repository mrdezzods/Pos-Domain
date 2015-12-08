package db.shoppingcartproduct;

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
import domain.product.ProductService;
import domain.shoppingcartproduct.ShoppingCartProduct;

public class ShoppingCartProductSQLRepository extends SQLrepository implements
		ShoppingCartProductDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.shoppingcartproduct";
	private static final String ID_FIELD = "id";
	private static final String PRODUCT_ID_FIELD = "product_id";
	private static final String CART_ID_FIELD = "cart_id";
	private static final String QUANTITY_FIELD = "qty";

	private final ProductService productService;

	public ShoppingCartProductSQLRepository(Properties properties,
			ProductService productService) {
		super(properties);
		this.productService = productService;
	}

	@Override
	public ShoppingCartProduct get(int id) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		ShoppingCartProduct p = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int productId = result.getInt(PRODUCT_ID_FIELD);
				int qty = result.getInt(QUANTITY_FIELD);
				int cartId = result.getInt(CART_ID_FIELD);
				p = new ShoppingCartProduct(id,
						productService.getProduct(productId), qty, cartId);
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
		return p;
	}

	@Override
	public List<ShoppingCartProduct> getAll() {
		Connection connection = createConnection();
		Statement statement = null;
		List<ShoppingCartProduct> list = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM "
					+ TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt(ID_FIELD);
				int productId = result.getInt(PRODUCT_ID_FIELD);
				int qty = result.getInt(QUANTITY_FIELD);
				int cartId = result.getInt(CART_ID_FIELD);
				list.add(new ShoppingCartProduct(id, productService
						.getProduct(productId), qty, cartId));
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
	public void add(ShoppingCartProduct product) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO " + TABLE_NAME + " (" + ID_FIELD + ", "
				+ PRODUCT_ID_FIELD + ", " + CART_ID_FIELD + ", "
				+ QUANTITY_FIELD + ") VALUES (?, ?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, product.getId());
			statement.setInt(2, product.getProduct().getId());
			statement.setInt(3, product.getCartId());
			statement.setInt(4, product.getQty());
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
	public void update(ShoppingCartProduct product) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "UPDATE " + TABLE_NAME + " SET " + PRODUCT_ID_FIELD
				+ " = ?, " + CART_ID_FIELD + " = ?, " + QUANTITY_FIELD
				+ " = ? WHERE " + ID_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, product.getProduct().getId());
			statement.setInt(2, product.getCartId());
			statement.setInt(3, product.getQty());
			statement.setInt(4, product.getId());
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
