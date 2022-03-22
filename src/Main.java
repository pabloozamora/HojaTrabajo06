import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static int getValidInt(Scanner sc, String message, boolean absoluteValues, Integer... validValues) {

		while (true) {

			try {

				System.out.println(message);
				int value = sc.nextInt();
				sc.nextLine();

				if (Arrays.asList(validValues).contains(value) || validValues.length == 0)
					return absoluteValues ? Math.abs(value) : value;
				else
					System.out.println("Por favor, ingrese un numero valido");

			} catch (Exception ex) {
				System.out.println("Por favor, ingrese un numero valido");
				sc.nextLine();
			}
		}
	}
	
	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nBienvenido al inventario de la tienda en linea");
		System.out.println("\nIngrese 'Enter' para buscar el archivo de inventario");
		sc.nextLine();
		String[] content = null;
		boolean fileFound = false;
		while (!fileFound) {
			try {
				content = FileController.readFile();
				fileFound = true;
			}
			catch (IOException e){
				System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo ListadoProducto.txt sea valido y se encuentre en la carpeta donde se encuentra el programa.");
				System.out.println("Presione enter para volver a buscar el archivo.");
				sc.nextLine();
			}
		}
		
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<String> products = new ArrayList<String>();
		for (String row : content) {
			String[] line = row.split("\\|");
			String category = line[0].replaceAll("\\s+$", "");
			String product = line[1].replaceAll("\t", "");
			categories.add(category);
			products.add(product);
		}
		String imp_menu = "\nSeleccione la implementacion de MAP a utilizar:\n1. Hashmap\n2. TreeMap\n3. LinkedHashMap";
		int implementation = getValidInt(sc, imp_menu, false, 1,2,3);
		controlador.createInventory(categories, products, implementation);
		
		String main_menu = "\nBienvenido a nuestra tienda online. Seleccione la accion a realizar:\n1. Agregar un producto al carrito" +
		"\n2. Mostrar la categoria de un producto\n3. Mostrar el carrito\n4. Mostrar el carrito, ordenado por categoria\n5. Mostrar el inventario" +
				"\n6. Mostrar el inventario, ordenado por categoria\n7. Salir";
		boolean end = false;
		while(!end) {
			int option = getValidInt(sc, main_menu, false, 1,2,3,4,5,6,7);
			switch (option) {
			case 1:
				System.out.println("\nIngrese la categoria del producto a agregar:");
				String category = sc.nextLine();
				System.out.println("\nIngrese el producto a ingresar:");
				String product = sc.nextLine();
				System.out.println("\nIngrese la cantidad a ingresar:");
				int quantity = sc.nextInt();
				sc.nextLine();
				System.out.println(controlador.addProduct(category, product, quantity));
				break;
				
			case 2:
				System.out.println("\nIngrese el nombre del producto:");
				String check_product = sc.nextLine();
				System.out.println(controlador.showCategory(check_product));
				break;
				
			case 3:
				System.out.println("\nMostrando el carrito: ");
				System.out.println(controlador.showCart());
				break;
				
			case 4:
				System.out.println("\nMostrando el carrito por tipo: ");
				System.out.println(controlador.showCartbyType());
				break;
				
			case 5:
				System.out.println("\nMostrando el inventario: ");
				System.out.println(controlador.showInventory());
				break;
			
			case 6:
				System.out.println("\nMostrando el inventario por tipo: ");
				System.out.println(controlador.showInventorybyType());
				break;
				
			case 7:
				System.out.println("\nGracias por utilizar nuestros servicios en linea. Feliz dia");
				end = true;
			}
		}
	}
}
