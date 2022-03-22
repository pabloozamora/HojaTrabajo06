import java.util.*;

public class Controlador {

	private Map<String, String> inventory;
	private Map<String, String> cart;
	
	public void createInventory(ArrayList<String> categories, ArrayList<String> products, int implementation) {
		MapFactory<String, String> factory = new MapFactory<String, String>(implementation);		
		inventory = factory.getInstance();
		for (int i = 0; i < categories.size();i++) {
			inventory.put(products.get(i),categories.get(i));
		}
	}
	
	public void addProduct(String category, String product, int quantity) {
		for (int i = 1; i <= quantity;i++) {
			int count = 1;
			String current_product = product + count;
			if (!cart.containsKey(current_product)) {//Es un producto nuevo
				cart.put(current_product, category);
			}
			else {
				while (cart.containsKey(current_product)) {
					count++;
					current_product = product + count;
				}
				cart.put(current_product, category);
			}
			
		}
	}
	
	
	public String showCategory(String product) {
		String message = "";
		String search_product = product + "1";
		String category = (String) cart.get(search_product);
		if (category == null) {
			message = "\nEl producto todavia no ha sido agregado al carrito";
		}
		else {
			message = "\nEl producto " + product + " pertenece a la categoria " + category;
		}
		return message;
	}
	
	public String showCart() {
		if (!cart.isEmpty()) {
			return "\nEl carrito de compras todavia esta vacio";
		}
		String message = "";
		for (Map.Entry<String, String> entry : cart.entrySet()) {
			String product = entry.getKey();
			String temp_product = product + 1;
			boolean notFinished = true;
			int quantity = 0;
			while (notFinished) {
				temp_product = product + (quantity+1);
				if(cart.containsKey(temp_product)) {
					quantity++;
				}
				else {
					notFinished = false;
				}
			}
			
			message += "\nProducto: " + product + "\nCategoria: " + cart.get(temp_product) + "\nCantidad: " + quantity + "\n"; 
		}
		return message;
	}
	
	public String showCartbyCategory() {
		if (!cart.isEmpty()) {
			return "\nEl carrito de compras todavia esta vacio";
		}
		String message = "";
		for (Map.Entry<String, String> entry : cart.entrySet()) {
			String product = entry.getKey();
			String temp_product = product + 1;
			boolean notFinished = true;
			int quantity = 0;
			while (notFinished) {
				temp_product = product + (quantity+1);
				if(cart.containsKey(temp_product)) {
					quantity++;
				}
				else {
					notFinished = false;
				}
			}
			
			message += "\nCategoria: " + "\nProducto: " + product + cart.get(temp_product) + "\nCantidad: " + quantity + "\n"; 
		}
		return message;
	}
	
	public String showInventory() {
		String message = "";
		for (Map.Entry<String, String> entry : inventory.entrySet()) {
			String product = entry.getKey();
			message += "\nCategoria: " + "\nProducto: " + product + inventory.get(product) + "\n";
		}
		return message;
	}
}
