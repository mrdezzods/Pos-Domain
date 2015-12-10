package db.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import domain.product.Product;

/**
 * 
 * @author Milan Sanders, Wouter Dumoulin
 *
 */
public class ProductLocalRepository implements ProductDbRepository {

	private Map<Integer, Product> products;

	private static ProductDbRepository repo = null;

	private ProductLocalRepository() {
		products = new HashMap<>();
		add(new Product(1, "Thing", "This is a thing", 9000.0));
		add(new Product(2, "Thing2", "This is a thing as well", 8000.0));
	}

	public Product get(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0.");
		}
		return products.get(id);
	}

	public List<Product> getAll() {
		return new ArrayList<Product>(products.values());
	}

	public void add(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		if (products.containsKey(product.getId())) {
			throw new IllegalArgumentException("Product already exists");
		}
		products.put(product.getId(), product);
	}

	public void update(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		products.put(product.getId(), product);
	}

	public void delete(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("id must be greater than 0");
		}
		products.remove(id);
	}

	public static ProductDbRepository instance(Properties properties) {
		if (repo == null) {
			repo = new ProductLocalRepository();
		}
		return repo;
	}

}
