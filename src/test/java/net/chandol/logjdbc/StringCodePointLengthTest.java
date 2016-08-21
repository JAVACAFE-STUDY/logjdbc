package net.chandol.logjdbc;

import org.junit.Test;

import java.lang.Character.UnicodeScript;

import static java.lang.Character.UnicodeScript.HANGUL;

public class StringCodePointLengthTest {

    @Test
    public void codePointTest() {
        String target = "안녕하세요. Hello!";

        for (int i = 0; i < target.length(); i++) {
            int codepoint = target.codePointAt(i);
            UnicodeScript type = UnicodeScript.of(codepoint);
            System.out.println("character : " + target.charAt(i) + "  type : " + type);

//            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HANGUL) {
//                System.out.println(target.indexOf(codepoint));
//            }
        }
    }

    @Test
    public void codePointGetLengthTest() {
        String target = "안녕하세요. Hello!";

        int koreanChracterSize = 0;
        for (int i = 0; i < target.length(); i++) {
            int codepoint = target.codePointAt(i);
            if (HANGUL == UnicodeScript.of(codepoint))
                koreanChracterSize += 1;
        }
        int length = target.length() + koreanChracterSize;

        System.out.println(target.length());
        System.out.println(length);
    }
}
