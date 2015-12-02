package domain.discount;

import db.discount.DiscountRepository;

public class DiscountService {
	DiscountRepository repo = new DiscountRepository();

	public Discount getDiscount(String code) {

		return repo.getDiscount(code);
	}
}
