
public class Decode {

	public static int prog;
	public static String nextPC;
	public static String instr = null;
	
	public Decode() {}
	
	public static void run() {
		
		if (instr != null) {
		
		System.out.println(CPU.program[prog] + " is in Decode stage:");
		System.out.println();
		
		String opcode = instr.substring(0, 4);
		ControlUnit.setFlags(opcode);
		
		CPU.RegisterFile.decode(instr);
		
		String immExtend;
		
		if (opcode.equals("0101"))
			immExtend = zeroExtend(instr.substring(12, 16));
		else
			immExtend = signExtend(instr.substring(12, 16));
		
		System.out.println("sign-extend: " + immExtend);
		
		System.out.println("Next PC: " + nextPC);
		
		String rd;

		if (ControlUnit.ALUSrc)
			rd = "don't care";
		else 
			rd = instr.substring(12, 16);
		
		System.out.println("rt: " + instr.substring(8, 12));
		System.out.println("rd: " + rd);
		
		System.out.println("WB controls: MemToReg: " + ControlUnit.MemToReg + ", RegWrite: " + ControlUnit.RegWrite);
		System.out.println("MEM controls: MemRead: " + ControlUnit.MemRead + ", MemWrite: " + ControlUnit.MemWrite + ", Branch: " + ControlUnit.Branch);
		System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp + ", ALUSrc: " + ControlUnit.ALUSrc + ", Jump: " + ControlUnit.Jump);

		System.out.println();
		
		Execute.prog = prog;
		Execute.nextPC = nextPC;
		Execute.immExtend = immExtend;
		Execute.RegWrite = ControlUnit.RegWrite;
		Execute.MemToReg = ControlUnit.MemToReg;
		Execute.MemWrite = ControlUnit.MemWrite;
		Execute.MemRead = ControlUnit.MemRead;
		Execute.Branch = ControlUnit.Branch;
		Execute.RegDst = ControlUnit.RegDst;
		Execute.ALUOp = ControlUnit.ALUOp;
		Execute.ALUSrc = ControlUnit.ALUSrc;
		Execute.Jump = ControlUnit.Jump;
		Execute.rt = instr.substring(8, 12);
		Execute.rd = instr.substring(12, 16);
		
		instr = null;
		
		}
	}
	
	public static String signExtend(String str) {
		
		char first = str.charAt(0);
		
		while (str.length()<16)
			str = first + str;
		
		return str;
	}
	
	public static String zeroExtend(String str) {
		
		while (str.length()<16)
			str = "0" + str;
		
		return str;
	}
	
	public static boolean inUse() {
		
		return instr != null;
	}

}
