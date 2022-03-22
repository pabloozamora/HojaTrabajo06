import java.util.*;
import java.util.Map.Entry;

/**
 * Clase Controlador. Posee los metodos necesarios para manejar el inventario y el carrito de compras.
 * @author Pablo Zamora
 * @version 21/03/2022
 */
public class Controlador {

	private Map<String, String> inventory; //Map del inventario
	private Map<String, String> cart; //Map del carrito
	
	/**
	 * Metodo createInventory. Crea el MAP correspondiente al inventario y al carrito
	 * @param categories ArrayList<String> Categorias a agregar al inventario.
	 * @param products ArrayList<String> Productos a agregar al inventario.
	 * @param implementation int Implementacion MAP a utilizar
	 */
	public void createInventory(ArrayList<String> categories, ArrayList<String> products, int implementation) {
		MapFactory<String, String> factory = new MapFactory<String, String>(implementation);	//Se determina el tipo de MAP a utilizar	
		inventory = factory.getInstance();
		//Se agregan todos los productos del archivo .txt
		for (int i = 0; i < categories.size();i++) {
			inventory.put(products.get(i),categories.get(i));
		}
		MapFactory<String, String> factory2 = new MapFactory<String, String>(implementation);		
		cart = factory2.getInstance();
	}
	
	/**
	 * Metodo addProduct. Permite agregar un producto al carrito de compras.
	 * @param category String. Categoria del producto
	 * @param product String. Descripcion del producto
	 * @param quantity int. Cantidad de articulos del mismo producto a agregar.
	 * @return String. Mensaje a desplegar en pantalla.
	 */
	public String addProduct(String category, String product, int quantity) {
		String message = "";
		if (inventory.containsValue(category)) { //Se determina si la categoria existe en el inventario
			if (inventory.containsKey(product)) { //Se determina si el producto existe en el inventario
				if (inventory.get(product).equals(category)) { //Se determina si el producto corresponde a la categoria ingresada por el usuario
					for (int i = 1; i <= quantity;i++) {
						int count = 1;
						String current_product = product + count;
						if (!cart.containsKey(current_product)) { //Es un producto nuevo dentro del carrito
							cart.put(current_product, category);
						}
						else {
							while (cart.containsKey(current_product)) { //Ya hay productos iguales en el carrito
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
	
	/**
	 * Metodo showCategory. Permite obtener la categoria a la que pertenece determinado producto.
	 * @param product String. Producto a evaluar.
	 * @return String. Mensaje a desplegar.
	 */
	public String showCategory(String product) {
		String message = "";
		String category = (String) inventory.get(product);
		if (category == null) {
			message = "\nEl producto ingresado no es valido";
		}
		else {
			message = "\nEl producto " + product + " pertenece a la categoria " + category;
		}
		return message;
	}
	
	/**
	 * Metodo showCart. Devuelve los productos ingresados al carrito, junto con su categoria y cantidad.
	 * @return String. Mensaje a desplegar.
	 */
	public String showCart() {
		if (cart.isEmpty()) { //El carrito esta vacio
			return "\nEl carrito de compras todavia esta vacio";
		}
		String message = "";
		ArrayList<String> alreadyDone = new ArrayList<String>(); //Arraylist con los productos ya evaluados
		for (Map.Entry<String, String> entry : cart.entrySet()) { //Por cada elemento del MAP
			String product = entry.getKey();
			int length = product.length();
			product = product.substring(0,length-1); //Se obtiene el nombre original del producto a evaluar
			if (!alreadyDone.contains(product)) { //Si el producto no ha sido evaluado
				String temp_product = product + 1;
				boolean notFinished = true;
				int quantity = 0;
				while (notFinished) { //Mientras haya un articulo mas con la misma descripcion de producto
					temp_product = product + (quantity+1);
					if(cart.containsKey(temp_product)) {
						quantity++; //Se suma uno a la cantidad de articulos de dicho producto en el carrito
					}
					else {
						notFinished = false;
					}
				}
				
				alreadyDone.add(product); //Se agrega el producto a la lista de productos evaluados
				temp_product = product + 1;
				message += "\nProducto: " + product + "\nCategoria: " + cart.get(temp_product) + "\nCantidad: " + quantity + "\n";
			}
			 
		}
		return message;
	}
	
	/**
	 * Metodo showCartbyType. Despliega los productos del carrito por categoria
	 * @return String. Mensaje a desplegar.
	 */
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
	
	/**
	 * Metodo showInventory. Despliega los productos del inventario.
	 * @return String. Mensaje a desplegar.
	 */
	public String showInventory() {
		String message = "";
		for (Map.Entry<String, String> entry : inventory.entrySet()) { //Por cada elemento del MAP
			String product = entry.getKey(); //Se determina el producto
			message += "\nCategoria: " + inventory.get(product) + "\nProducto: " + product + "\n";
		}
		return message;
	}
	
	/**
	 * Metodo showInventorybyType. Despliega los productos del inventario por categoria.
	 * @return String. Mensaje a desplegar.
	 */
	public String showInventorybyType() {
		String message = "";
		List<Map.Entry<String, String>> list = new ArrayList<>(inventory.entrySet()); //Se realiza un ArrayList para ordenar los productos
		list.sort(Entry.comparingByValue()); //Se ordena el arrayList, comparando los valores (categorias) alfabeticamente
		for (Map.Entry<String, String> entry: list) { //Se despliega cada elemento del arrayList
			String product = entry.getKey();
			message += "\nCategoria: " + inventory.get(product) + "\nProducto: " + product + "\n";
		}
		return message;
	}
	
}
