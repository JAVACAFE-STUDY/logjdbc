package net.chandol.logjdbc.util;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.UnicodeScript.HANGUL;

/**
 * Create asciiTable for java
 *
 * <pre>
 * AsciiTable4j table = new AsciiTable4j();
 *
 * table.addRow(Arrays.asList("id", "name", "hobby"));
 * table.addRow(Arrays.asList("1", "박세종", "Programming"));
 * table.addRow(Arrays.asList("2", "Gordon Park", "Swimming "));
 * table.addRow(Arrays.asList("3", "Sejong Park", "Playing Piano"));
 *
 * System.out.println(table.renderTable());
 *
 * // result
 * +----+-------------+---------------+
 * | id | name        | hobby         |
 * |----+-------------+---------------|
 * | 1  | 박세종      | Programming   |
 * | 2  | Gordon Park | Swimming      |
 * | 3  | Sejong Park | Playing Piano |
 * +----+-------------+---------------+
 * </pre>
 */
public class AsciiTable4j {
    private List<List<String>> rows;

    public AsciiTable4j() {
        rows = new ArrayList<>();
    }

    // row를 추가한다.
    public void addRow(List<String> row) {
        for (int idx = 0, rowSize = row.size(); idx < rowSize; idx++) {
            if (row.get(idx) == null) row.set(idx, "");
        }

        rows.add(row);
    }

    // 추가된 row를 테이블형태로 변환한다.
    public String renderTable() {
        // 각 column의 길이를 반환한다.
        List<Integer> lengthOfColumns = calculateLengthOfColumns(rows);

        StringBuilder tableBuilder = new StringBuilder();
        //상단 splitter 추가
        tableBuilder.append(rowSplitter('+', lengthOfColumns)).append("\n");

        for (int idx = 0, rowSize = rows.size(); idx < rowSize; idx++) {
            // 각 row를 문자열로 변환한다.
            tableBuilder.append(getRowString(rows.get(idx), lengthOfColumns))
                    .append("\n");

            // 첫번째 로우아래에는 splitter를 추가한다
            if (idx == 0)
                tableBuilder.append(rowSplitter('|', lengthOfColumns)).append("\n");
        }

        // 제일 아래에는 row splitter를 추가한다.
        tableBuilder.append(rowSplitter('+', lengthOfColumns));

        return tableBuilder.toString();
    }

    // row를 문자열로 변환한다.
    private StringBuilder getRowString(List<String> row, List<Integer> lengthOfColumns) {
        StringBuilder builder = new StringBuilder("|");

        int rowSize = row.size();
        for (int idx = 0; idx < rowSize; idx++) {
            int length = lengthOfColumns.get(idx);
            String item = row.get(idx);
            String paddedItem = " " + padRight(item, length) + " ";
            builder.append(paddedItem).append("|");
        }

        return builder;
    }

    // 컬럼별 최대 길이를 구한다.
    static List<Integer> calculateLengthOfColumns(List<List<String>> rows) {
        List<Integer> lengthOfColumns = new ArrayList<>();
        int columnSize = rows.get(0).size();

        for (int idx = 0; idx < columnSize; idx++) {
            int longColumnLength = -1;
            for (List<String> row : rows) {
                int itemLength = getConsoleLength(row.get(idx));
                if (longColumnLength < itemLength)
                    longColumnLength = itemLength;
            }

            lengthOfColumns.add(longColumnLength);
        }

        return lengthOfColumns;
    }

    // row 사이를 구분하는 문자열을 생성한다.
    static String rowSplitter(Character padCharacter, List<Integer> lengthOfColumns) {
        StringBuilder builder = new StringBuilder();
        builder.append(padCharacter);
        for (int i = 0, lengthOfColumnsSize = lengthOfColumns.size(); i < lengthOfColumnsSize; i++) {
            int lengthOfColumn = lengthOfColumns.get(i);
            builder.append(strFilledWithCharacter('-', lengthOfColumn + 2));

            if (i < lengthOfColumnsSize - 1)
                builder.append("+");
            else
                builder.append(padCharacter);
        }

        return builder.toString();
    }

    // 공백을 추가한다. 한글이 있는 경우에는 보정치를 더해준다.
    static String padRight(String target, int length) {
        int lengthCorrection = target.length() - getConsoleLength(target);

        int adjustedLength = length + lengthCorrection;
        return String.format("%1$-" + adjustedLength + "s", target);
    }

    // 특정 문자열로 채워진 문자열을 반환한다.
    static String strFilledWithCharacter(Character c, int length) {
        return new String(new char[length]).replace('\0', c);
    }

    // 한글인 경우, 하나의 글자당 2개의 문자열 크기를 가지도록 수정한다.
    static int getConsoleLength(String str) {
        int koreanCharacterSize = 0;
        for (int i = 0; i < str.length(); i++) {
            int codepoint = str.codePointAt(i);
            if (HANGUL == UnicodeScript.of(codepoint))
                koreanCharacterSize += 1;
        }

        return str.length() + koreanCharacterSize;
    }

}