package db.product;

import java.util.List;

import domain.product.Product;

/**
 * 
 * @author Milan Sanders, Wouter Dumoulin
 *
 */
public interface ProductDbRepository {
	Product get(int id);
	List<Product> getAll();
	void add(Product product);
	void update(Product product);
	void delete(int id);
}
