package hw21;

import com.hw21.WordPattern;
import org.junit.Assert;
import org.junit.Test;

public class WordPatternTest {
    @Test
    public void testWordPattern() {
        var wordPattern = new WordPattern();

        String pattern = "abba";
        String s1 = "dog cat cat dog";
        String s2 = "dog cat cat fish";

        Assert.assertTrue(wordPattern.wordPattern(pattern, s1));
        Assert.assertFalse(wordPattern.wordPattern(pattern, s2));
    }
}