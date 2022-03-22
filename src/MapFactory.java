import java.util.*;

/**
 * Clase MapFactory. Permite instanciar la implementacion adecuada del ADT Stack.
 * @author Pablo Zamora
 * @version 21/03/2022
 * @param <K, V> Parametros de la llave y el valor.
 */
public class MapFactory<K, V> {
	private Map<K, V> map; //Tipo de MAP a instanciar
	
	/**
	 * Metodo constructor.
	 * @param implementation Determina la clase de MAP a instanciar.
	 */
	public MapFactory(int implementation) {
		
		//Modificar la implementacion a utilizar
		if (implementation == 1) { //HashMap
			map = new HashMap<>();
		}
		else if (implementation == 2) { //TreeMap
			map = new TreeMap<>();
		}
		else if (implementation == 3) { //LinkedHashMap
			map = new LinkedHashMap<>();
		}
	}
	
	/**
	 * Metodo getInstance. Devuelve la instancia correspondiente del MAP.
	 * @return Map<K, V>
	 */
	public Map<K,V >getInstance() {
		return map;
	}
}
