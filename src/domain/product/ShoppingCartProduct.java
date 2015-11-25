package domain.product;

public class ShoppingCartProduct {
	private Product product;

	private void setProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		this.product = product;
	}

	private void setQty(int qty) {
		if (qty <= 0) {
			throw new IllegalArgumentException("Qty should be >0");
		}
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public int getQty() {
		return qty;
	}

	private int qty;

	public ShoppingCartProduct(Product product, int qty) {

		this.setProduct(product);
		this.setQty(qty);
	}

	public double getTotal() {
		return product.getPrice() * qty;
	}

}
