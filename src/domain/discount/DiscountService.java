package domain.discount;

import java.util.List;
import java.util.Properties;

import db.DBtypes;
import db.discount.DiscountDbFactory;
import db.discount.DiscountDbRepository;

public class DiscountService {
	DiscountDbRepository repo;

	public DiscountService(DBtypes type, Properties properties) {
		DiscountDbFactory factory = DiscountDbFactory.getDiscountDbFactory();
		repo = factory.createDiscountDb(type, properties);
	}

	public Discount getDiscount(String code) {
		return getDiscountRepository().get(code);
	}

	public List<Discount> getDiscounts() {
		return getDiscountRepository().getAll();
	}

	public void addDiscount(Discount discount) {
		getDiscountRepository().add(discount);
	}

	public void updateDiscount(Discount discount) {
		getDiscountRepository().update(discount);
	}

	public void deleteDiscount(String code) {
		getDiscountRepository().delete(code);
	}

	private DiscountDbRepository getDiscountRepository() {
		return repo;
	}
}
