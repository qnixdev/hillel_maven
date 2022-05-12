package hw14;

import com.hw14.HappyNumberManager;
import org.junit.Assert;
import org.junit.Test;

public class HappyNumberManagerTest {
    @Test
    public void testIsHappy() {
        var manager = new HappyNumberManager();

        Assert.assertTrue(manager.isHappy(19));
        Assert.assertFalse(manager.isHappy(2));
    }
}