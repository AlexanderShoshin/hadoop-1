package shoshin.alex.utils;

import org.junit.Assert;
import org.junit.Test;

public class WordsTest {
    @Test
    public void should_return_true_if_russian_word() {
        Assert.assertTrue(Words.isRussianWord("привет"));
    }

    @Test
    public void should_return_false_if_contains_not_cyrillic() {
        Assert.assertFalse(Words.isRussianWord("hello"));
    }

    @Test
    public void should_crop_punctuation_marks() {
        Assert.assertEquals(Words.removePunctuationMarks("(привет)!.."), "привет");
    }
}