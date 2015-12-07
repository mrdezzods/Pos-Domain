package db.discount;

import java.util.List;

import domain.discount.Discount;

public interface DiscountDbRepository {
	Discount get(String code);
	List<Discount> getAll();
	void add(Discount discount);
	void update(Discount discount);
	void delete(String code);
}
