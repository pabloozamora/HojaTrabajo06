/**
 * Programa que utiliza tres implementaciones MAP diferentes para simular un del inventario de una tienda
 * en linea, y el carrito de compras de un usuario.
 * Realizado por Pablo Andres Zamora Vasquez - 21780 el 21/03/2022
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase Main. Permite interactuar con el usuario para preguntarle la implmentacion MAP a utilizar,
 * y las acciones a realizar en cuanto al inventario y el carrito de compras.
 * @author Pablo Zamora
 * @version 21/03/2022
 */
public class Main {

	/**
	 * Metodo getValidInt. Permite verificar si la opcion ingresada por el usuario es valida.
	 * @param sc Scanner utilizado para el texto que ingresa el usuario
	 * @param message Texto ingresado
	 * @param absoluteValues Permite convertir cualquier entero a positivo
	 * @param validValues Valores validos
	 * @return int Opcion ingresada
	 */
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
	
	/**
	 * Metodo main. Despliega los menus necesarios para obtener la informacion que necesita
	 * la clase Controlador para manejar los MAPs de inventario y carrito de compras.
	 * @param args
	 */
	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Scanner sc = new Scanner(System.in);
		
		//Se busca el archivo de texto
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
		
		//Se lee el archivo
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<String> products = new ArrayList<String>();
		for (String row : content) {
			String[] line = row.split("\\|");
			String category = line[0].replaceAll("\\s+$", "");
			String product = line[1].replaceAll("\t", "");
			//Se separan las categorias y los productos
			categories.add(category); 
			products.add(product);
		}
		String imp_menu = "\nSeleccione la implementacion de MAP a utilizar:\n1. Hashmap\n2. TreeMap\n3. LinkedHashMap";
		int implementation = getValidInt(sc, imp_menu, false, 1,2,3);
		controlador.createInventory(categories, products, implementation); //Se crea el MAP del inventario y el carrito segun la implementacion elegida
		
		String main_menu = "\nBienvenido a nuestra tienda online. Seleccione la accion a realizar:\n1. Agregar un producto al carrito" +
		"\n2. Mostrar la categoria de un producto\n3. Mostrar el carrito\n4. Mostrar el carrito, ordenado por tipo\n5. Mostrar el inventario" +
				"\n6. Mostrar el inventario, ordenado por tipo\n7. Salir";
		boolean end = false;
		while(!end) {
			int option = getValidInt(sc, main_menu, false, 1,2,3,4,5,6,7);
			switch (option) { //Agregar un producto al carrito
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
				
			case 2: //Determinar la categoria de un producto
				System.out.println("\nIngrese el nombre del producto:");
				String check_product = sc.nextLine();
				System.out.println(controlador.showCategory(check_product));
				break;
				
			case 3: //Mostrar los productos dentro del carrito
				System.out.println("\nMostrando el carrito: ");
				System.out.println(controlador.showCart());
				break;
				
			case 4: //Mostrar los productos dentro del carrito, por tipo
				System.out.println("\nMostrando el carrito por tipo: ");
				System.out.println(controlador.showCartbyType());
				break;
				
			case 5: //Mostrar el inventario
				System.out.println("\nMostrando el inventario: ");
				System.out.println(controlador.showInventory());
				break;
			
			case 6: //Mostrar el inventario, por tipo
				System.out.println("\nMostrando el inventario por tipo: ");
				System.out.println(controlador.showInventorybyType());
				break;
				
			case 7: //Finalizar el programa
				System.out.println("\nGracias por utilizar nuestros servicios en linea. Feliz dia");
				end = true;
			}
		}
	}
}
