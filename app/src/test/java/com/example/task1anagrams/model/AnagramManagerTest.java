package com.example.task1anagrams.model;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;

public class AnagramManagerTest {
    private final HashSet<Character> numbers =
            new HashSet<>(Arrays.asList('1','2','3','4','5','6','7','8','9','0','!','/'));
    private final HashSet<Character> xl = new HashSet<>(Arrays.asList('x','l'));

    @Test
    public void getReverseWords() {
        Assert.assertEquals("dednimxoF looc 7/42",
                new AnagramManager("Foxminded cool 24/7", new HashSet<>()).getReverseWords());
        Assert.assertEquals("dednimxoF looc 24/7",
                new AnagramManager("Foxminded cool 24/7", numbers).getReverseWords());
        Assert.assertEquals("dexdnimoF oocl 7/42",
                new AnagramManager("Foxminded cool 24/7", xl).getReverseWords());

        Assert.assertEquals("dcba hgfe",
                new AnagramManager("abcd efgh", new HashSet<>()).getReverseWords());
        Assert.assertEquals("dcba hgfe",
                new AnagramManager("abcd efgh", numbers).getReverseWords());
        Assert.assertEquals("dcba hgfe",
                new AnagramManager("abcd efgh", xl).getReverseWords());

        Assert.assertEquals("dcb1a hlgfe",
                new AnagramManager("a1bcd efglh", new HashSet<>()).getReverseWords());
        Assert.assertEquals("d1cba hgf!e",
                new AnagramManager("a1bcd efg!h", numbers).getReverseWords());
        Assert.assertEquals("dcb1a hgfle",
                new AnagramManager("a1bcd efglh", xl).getReverseWords());
    }
}