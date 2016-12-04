package mk.ukim.finki.np.av7;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Count visualizer using one * per n units
 */
public class CountVisualizer {
    private final int n;

    public CountVisualizer(int n) {
        this.n = n;
    }

    public void visualize(OutputStream outputStream, int[] counts) {
        PrintWriter writer = new PrintWriter(outputStream);
        for (Integer count : counts) {
            while (count > 0) {
                writer.print("*");
                count -= n;
            }
            writer.println();
        }
        writer.flush();
    }
}
