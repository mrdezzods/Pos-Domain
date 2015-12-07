package domain.discount;

import java.util.List;

import domain.product.ShoppingCartProduct;

public class ProductPercentDiscount extends Discount {
	private int productId;

	public ProductPercentDiscount(String code, Double amount, int productId) {
		super(DiscountType.PRODUCT_PERCENTAGE, code, amount);
		this.setProductId(productId);
		;
	}

	@Override
	public double calcuate(List<ShoppingCartProduct> products) {
		double sum = 0;

		for (ShoppingCartProduct product : products) {

			if (product.getId() == this.productId) {
				sum += product.getTotal() * (1 - this.amount);
			} else {
				sum += product.getTotal();
			}
		}

		return sum;

	}

	private void setProductId(int id) {
		this.productId = id;
	}
	
	protected void setAmount(double amount) {
		if (amount <= 0 || amount > 1) {
			throw new IllegalArgumentException("Amount must be between 0 and 1");
		}
		super.setAmount(amount);
	}
	
	public Integer getProductId() {
		return productId;
	}

}
