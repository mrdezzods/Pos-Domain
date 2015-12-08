package domain.discount;

import java.util.List;

import domain.shoppingcartproduct.ShoppingCartProduct;

public class ProductAmountDiscount extends Discount {

	private int productId;

	public ProductAmountDiscount(String code, Double amount, int productId) {
		super(DiscountType.PRODUCT_AMOUNT, code, amount);
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
	
	public Integer getProductId() {
		return productId;
	}

}
