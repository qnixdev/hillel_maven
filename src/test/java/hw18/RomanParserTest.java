package hw18;

import com.hw18.RomanParser;
import org.junit.Assert;
import org.junit.Test;

public class RomanParserTest {
    @Test
    public void testRomanToInt() {
        var romanParser = new RomanParser();

        Assert.assertEquals(58, romanParser.romanToInt("LVIII"));
        Assert.assertNotEquals(2, romanParser.romanToInt("III"));
    }
}