package hw12;

import com.hw12.JewelsAndStones;
import org.junit.Assert;
import org.junit.Test;

public class JewelsAndStonesTest {
    @Test
    public void testNumJewelsInStones() {
        var jewelsAndStones = new JewelsAndStones();

        Assert.assertEquals(3, jewelsAndStones.numJewelsInStones("aA", "aAAbbbb"));
        Assert.assertNotEquals(1, jewelsAndStones.numJewelsInStones("z", "ZZ"));
    }
}