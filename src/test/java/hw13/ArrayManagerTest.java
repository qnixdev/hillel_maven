package hw13;

import com.hw13.ArrayManager;
import org.junit.Assert;
import org.junit.Test;

public class ArrayManagerTest {
    @Test
    public void testMaxProduct() {
        ArrayManager arrayManager = new ArrayManager();

        var arrayOne = new int[] {3, 4, 5, 2};
        var arrayTwo = new int[] {2, 2};

        Assert.assertEquals(12, arrayManager.maxProduct(arrayOne));
        Assert.assertNotEquals(2, arrayManager.maxProduct(arrayTwo));
    }

    @Test
    public void testSortedSquares() {
        ArrayManager arrayManager = new ArrayManager();

        var arrayOne = new int[] {-4, -1, 0, 3, 10};
        var arrayTwo = new int[] {2, -2};

        var answerTwo = arrayManager.sortedSquares(arrayTwo);

        Assert.assertArrayEquals(new int[] {0, 1, 9, 16, 100}, arrayManager.sortedSquares(arrayOne));
        Assert.assertNotEquals(5, answerTwo[0]);
        Assert.assertNotEquals(5, answerTwo[1]);
    }
}