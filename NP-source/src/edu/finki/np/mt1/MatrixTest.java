package edu.finki.np.mt1;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MatrixTest {
	public static void main(String[] args) {
		try {
			Matrix mat = new Matrix(System.in);
			mat.write(System.out);
		} catch (MatrixInvalidDimensions e) {
			System.out.println(e.getMessage());
		}
	}
}


class Matrix {
	private double[][] x;
	private int n, m;

	public Matrix(InputStream inputStream) throws MatrixInvalidDimensions {
        Scanner scanner = new Scanner(inputStream);
		this.m = scanner.nextInt();
		this.n = scanner.nextInt();
		if(this.m < 0 || this.n < 0) {
			throw new MatrixInvalidDimensions("INVALID DIMENSIONS");
		}
		x = new double[m][];
		for (int i = 0; i < m; i++) {
			x[i] = new double[n];
		}
        scanner.nextLine();
        for(int i = 0; i < this.m; i++) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			for(int j = 0; j < this.n; j++) {
				x[i][j] = Double.parseDouble(parts[j]);
			}
		}
	}
	
	public void write(OutputStream outputStream) {
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.print(String.format("%d %d\n", this.n, this.m));
		for(int i = 0; i < this.m; i++) {
			for(int j = 0; j < this.n; j++) {
				printWriter.print(String.format("%10.2f", x[i][j]));
			}
			printWriter.print('\n');
		}
		printWriter.flush();
	}
	
}
class MatrixInvalidDimensions extends Exception {
	MatrixInvalidDimensions(String msg) {
        super(msg);
    }
}

