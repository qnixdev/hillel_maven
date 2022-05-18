package hw14;

import com.hw14.UniqueMorseParser;
import org.junit.Assert;
import org.junit.Test;

public class UniqueMorseParserTest {
    @Test
    public void testUniqueMorseRepresentations() {
        UniqueMorseParser morseParser = new UniqueMorseParser();

        var arrayOne = new String[] {"gin", "zen", "gig", "msg"};
        var arrayTwo = new String[] {"a", "a"};

        Assert.assertEquals(2, morseParser.uniqueMorseRepresentations(arrayOne));
        Assert.assertNotEquals(2, morseParser.uniqueMorseRepresentations(arrayTwo));
    }
}