package domain.shoppingcartproduct;

import domain.product.Product;

public class ShoppingCartProduct {

	private static int counter = 1;

	private static int nextNumber() {
		return counter++;
	}
	
	private Product product;
	private final int id;
	private int qty;
	private final int cartId;

	private void setProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		this.product = product;
	}

	public void setQty(int qty) {
		if (qty <= 0) {
			throw new IllegalArgumentException("Quantity should be greater than 0");
		}
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public int getQty() {
		return qty;
	}
	
	public int getId() {
		return this.id;
	}

	public ShoppingCartProduct(int id, Product product, int qty, int cartId) {
		this.setProduct(product);
		this.setQty(qty);
		this.id = id;
		this.cartId = cartId;
	}
	
	public ShoppingCartProduct(Product product, int qty, int cartId) {
		this(nextNumber(), product, qty, cartId);
	}

	public double getTotal() {
		return product.getPrice() * qty;
	}
	
	public int getCartId() {
		return this.cartId;
	}

}
