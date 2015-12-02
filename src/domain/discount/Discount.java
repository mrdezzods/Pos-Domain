package domain.discount;

import java.util.List;

import domain.product.ShoppingCartProduct;

public abstract class Discount {
	protected double amount;
	protected String code;

	public abstract double calcuate(List<ShoppingCartProduct> products);

	public Discount(String code, Double amount) {
		this.setCode(code);
		this.setAmount(amount);
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
	
	public String getCode(){
		return this.code;
	}

}
