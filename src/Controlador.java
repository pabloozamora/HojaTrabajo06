import java.util.*;
import java.util.Map.Entry;

public class Controlador {

	private Map<String, String> inventory;
	private Map<String, String> cart;
	
	public void createInventory(ArrayList<String> categories, ArrayList<String> products, int implementation) {
		MapFactory<String, String> factory = new MapFactory<String, String>(implementation);		
		inventory = factory.getInstance();
		for (int i = 0; i < categories.size();i++) {
			inventory.put(products.get(i),categories.get(i));
		}
		MapFactory<String, String> factory2 = new MapFactory<String, String>(implementation);		
		cart = factory2.getInstance();
	}
	
	public String addProduct(String category, String product, int quantity) {
		String message = "";
		if (inventory.containsValue(category)) {
			if (inventory.containsKey(product)) {
				if (inventory.get(product).equals(category)) {
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
					message = "\nProducto(s) agregado(s) al carrito con exito";
				}
				else {
					message = "\nEl producto y la categoria ingresados no coinciden";
					return message;
				}
			}
			else {
				message = "\nEl producto ingresado no es valido";
				return message;
			}
		}
		else {
			message = "\nLa categoria ingresada no es valida (Revise mayusculas y espacios)";
			return message;
		}
		return message;
	}
	
	
	public String showCategory(String product) {
		String message = "";
		String search_product = product + "1";
		String category = (String) inventory.get(search_product);
		if (category == null) {
			message = "\nEl producto ingresado no es valido";
		}
		else {
			message = "\nEl producto " + product + " pertenece a la categoria " + category;
		}
		return message;
	}
	
	public String showCart() {
		if (cart.isEmpty()) {
			return "\nEl carrito de compras todavia esta vacio";
		}
		String message = "";
		ArrayList<String> alreadyDone = new ArrayList<String>();
		for (Map.Entry<String, String> entry : cart.entrySet()) {
			String product = entry.getKey();
			int length = product.length();
			product = product.substring(0,length-1);
			if (!alreadyDone.contains(product)) {
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
				
				alreadyDone.add(product);
				temp_product = product + 1;
				message += "\nProducto: " + product + "\nCategoria: " + cart.get(temp_product) + "\nCantidad: " + quantity + "\n";
			}
			 
		}
		return message;
	}
	
	public String showCartbyType() {
		if (cart.isEmpty()) {
			return "\nEl carrito de compras todavia esta vacio";
		}
		String message = "";
		ArrayList<String> alreadyDone = new ArrayList<String>();
		for (Map.Entry<String, String> entry : cart.entrySet()) {
			String product = entry.getKey();
			int length = product.length();
			product = product.substring(0,length-1);
			if (!alreadyDone.contains(product)) {
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
				
				alreadyDone.add(product);
				temp_product = product + 1;
				message += "\nCategoria: " + cart.get(temp_product) + "\nProducto: " + product + "\nCantidad: " + quantity + "\n";
			}
			 
		}
		return message;
	}
	
	public String showInventory() {
		String message = "";
		for (Map.Entry<String, String> entry : inventory.entrySet()) {
			String product = entry.getKey();
			message += "\nCategoria: " + inventory.get(product) + "\nProducto: " + product + "\n";
		}
		return message;
	}
	
	public String showInventorybyType() {
		String message = "";
		List<Map.Entry<String, String>> list = new ArrayList<>(inventory.entrySet());
		list.sort(Entry.comparingByValue());
		for (Map.Entry<String, String> entry: list) {
			String product = entry.getKey();
			message += "\nCategoria: " + inventory.get(product) + "\nProducto: " + product + "\n";
		}
		return message;
	}
	
}
