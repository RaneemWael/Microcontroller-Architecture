
public class MemoryAccess {
	
	//WB
	public static boolean RegWrite = false;
	public static boolean MemToReg = false;
	
	//M
	public static boolean MemWrite = false;
	public static boolean MemRead = false;
	public static boolean Branch = false;
	
	public static int prog;
	public static String branchPC = null;
	public static boolean zeroFlag;
	public static String aluResult;
	public static String writeData;
	public static String regDst;

	public MemoryAccess() {}
	
	public static void run () {
		
		if (branchPC != null) {
		
		System.out.println(CPU.program[prog] + " is in Memory stage:");
		System.out.println();
		
		CPU.branchAddress = branchPC;
		CPU.PCSrc = zeroFlag && Branch;
		
		String readData = "";
		if (MemRead)
			readData = CPU.Cache.read(aluResult);
		else if (MemWrite)
			CPU.Cache.write(writeData, aluResult);

		System.out.println("ALU result: " + aluResult);
		
		if (MemRead)
			System.out.println("memory word read: " + readData);
		else
			System.out.println("memory word read: don't care");
		
		System.out.println("rt/rd field: " + regDst);
		
		System.out.println("WB controls: MemToReg: " + MemToReg + ", RegWrite: " + RegWrite);

		System.out.println();
		
		WriteBack.prog = prog;
		WriteBack.RegWrite = RegWrite;
		WriteBack.MemToReg = MemToReg;
		WriteBack.aluResult = aluResult;
		WriteBack.readData = readData;
		WriteBack.regDst = regDst;	
		
		branchPC = null;
		
		}
	}
	
	public static boolean inUse() {
		
		return branchPC != null;
	}

}
