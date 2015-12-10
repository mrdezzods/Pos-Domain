package domain.shoppingcart;

/**
 * 
 * @author Vijay Sapkota
 *
 */
public class PendingState implements ShoppingCartState {

	private ShoppingCart cart;

	public PendingState(ShoppingCart cart) {
		this.cart = cart;
	}

	@Override
	public void completed() {
	}

	@Override
	public void pending() {
	}

}
