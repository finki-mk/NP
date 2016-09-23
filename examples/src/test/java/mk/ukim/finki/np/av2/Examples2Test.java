package mk.ukim.finki.np.av2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests
 */
public class Examples2Test {

    @Test
    public void testIsPrefix() {
        String prefix = "mak";
        String word = "makedonija";
        assertTrue(Examples2.isPrefix(prefix, word));
    }

    @Test
    public void testIsPrefixEqual() {
        String prefix = "Macedonia";
        String word = "Macedonia";
        assertTrue(Examples2.isPrefix(prefix, word));
    }

    @Test
    public void testIsNotPrefixLonger() {
        String prefix = "makedonija1";
        String word = "makedonija";
        assertFalse(Examples2.isPrefix(prefix, word));
    }

    @Test
    public void testNotPrefix() {
        String prefix = "mkd";
        String word = "makedonija";
        assertFalse(Examples2.isPrefix(prefix, word));
    }

    @Test
    public void testSum() {
        double[][] numbers = {
                {1, 2},
                {3, 4}
        };
        double sum = Examples2.sum(numbers);
        assertEquals(10, sum, 0.001);
    }

    @Test
    public void testAverage() {
        double[][] numbers = {
                {1, 2},
                {3, 4}
        };
        double average = Examples2.average(numbers);
        assertEquals(2.5, average, 0.001);
    }

}