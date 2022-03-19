public class ALU {
	
	public ALU () {}
	
	public static String Evaluator (String Op, String Operand1, String Operand2) {
			
		switch (Op) {
			
			case "0000": return add (Operand1, Operand2); 
			case "0001": return sub (Operand1, Operand2); 
			case "0010": return mul (Operand1, Operand2); 
			case "0011": return or (Operand1, Operand2); 
			case "0100": return jr (Operand1, Operand2); 
			case "0101": return andi (Operand1, Operand2); 
			case "0110": return addi (Operand1, Operand2); 
			case "0111": return slti (Operand1, Operand2); 
			case "1000": return lw (Operand1, Operand2); 
			case "1001": return sw (Operand1, Operand2); 
			case "1010": return beq (Operand1, Operand2); 
			case "1011": return blt (Operand1, Operand2); 
			case "1100": return srl (Operand1, Operand2); 
			case "1101": return sll (Operand1, Operand2); 
		}
		return null;
	}
	
	public static String add (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) + (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}

	public static String sub (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) - (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String mul (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) * (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String or (String op1, String op2) {
		
		String output = "";
		
		for (int i=0; i<op1.length(); i++) {
			
			if (op1.charAt(i) == '1' || op2.charAt(i) == '1')
				output += "1";
			else
				output += "0";
		}
		
		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String andi (String op1, String op2) {
		
		String output = "";
		
		for (int i=0; i<op1.length(); i++) {
			
			if (op1.charAt(i) == '1' && op2.charAt(i) == '1')
				output += "1";
			else
				output += "0";
		}
		
		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String addi (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) + (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String slti (String op1, String op2) {
		
		String output = "";
		
		if (Long.parseLong(op1, 2) < Long.parseLong(op2, 2))
			output = "011111111111111111111111111111111";
		
		else 
			output = "000000000000000000000000000000000";
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String lw (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) + (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String sw (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) + (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String beq (String op1, String op2) {
		
		String output = "0000000000000000";
		
		if ((int) Long.parseLong(op1, 2) == (int) Long.parseLong(op2, 2))
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String blt (String op1, String op2) {
		
		String output = "0000000000000000";
		
		if ((int) Long.parseLong(op1, 2) < (int) Long.parseLong(op2, 2))
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String srl (String str, String shamt) {
		
		int shamtInt = (int) Long.parseLong(shamt, 2);
		
		String output = "";
		
		for (int i=shamtInt-1; i<str.length(); i++) 
			output += str.charAt(i);
		
		for (int j=0; j<shamtInt; j++) 
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String sll (String str, String shamt) {
		
		int shamtInt = (int) Long.parseLong(shamt, 2);
		
		String output = "";
		
		for (int i=shamtInt-1; i<str.length(); i++) 
			output += str.charAt(i);
		
		for (int j=0; j<shamtInt; j++) 
			output += "0";
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}
	
	public static String jr (String op1, String op2) {
		
		String output = Integer.toBinaryString((int) Long.parseLong(op1, 2) + (int) Long.parseLong(op2, 2));

		while (output.length()<16)
			output = "0" + output;
		
		if ((int) Long.parseLong(output, 2) == 0)
			Execute.zeroFlag = true;
		else
			Execute.zeroFlag = false;
		
		return output;
	}

}
