package domain.discount;

import java.util.List;

import domain.product.ShoppingCartProduct;

public class ProductAmountDiscount extends Discount {

	private int productId;

	public ProductAmountDiscount(String code, Double amount, int productId) {
		super(code, amount);
		this.setProductId(productId);
	}

	@Override
	public double calcuate(List<ShoppingCartProduct> products) {
		double sum = 0;
		boolean used = false;
		for (ShoppingCartProduct product : products) {

			if (product.getId() == this.productId && !used) {
				sum += product.getTotal() - this.amount;
				used = true;
			} else {
				sum += product.getTotal();
			}
		}

		return sum;

	}

	private void setProductId(int id){
		this.productId = id;
	}

}