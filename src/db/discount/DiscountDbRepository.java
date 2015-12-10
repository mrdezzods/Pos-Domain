package db.discount;

import java.util.List;
import java.util.Properties;

import domain.discount.Discount;

/**
 * 
 * @author Milan Sanders
 *
 */
public interface DiscountDbRepository {
	
	
	Discount get(String code);
	List<Discount> getAll();
	void add(Discount discount);
	void update(Discount discount);
	void delete(String code);
}
