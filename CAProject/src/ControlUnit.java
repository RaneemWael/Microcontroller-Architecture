
public class ControlUnit {
	
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
		
	public ControlUnit() {}
	
	public static void setFlags (String opcode) {
		
		ALUOp = opcode;
		
		//add, sub, mul, or
		if (opcode.equals("0000") || opcode.equals("0001") || opcode.equals("0010") || opcode.equals("0011")) {
			RegWrite = true;
			RegDst = true;
			
			MemToReg = false;
			MemWrite = false;
			MemRead = false;
			Branch = false;
			ALUSrc = false;
			Jump = false;
		}
		
		//andi, addi, slti
		else if (opcode.equals("0101") || opcode.equals("0110") || opcode.equals("0111")) {
			RegWrite = true;
			ALUSrc = true;
			
			MemToReg = false;
			MemWrite = false;
			MemRead = false;
			Branch = false;
			RegDst = false;
			Jump = false;
		}
		
		//lw
		else if (opcode.equals("1000")) {
			RegWrite = true;
			MemToReg = true;
			MemRead = true;
			ALUSrc = true;
			
			MemWrite = false;
			Branch = false;
			RegDst = false;
			Jump = false;
		}
		
		//sw
		else if (opcode.equals("1001")) {
			MemWrite = true;
			ALUSrc = true;
			
			RegWrite = false;
			MemToReg = false;
			MemRead = false;
			Branch = false;
			RegDst = false;
			Jump = false;
		}
		
		//beq, blt
		else if (opcode.equals("1010") || opcode.equals("1011")) {
			Branch = true;
			
			RegWrite = false;
			MemToReg = false;
			MemWrite = false;
			MemRead = false;
			Branch = false;
			ALUSrc = false;
			RegDst = false;
			Jump = false;
		}
		
		//srl, sll
		else if (opcode.equals("1100") || opcode.equals("1101")) {
			ALUSrc = true;
			RegWrite = true;
			
			MemToReg = false;
			MemWrite = false;
			MemRead = false;
			Branch = false;
			RegDst = false;
			Jump = false;
		}
		
		//jr
		else if (opcode.equals("0100")) {
			Jump = true;
			
			RegWrite = false;
			ALUSrc = false;
			MemToReg = false;
			MemWrite = false;
			MemRead = false;
			Branch = false;
			RegDst = false;
		}
	}

}
