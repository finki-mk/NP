package edu.finki.np.av3;

import java.util.Scanner;

public class CalculatorTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Calculator calculator = new Calculator();
			System.out.println(calculator.init());
			while (true) {
				String line = scanner.nextLine();
				line = line.trim();
				if(line.length() == 0) continue;
				if (line.length() == 1) {
					char r = line.charAt(0);
					r = Character.toLowerCase(r);
					if (r == 'r') {
						System.out.println(String.format("final result = %f",
								calculator.getResult()));
						break;
					}
				}
				String[] parts = line.split("\\s+");
				char operator = parts[0].charAt(0);
				try {
					double value = Double.parseDouble(parts[1]);
					String result = calculator.execute(operator, value);
					System.out.println(result);
					System.out.println(calculator);
				} catch (UnknownOperatorException e) {
					System.out.printf("Unknown operator: %s\n", e.getMessage());
				} catch(NumberFormatException e) {
					System.out.println("Second argument is not number");
				}

			}

			System.out.println("(Y/N)");
			String s = scanner.nextLine();
			char c = s.charAt(0);
			c = Character.toLowerCase(c);
			if (c == 'n') {
				break;
			}
		}
	}
}
