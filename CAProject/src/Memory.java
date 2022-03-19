import java.util.Arrays;

public class Memory {
	
	public String[] mem = new String[2048];

	public Memory() {
		
		Arrays.fill(mem, "0000000000000000");
	}
	
	public String fetch (String PC) {
		
		int PCInt = (int) Long.parseLong(PC, 2);
		
		return mem[PCInt] + mem[PCInt+1];
	}
	
	public void write (String data, String address) {
		
		int pos = (int) Long.parseLong(address, 2);
		mem[pos] = data.substring(0, 8);
		mem[pos+1] = data.substring(8, 16);
	}

	public String read (String address) {
		
		int pos = (int) Long.parseLong(address, 2);
		return mem[pos] + mem[pos+1];
	}

}
