import java.util.Arrays;

public class RegisterFile {
	
	public boolean RegWrite = false;
	public String[] regValues = new String[16];

	public RegisterFile() {
		
		Arrays.fill(regValues, "0000000000000000");
	}
	
	public void decode (String instr) {
		
		String rs = instr.substring(4, 8);
		String rt = instr.substring(8, 12);
			
		String read1 = getValue(rs);
		System.out.println("read data 1: " + read1);
		String read2 = getValue(rt);
		System.out.println("read data 2: " + read2);
		
		Execute.read1 = read1;
		Execute.read2 = read2;
	}
	
	public String getValue (String regNum) {

		return regValues[(int) Long.parseLong(regNum, 2)];
	}
	
	public void setWriteReg (String regNum, String data) {
		
		if (RegWrite)
			regValues[(int) Long.parseLong(regNum, 2)] = data;
	}

}
