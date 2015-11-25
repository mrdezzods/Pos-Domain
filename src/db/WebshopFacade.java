package db;

import java.util.List;
import java.util.Properties;

import domain.ShoppingCart;
import domain.ShoppingCartService;
import domain.person.Person;
import domain.person.PersonService;
import domain.person.Role;
import domain.product.Product;
import domain.product.ProductService;

public class WebshopFacade {
	private final PersonService personService;
	private final ProductService productService;
	private final ShoppingCartService shoppingCartService;

	public WebshopFacade(Properties properties) {
		personService = new PersonService(DBtypes.LOCALDB, properties);
		productService = new ProductService(DBtypes.LOCALDB, properties);
		shoppingCartService = new ShoppingCartService();
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

	public ShoppingCart getCart(String userId) {
		return shoppingCartService.getCart(userId);
	}

	public void deleteCart(String userId) {
		shoppingCartService.removeCart(userId);
	}

	public double getTotalFromCart(String userId) {
		ShoppingCart shoppingcart = shoppingCartService.getCart(userId);
		return shoppingcart.getTotalPrice();
	}

	public void addProductToCart(String userId, Product product) {
		ShoppingCart shoppingcart = shoppingCartService.getCart(userId);
		shoppingcart.addProduct(product);
	}
	
	public void addProductToCart(String userId, Product product, int quantity) {
		shoppingCartService.getCart(userId).addProduct(product, quantity);
	}

	public Object getOrderAmount(String userId) {
		return shoppingCartService.getCart(userId).getOrderAmount();
	}

}
