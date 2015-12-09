package db.discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.discount.CartAmountDiscount;
import domain.discount.CartPercentDiscount;
import domain.discount.Discount;

/**
 * 
 * @author Milan Sanders, Vijay Sapkota
 *
 */
public class DiscountLocalRepository implements DiscountDbRepository{

	Map<String, Discount> discounts = new HashMap<>();

	public DiscountLocalRepository() {

		CartAmountDiscount d1 = new CartAmountDiscount("C1", (double) 10, 10);
		CartPercentDiscount d2 = new CartPercentDiscount("C2", 0.9);
		discounts.put(d1.getCode(), d1);
		discounts.put(d2.getCode(), d2);
	}

	public Discount get(String code) {
		if(code == null){
			throw new IllegalArgumentException("No code given");
		}
		return discounts.get(code);
	}

	@Override
	public List<Discount> getAll() {
		return new ArrayList<Discount>(discounts.values());
	}

	@Override
	public void add(Discount discount) {
		if(discount == null){
			throw new IllegalArgumentException("No discount given");
		}
		if (get(discount.getCode()) == null)
			discounts.put(discount.getCode(), discount);
	}

	@Override
	public void update(Discount discount) {
		if(discount == null){
			throw new IllegalArgumentException("No discount given");
		}
		if (get(discount.getCode()) == null)
			throw new IllegalArgumentException("A discount with this code doesn't exist");
		add(discount);
	}

	@Override
	public void delete(String code) {
		if(code == null){
			throw new IllegalArgumentException("No code given");
		}
		discounts.remove(code);
	}
}
