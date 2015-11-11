package edu.finki.np.av3;

class UnknownOperatorException extends Exception {

	public UnknownOperatorException(String msg) {
		super(msg);
	}
}

public class Calculator {
	private double result;
	public static final char PLUS = '+';
	public static final char MINUS = '-';
	public static final char MULTIPLY = '*';
	public static final char DIVIDE = '/';

	public Calculator() {
		result = 0;
	}

	public String execute(char operator, double value)
			throws UnknownOperatorException {
		if (operator == PLUS) {
			result += value;
		} else if (operator == MINUS) {
			result -= value;
		} else if (operator == MULTIPLY) {
			result *= value;
		} else if (operator == DIVIDE) {
			result /= value;
		} else {
			throw new UnknownOperatorException(String.valueOf(operator));
		}
		return String.format("result = %.2f", result);
	}
	
	public double getResult() {
		return result;
	}
	
	public String init() {
		result = 0;
		return String.format("result = %.2f", result);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
