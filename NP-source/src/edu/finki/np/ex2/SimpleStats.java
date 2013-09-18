package edu.finki.np.ex2;

import java.util.List;

public class SimpleStats {
	public static double max(List<? extends Number> list) {
		return 0;
	}
	
	public static double min(List<? extends Number> list) {
		return 0;
	}
	
	public static double avarage(List<? extends Number> list) {
		return 0;
	}
	
	public static double sum(List<? extends Number> list) {
		double sum = 0;
		for(Number n : list) {
			sum += n.doubleValue();
		}
		return sum;
	}
}
