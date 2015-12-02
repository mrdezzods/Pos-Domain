package db.discount;

import java.util.ArrayList;
import java.util.List;

import domain.discount.CartAmountDiscount;
import domain.discount.Discount;
import domain.product.ShoppingCartProduct;

public class DiscountRepository {

	ArrayList<Discount> discounts = new ArrayList<>();

	public DiscountRepository() {
		
	}

	public Discount getDiscount(String code) {
		for(Discount d :discounts){
			//if(d.get)
		}
		
		return null;
	}
}
