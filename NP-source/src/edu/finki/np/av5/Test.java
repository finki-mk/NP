package edu.finki.np.av5;


public class Test {
	public static void main(String[] args) {
		String str = "3+5*234+65";
		char[] s = str.toCharArray();
		StringBuilder num = new StringBuilder();
		for(int i = 0; i < s.length; i++) {
			if(Character.isDigit(s[i])) {
				num.append(s[i]);
			} else {
				num.append(" ");
				num.append(s[i]);
				num.append(" ");
			}
		}
		System.out.println("S: " + num.toString());
	}
}
