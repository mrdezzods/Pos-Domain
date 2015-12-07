package domain.discount;

import java.util.List;

import domain.shoppingcartproduct.ShoppingCartProduct;

/**
 * 
 * @author mrdezzods
 *
 */
public class CartPercentDiscount extends Discount {

	/**
	 * 
	 * @param code
	 * @param amount
	 *            Discount percentage must be a float between 0 and 1
	 */
	public CartPercentDiscount(String code, Double amount) {
		super(DiscountType.CART_PERCENTAGE, code, amount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcuate(List<ShoppingCartProduct> products) {
		double sum = 0;

		for (ShoppingCartProduct product : products) {
			sum += product.getTotal();
		}

		return sum * (1 - this.amount);
	}

	protected void setAmount(double amount) {
		if (amount <= 0 || amount > 1) {
			throw new IllegalArgumentException("Amount must be between 0 and 1");
		}
		super.setAmount(amount);
	}

}
