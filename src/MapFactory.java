import java.util.*;

public class MapFactory<K, V> {
	private Map<K, V> map;
	
	public MapFactory(int implementation) {
		if (implementation == 1) {
			map = new HashMap<>();
		}
		else if (implementation == 2) {
			map = new TreeMap<>();
		}
		else if (implementation == 3) {
			map = new LinkedHashMap<>();
		}
	}
	
	public Map<K,V >getInstance() {
		return map;
	}
}
