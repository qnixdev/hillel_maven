package hw12;

import com.hw12.GoodPairs;
import org.junit.Assert;
import org.junit.Test;

public class GoodPairsTest {
    @Test
    public void testNumIdenticalPairs() {
        GoodPairs goodPairs = new GoodPairs();

        var arrayOne = new int[] {1, 2, 3, 1, 1, 3};
        var arrayTwo = new int[] {1, 2, 3};

        Assert.assertEquals(4, goodPairs.numIdenticalPairs(arrayOne));
        Assert.assertNotEquals(1, goodPairs.numIdenticalPairs(arrayTwo));
    }
}