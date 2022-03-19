import java.util.Arrays;

public class Cache {
	
	public int misses = 0;
	public int hits = 0;
	public CacheBlock[] cache = new CacheBlock[512];

	public Cache() {
		
		Arrays.fill(cache, new CacheBlock());
	}
	
	public String read (String address) {
		
		int blockNum = (int) Long.parseLong(address, 2) % 512;
		String tag = null;
		
		for (int i=0; i<7; i++) {
			tag += address.charAt(i);
		}
		
		if (cache[blockNum].valid) {
			
			if (cache[blockNum].tag.equals(tag)) {
				hits++;
				return cache[blockNum].data;
			}
			else {
				misses++;
				cache[blockNum].setTag(tag);
				cache[blockNum].setData(CPU.Memory.read(address));
				return cache[blockNum].data; 
			}
		}
		else {
			misses++;
			cache[blockNum].setTag(tag);
			cache[blockNum].setData(CPU.Memory.read(address));
			cache[blockNum].setValid(true);
			return cache[blockNum].data; 
		}
	}
	
	public void write (String data, String address) {
		
		int blockNum = (int) Long.parseLong(address, 2) % 512;
		String tag = null;
		
		for (int i=0; i<7; i++) {
			tag += address.charAt(i);
		}
		
		if (cache[blockNum].valid) {
			
			if (cache[blockNum].tag.equals(tag)) {
				cache[blockNum].data = data;
				CPU.Memory.write(data, address);
			}
			else {
				CPU.Memory.write(data, address);
			}
		}
		else {
			CPU.Memory.write(data, address);
		}
	}

}
