import java.util.HashMap;

public class HashIndex extends HashMap {
	// Index storage using hashing with chaining
	public HashIndex () {
		super();
	}
	
	public int find(String ID) {
		Integer i = (Integer) this.get(ID);
		if (i == null) return 0;
		return i;
	}
	
	public void mapping (String key, int value) {
		try { 
			Integer getKey = Integer.parseInt(key);
			this.put(key, value);
		} catch (NumberFormatException e) {
			//Write to log.txt use e.getMessage()
		}
		
	}
}
