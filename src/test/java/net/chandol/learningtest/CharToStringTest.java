package net.chandol.learningtest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharToStringTest {
    @Test
    public void charArrTest(){
        char[] charArr = "Tester".toCharArray();
        assertTrue(charArr.getClass().isAssignableFrom(char[].class));
    }
}
