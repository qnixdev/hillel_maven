package hw12;

import com.hw12.RomanParser;
import org.junit.Assert;
import org.junit.Test;

public class RomanParserTest {
    @Test
    public void testRomanToInt() {
        RomanParser romanParser = new RomanParser();
        int parseNumber = romanParser.romanToInt("LVIII");

        Assert.assertEquals(58, parseNumber);
    }
}