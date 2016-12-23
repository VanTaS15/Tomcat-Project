package crunchify;

import java.util.StringTokenizer;
 
/**
 * @author Crunchify.com
 *
 */
public class CrunchifyStringTokenizerAndSplit {
 
	public static void main(String[] args) {
		String delimiters = ", ";
		String string = "one two,l;three,four,,five";
 
		System.out.println("StringTokenizer Example: \n");
		StringTokenizer st = new StringTokenizer(string, delimiters);
		while (st.hasMoreElements()) {
			System.out.println("StringTokenizer Output: " + st.nextElement());
		}
 
		System.out.println("\n\nSplit Example: \n");
		String[] tokens = string.split(delimiters);
		int tokenCount = tokens.length;
		for (int j = 0; j < tokenCount; j++) {
			System.out.println("Split Output: "+ tokens[j]);
		}
	}
}