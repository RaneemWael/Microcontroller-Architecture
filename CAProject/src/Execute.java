
public class Execute {
	
	//WB
	public static boolean RegWrite = false;
	public static boolean MemToReg = false;
	
	//M
	public static boolean MemWrite = false;
	public static boolean MemRead = false;
	public static boolean Branch = false;
	
	//EX
	public static boolean ALUSrc = false;
	public static boolean RegDst = false;
	public static boolean Jump = false;
	public static String ALUOp;
	
	public static int prog;
	public static String nextPC;
	public static String read1 = null;
	public static String read2;
	public static String immExtend;
	public static String rt;
	public static String rd;
	public static boolean zeroFlag;

	public Execute() {}
	
	public static void run() {
		
		if (read1 != null) {
		
		System.out.println(CPU.program[prog] + " is in Execute stage:");
		System.out.println();
		
		CPU.jump = Jump;
		CPU.jumpAddress = read1;
		
		String branchAdd = Integer.toBinaryString((int) Long.parseLong(nextPC, 2) + (int) Long.parseLong(shiftLeft(immExtend, 1), 2));
		
		while (branchAdd.length()<16)
			branchAdd = "0" + branchAdd;
		
		String aluResult;
		if (ALUSrc)
			aluResult = ALU.Evaluator(ALUOp, read1, immExtend);
		else
			aluResult = ALU.Evaluator(ALUOp, read1, read2);
		
		String regDst;
		if (RegDst)
			regDst = rt;
		else
			regDst = rd;
		
		System.out.println("zero flag: " + zeroFlag);
		System.out.println("branch address: " + branchAdd);
		
		if (ALUOp.equals("0100"))
			System.out.println("jr address: " + read1);
		else
			System.out.println("jr address: none");
		
		if (ALUOp.equals("1010") || ALUOp.equals("1011")) {
			aluResult = immExtend;
			System.out.println("ALU result/address: " + immExtend);
		}
		else
			System.out.println("ALU result/address: " + aluResult);
		
		System.out.println("register value to write to memory: " + read2);
		System.out.println("rt/rd register: " + regDst);
		
		
		System.out.println("WB controls: MemToReg: " + MemToReg + ", RegWrite: " + RegWrite);
		System.out.println("MEM controls: MemRead: " + MemRead + ", MemWrite: " + MemWrite + ", Branch: " + Branch);

		System.out.println();
		
		MemoryAccess.prog = prog;
		MemoryAccess.branchPC = branchAdd;
		MemoryAccess.zeroFlag = zeroFlag;
		MemoryAccess.aluResult = aluResult;
		MemoryAccess.writeData = read2;
		MemoryAccess.regDst = regDst;
		MemoryAccess.RegWrite = RegWrite;
		MemoryAccess.MemToReg = MemToReg;
		MemoryAccess.MemRead = MemRead;
		MemoryAccess.MemWrite = MemWrite;
		MemoryAccess.Branch = Branch;
		
		read1 = null;
		
		}
	}
	
	public static String shiftLeft (String str, int shamt) {
		
		String res = "";
		
		for (int i=shamt-1; i<str.length(); i++) 
			res += str.charAt(i);
		
		for (int j=0; j<shamt; j++) 
			res += "0";
		
		return res;
	}
	
	public static boolean inUse() {
		
		return read1 != null;
	}

}
