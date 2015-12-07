package db;

import java.util.List;
import java.util.Properties;

import domain.ShoppingCart;
import domain.ShoppingCartService;
import domain.discount.Discount;
import domain.discount.DiscountService;
import domain.person.Person;
import domain.person.PersonService;
import domain.person.Role;
import domain.product.Product;
import domain.product.ProductService;
import domain.product.ShoppingCartProduct;

public class WebshopFacade {
	private final PersonService personService;
	private final ProductService productService;
	private final ShoppingCartService shoppingCartService;
	private final DiscountService discountService;

	public WebshopFacade(Properties properties) {
		personService = new PersonService(DBtypes.LOCALDB, properties);
		productService = new ProductService(DBtypes.LOCALDB, properties);
		shoppingCartService = new ShoppingCartService();

		discountService = new DiscountService();

	}

	// product things

	public Product getProduct(int productId) {
		return productService.getProduct(productId);
	}

	public List<Product> getProducts() {
		return productService.getProducts();
	}

	public void addProduct(Product product) {
		productService.addProduct(product);
	}

	public void updateProduct(Product product) {
		productService.updateProduct(product);
	}

	public void deleteProduct(int productId) {
		productService.deleteProduct(productId);
	}

	// person things

	public Person getPerson(String personId) {
		return personService.getPerson(personId);
	}

	public List<Person> getPersons() {
		return personService.getPersons();
	}

	public boolean hasPerson(Person person) {
		return personService.hasPerson(person);
	}

	public boolean canHaveAsPerson(Person person) {
		return personService.canHaveAsPerson(person);
	}

	public void addPerson(Person person) {
		if (canHaveAsPerson(person))
			personService.addPerson(person);
	}

	public void updatePerson(Person person) {
		personService.updatePerson(person);
	}

	public void deletePerson(String id) {
		personService.deletePerson(id);
	}

	public Person getAuthenticatedUser(String personId, String password) {
		return personService.getAuthenticatedUser(personId, password);
	}

	public Role getRole(String userId) {
		Person person = personService.getPerson(userId);
		return person == null ? null : person.getRole();
	}

	// cart things

	public ShoppingCart createCart(String userId) {
		return shoppingCartService.createCart(userId);
	}

	public ShoppingCart getCart(int cartId) {
		return shoppingCartService.getCart(cartId);
	}

	public String getAppliedDiscountCode(int cartId) {
		Discount discount = shoppingCartService.getCart(cartId).getDiscount();
		if (discount == null) {
			return null;
		}
		return discount.getCode();
	}

	public ShoppingCart getCartFromUser(String userId) {
		return shoppingCartService.getCartFromUser(userId);
	}

	public void deleteCart(int cartId) {
		shoppingCartService.removeCart(cartId);
	}

	public double getTotalFromCart(int cartId) {
		ShoppingCart shoppingcart = shoppingCartService.getCart(cartId);
		return shoppingcart.getTotalPrice();
	}

	public void addProductToCart(int cartId, Product product) {
		addProductToCart(cartId, product, 1);
	}

	public void addProductToCart(int cartId, Product product, int quantity) {
		ShoppingCartProduct prd = new ShoppingCartProduct(product, quantity);
		shoppingCartService.getCart(cartId).addProduct(prd);
	}

	public void addProductToCartFromUser(String userId, Product product, int quantity) {
		ShoppingCart cart = shoppingCartService.getCartFromUser(userId);
		if (cart == null)
			throw new IllegalArgumentException("This user doesn't have a cart");
		addProductToCart(cart.getId(), product, quantity);
	}

	public int getTotalQty(int cartId) {
		return shoppingCartService.getCart(cartId).getTotalQty();
	}

	public int getTotalQtyFromUser(String userId) {
		ShoppingCart cart = shoppingCartService.getCartFromUser(userId);
		if (cart == null)
			throw new IllegalArgumentException("This user doesn't have a cart");
		return getTotalQty(cart.getId());
	}

	// discount things
	public void addDiscountToCart(int cartId, String code) {
		ShoppingCart cart = getCart(cartId);

		Discount discount = getDiscount(code);
		cart.setDiscount(discount);
	}

	private Discount getDiscount(String code) {
		return discountService.getDiscount(code);
	}

}
