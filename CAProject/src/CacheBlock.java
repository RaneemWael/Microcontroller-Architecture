
public class CacheBlock {
	
	//last 9 bits of the address
	public String index;
	//first 7 bits of the address
	public String tag;
	public boolean valid = false;
	public String data;
	public int indexCount = 0;
	
	public CacheBlock () {
		
		index = Integer.toBinaryString(indexCount);
		
		while (index.length()<9)
			index = "0" + index;
		
		tag = "";
		valid = false;
		data = "";
		
		indexCount++;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
