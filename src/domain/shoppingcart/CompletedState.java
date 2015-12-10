package domain.shoppingcart;

/**
 * 
 * @author Vijay Sapkota
 *
 */
public class CompletedState implements ShoppingCartState {
	private ShoppingCart cart;

	public CompletedState(ShoppingCart cart) {
		this.cart = cart;
	}

	@Override
	public void completed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pending() {
		// TODO Auto-generated method stub

	}

}
