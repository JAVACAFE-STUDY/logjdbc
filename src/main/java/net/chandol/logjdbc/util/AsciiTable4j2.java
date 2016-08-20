package net.chandol.logjdbc.util;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.UnicodeScript.HANGUL;

/**
 * 기존 AsciiTable이 구려서 직접 만드는 중
 */
public class AsciiTable4j2 {
    private List<List<String>> table;

    public AsciiTable4j2(){
        table = new ArrayList<>();
    }

    public void addRow(List<String> row) {
        table.add(row);
    }


    public String renderTable() {
        // 1. 각 컬럼별 최대길이를 측정한다.
        // 1-1 첫번째 로우를 기준으로 for문을 돌린다.


        return null;
    }

    static int getConsoleLength(String str){
        int koreanChracterSize = 0;
        for (int i = 0; i < str.length(); i++) {
            int codepoint = str.codePointAt(i);
            if (HANGUL == UnicodeScript.of(codepoint))
                koreanChracterSize += 1;
        }

        return str.length() + koreanChracterSize;
    }

}