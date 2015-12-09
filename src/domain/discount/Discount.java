package domain.discount;

import java.util.List;

import domain.shoppingcartproduct.ShoppingCartProduct;

/**
 * 
 * @author Milan Sanders, Vijay Sapkota
 *
 */
public abstract class Discount {
	protected double amount;
	protected String code;
	private final DiscountType type;

	public abstract double calcuate(List<ShoppingCartProduct> products);

	public Discount(DiscountType type, String code, Double amount) {
		this.setCode(code);
		this.setAmount(amount);
		this.type = type;
	}

	private void setCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("Code cannot be null");
		}
		this.code = code;
	}

	protected void setAmount(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount cannot be lower than 0");
		}
		this.amount = amount;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public DiscountType getType() {
		return this.type;
	}

	public Double getThreshold() {
		return null;
	}

	public Integer getProductId() {
		return null;
	}

}
