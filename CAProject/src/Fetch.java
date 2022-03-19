
public class Fetch {

	public Fetch() {}
	
	public static void run () {
		
		if (CPU.progCount > 0) {
		
		System.out.println(CPU.program[14-CPU.progCount] + " is in Fetch stage:");
		System.out.println();
		
		String nextPC = Integer.toBinaryString((int) Long.parseLong(CPU.PC, 2) + 2);
		
		while (nextPC.length()<16)
			nextPC = "0" + nextPC;
		
		CPU.nextPC = nextPC;
		
		System.out.println("Next PC: " + nextPC);
	
		String instr = CPU.Memory.fetch(CPU.PC);
		
		System.out.println("Instruction: " + instr);
		
		System.out.println();
		
		Decode.prog = 14-CPU.progCount;
		CPU.progCount--;
		Decode.nextPC = nextPC;
		Decode.instr = instr;
		
		}
	}
	
	public static boolean inUse() {
		
		return CPU.progCount > 0;
	}
}
