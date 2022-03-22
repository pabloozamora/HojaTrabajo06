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
			String product = line[1];
			categories.add(category);
			products.add(product);
		}
		
	}
}
