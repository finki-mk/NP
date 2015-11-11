package edu.finki.np.av2;

public class Test {
	public static boolean isPrefix(String s1, String s2) {
		if(s1.length() > s2.length()) return false;
		for(int i = 0; i < s1.length(); ++i) {
			if(s1.charAt(i) != s2.charAt(i)) return false;
		}
		return true;
	}
	
	public static double sum(double[][] x) {
		double sum = 0;
		for(int i = 0; i < x.length; ++i) {
			for(int j = 0; j < x[i].length; ++j) {
				sum += x[i][j];
			}
		}
		return sum;
	}
	
	public static double avg(double[][] x) {
		return sum(x) / (x.length * x[0].length);
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(isPrefix("abc", "a"));
		CombinationLock cl;
	}
}
