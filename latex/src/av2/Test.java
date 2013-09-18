package edu.finki.np.av2;

public class Test {
	public static boolean isPrefix(String str1, String str2) {
		if(str1.length() > str2.length()) {
			return false;
		}		
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static double sum(double[][] a) {
		double s = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				s += a[i][j];
			}
		}
		return s;
	}
	
	public static double avarage(double[][] a) {
		double s = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				s += a[i][j];
			}
		}
		return s / (a.length * a[0].length); 
	}
	
	public static void main(String[] args) {
		System.out.println(isPrefix("mak", "makedonija"));
		System.out.println(isPrefix("napredno programiranje", "np"));
		System.out.println(isPrefix("start", "start stop"));
	}
}
