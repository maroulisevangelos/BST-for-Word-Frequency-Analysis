import java.util.Comparator;

public class StringComparator implements Comparator<String> {//finds which string is bigger than the other 
	/**
	 * Compares two strings
	 * 
	 * @param String t1, String t2
	 * @return 1 if t1>t2, -1 if t1<t2
	 */
    @Override
    public int compare(String t1, String t2) {
        for(int i = 0;i<Math.min(t1.length(),t2.length());i++) {//compares every char of every string until the end of minimum length
        	if (t1.charAt(i)>t2.codePointAt(i)) {
        		return 1;
        	}else if (t1.charAt(i)<t2.codePointAt(i)) {
        		return -1;
        	}
        }
        if (t1.length()>t2.length()) {//if both of them are the same until this char compare the lengths
        	return 1;
        }else {
        	return -1;
        }
    }
}