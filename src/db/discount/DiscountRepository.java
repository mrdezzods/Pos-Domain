package db.discount;

import java.util.ArrayList;
import java.util.List;

import domain.discount.CartAmountDiscount;
import domain.discount.CartPercentDiscount;
import domain.discount.Discount;
import domain.product.ShoppingCartProduct;

public class DiscountRepository {

	ArrayList<Discount> discounts = new ArrayList<>();

	public DiscountRepository() {

		CartAmountDiscount d1 = new CartAmountDiscount("C1", (double) 10, 10);
		CartPercentDiscount d2 = new CartPercentDiscount("C2", 0.9);
		discounts.add(d1);
		discounts.add(d2);
	}

	public Discount getDiscount(String code) {
		System.out.println(code);
		for (Discount d : discounts) {
			if (d.getCode().equals(code)) {
				return d;
			}
		}

		return null;
	}
}
