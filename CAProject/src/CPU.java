public class CPU {
	
	public static Cache Cache = new Cache();
	public static Memory Memory = new Memory();
	public static RegisterFile RegisterFile = new RegisterFile();
	public static String PC = "00000000000000000000000000000000";
	public static String nextPC;
	public static String[] program = new String[14];
	public static int progCount = 14;
	public static int currentCycle = 0;
	public static boolean PCSrc = false;
	public static boolean jump = false;
	public static String branchAddress;
	public static String jumpAddress;

	public CPU () {}

	public static void main(String[] args) {
				
		load();
		
		currentCycle++;	
		System.out.println("After clock-cycle: " + currentCycle);
		System.out.println();
		
		Fetch.run();
		
		PCIncrement();
		
		System.out.println("---------------------------------------------------------------------------");
		
		while (Fetch.inUse() || WriteBack.inUse() || Decode.inUse() || Execute.inUse() || MemoryAccess.inUse()) {
					
			currentCycle++;	
			System.out.println("After clock-cycle: " + currentCycle);
			System.out.println();
			
			WriteBack.run();
			MemoryAccess.run();
			Execute.run();
			Decode.run();
			Fetch.run();
			
			PCIncrement();
			
			System.out.println("---------------------------------------------------------------------------");
		}
		
	}
	
	public static void load () {
		
		//R type
		program[0] = "add $r4, $r2, $r3";
		String addInstr = "0000010001010110";
		program[1] = "sub $r8, $r6, $r3";
		String subInstr = "0001100001011010";
		program[2] = "mul $r6, $r0, $r3";
		String mulInstr = "0010001001011000";
		program[3] = "or $r7, $r5, $r6";
		String orInstr = "0011011110001001";

		//I type
		program[4] = "andi $r7, $r5, 6";
		String andiInstr = "0101011110010110";
		program[5] = "addi $r4, $r5, 10";
		String addiInstr = "0110011101101010";
		program[6] = "slti $r9, $r2, 4";
		String sltiInstr = "0111010010110100";
		program[7] = "lw $r1, 0($m0)";
		String lwInstr = "1000110000110000";
		program[8] = "sw $m2, 0($m1)";
		String swInstr = "1001110111100000";

		//S type
		program[9] = "srl $r6, $r0, 5";
		String srlInstr = "1100001010000101";
		program[10] = "sll $r7, $r5, 3";
		String sllInstr = "1101011110010011";
		
		//
		program[11] = "jr $ja";
		String jInstr = "0100000100000000";
		program[12] = "beq $r4, $r5, HEY (at 0000000000001111)";
		String beqInstr = "1010011101101111";
		program[13] = "blt $r1, $r2, BYE (at 0000000000001101)";
		String bltInstr = "1011010000111101";
		
		Memory.mem[0] = addInstr.substring(0, 8);
		Memory.mem[1] = addInstr.substring(8, 16);
		
		Memory.mem[2] = subInstr.substring(0, 8);
		Memory.mem[3] = subInstr.substring(8, 16);
		
		Memory.mem[4] = mulInstr.substring(0, 8);
		Memory.mem[5] = mulInstr.substring(8, 16);
		
		Memory.mem[6] = orInstr.substring(0, 8);
		Memory.mem[7] = orInstr.substring(8, 16);
		
		Memory.mem[8] = andiInstr.substring(0, 8);
		Memory.mem[9] = andiInstr.substring(8, 16);
		
		Memory.mem[10] = addiInstr.substring(0, 8);
		Memory.mem[11] = addiInstr.substring(8, 16);
		
		Memory.mem[12] = sltiInstr.substring(0, 8);
		Memory.mem[13] = sltiInstr.substring(8, 16);
		
		Memory.mem[14] = lwInstr.substring(0, 8);
		Memory.mem[15] = lwInstr.substring(8, 16);
		
		Memory.mem[16] = swInstr.substring(0, 8);
		Memory.mem[17] = swInstr.substring(8, 16);
		
		Memory.mem[18] = srlInstr.substring(0, 8);
		Memory.mem[19] = srlInstr.substring(8, 16);
		
		Memory.mem[20] = sllInstr.substring(0, 8);
		Memory.mem[21] = sllInstr.substring(8, 16);
		
		Memory.mem[22] = jInstr.substring(0, 8);
		Memory.mem[23] = jInstr.substring(8, 16);

		Memory.mem[24] = beqInstr.substring(0, 8);
		Memory.mem[25] = beqInstr.substring(8, 16);
		
		Memory.mem[26] = bltInstr.substring(0, 8);
		Memory.mem[27] = bltInstr.substring(8, 16);
	}
	
	public static void PCIncrement () {
		
		if (PCSrc)
			PC = branchAddress;
		else if (jump)
			PC = jumpAddress;
		else
			PC = nextPC;
	}

}
