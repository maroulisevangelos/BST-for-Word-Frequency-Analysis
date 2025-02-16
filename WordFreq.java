public class WordFreq{
	private String key;//contains the word
	private int num;//contains the number of appearances
	
	public WordFreq(String k) {//constructor
		key = k;
		num = 1;
	}
	
	public String key() {//return the key
		return key;
	}
	
	public int num() {//return the num
		return num;
	}
	public String toString() {//returns a string with informations about WordFreq
		if (num == 1) {
			return "Word: <<"+key+">> appears 1 time.";
		}else {
			return "Word: <<"+key+">> appears "+num+" times.";
		}
	}
	
	public void addAppearance() {//add 1 to num
		num = num +1;
	}
	
	public void addIntAppearance(int n) {//add a number to num
		num += n;
	}
}