package mk.ukim.finki.np.av2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Combination Lock tests
 */
public class CombinationLockTest {

    CombinationLock combinationLock;
    static final int COMBO = 555;

    @Before
    public void setup() {
        combinationLock = new CombinationLock(COMBO);
    }

    @Test
    public void testIsNotOpen() {
        assertFalse(combinationLock.isOpen());
    }

    @Test
    public void testChangeComboSuccess() {
        int oldCombo = COMBO;
        int newCombo = 666;
        assertTrue(combinationLock.changeCombo(oldCombo, newCombo));
        assertFalse(combinationLock.open(oldCombo));
        assertTrue(combinationLock.open(newCombo));
    }

}