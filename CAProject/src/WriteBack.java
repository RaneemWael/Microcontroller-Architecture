
public class WriteBack {
	
	//WB
	public static boolean RegWrite = false;
	public static boolean MemToReg = false;
	
	public static int prog;
	public static String readData = null;;
	public static String aluResult;
	public static String regDst;

	public WriteBack() {}
	
	public static void run () {
		
		if (readData != null) {
		
		System.out.println(CPU.program[prog] + " is in WB stage");
		System.out.println();
		
		CPU.RegisterFile.RegWrite = RegWrite;
		
		if (MemToReg)
			CPU.RegisterFile.setWriteReg(regDst, readData);
		else
			CPU.RegisterFile.setWriteReg(regDst, aluResult);
		
		readData = null;
		
		}
	}
	
	public static boolean inUse() {
		
		return readData != null;
	}

}
