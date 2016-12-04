package mk.ukim.finki.np.av7;

import java.io.InputStream;
import java.util.List;

/**
 * Interface for reading numbers from InputStream
 */
public interface NumbersReader {

    List<Integer> read(InputStream inputStream);

}
