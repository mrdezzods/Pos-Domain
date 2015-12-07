package domain.discount;

public class DiscountFactory {

	public Discount createDiscount(DiscountType type, String code,
			double amount, Double threshold, Integer productId) {
		switch (type) {
		case CART_AMOUNT:
			return new CartAmountDiscount(code, amount, threshold);

		case CART_PERCENTAGE:
			return new CartPercentDiscount(code, amount);

		case PRODUCT_AMOUNT:
			return new ProductAmountDiscount(code, amount, productId);

		case PRODUCT_PERCENTAGE:
			return new ProductPercentDiscount(code, amount, productId);

		default:
			return null;
		}
	}
}
