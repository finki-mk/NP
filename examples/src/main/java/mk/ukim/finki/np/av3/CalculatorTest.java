package mk.ukim.finki.np.av3;

import java.util.Scanner;

import edu.finki.np.av3.Calculator.UnknownOperatorException;

public class CalculatorTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Calculator calculator = new Calculator();
			System.out.println(calculator.init());
			while (true) {
				String line = scanner.nextLine();
				if (line.length() == 1) {
					char r = line.charAt(0);
					r = Character.toLowerCase(r);
					if (r == 'r') {
						System.out.println(String.format("final result = %f",
								calculator.getResult()));
						break;
					}
				}
				String[] parts = line.split(" ");
				char operator = parts[0].charAt(0);
				double value = Double.parseDouble(parts[1]);
				try {
					String result = calculator.execute(operator, value);
					System.out.println(result);
					System.out.println(calculator);
				} catch (UnknownOperatorException e) {
					System.out.println(e.getMessage());
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
