package net.chandol.logjdbc.util;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.UnicodeScript.HANGUL;

public class AsciiTable4j {
    private List<List<String>> rows;

    public AsciiTable4j() {
        rows = new ArrayList<>();
    }

    public void addRow(List<String> row) {
        for (int idx = 0, rowSize = row.size(); idx < rowSize; idx++) {
            if (row.get(idx) == null) row.set(idx, "");
        }

        rows.add(row);
    }

    public String renderTable() {
        List<Integer> lengthOfColumns = calculateLengthOfColumns();

        StringBuilder builder = new StringBuilder();
        builder.append(rowSplitter('+', lengthOfColumns)).append("\n");
        int rowSize = rows.size();
        for (int idx = 0; idx < rowSize; idx++) {
            StringBuilder row = getRowString(rows.get(idx), lengthOfColumns);
            builder.append(row).append("\n");
            if (idx == 0)
                builder.append(rowSplitter('|', lengthOfColumns)).append("\n");
        }
        builder.append(rowSplitter('+', lengthOfColumns));

        return builder.toString();
    }

    private String rowSplitter(Character padCharacter, List<Integer> lengthOfColumns) {
        StringBuilder builder = new StringBuilder();
        builder.append(padCharacter);
        for (int i = 0, lengthOfColumnsSize = lengthOfColumns.size(); i < lengthOfColumnsSize; i++) {
            int lengthOfColumn = lengthOfColumns.get(i);
            builder.append(repeatCharacter('-', lengthOfColumn + 2));

            if (i < lengthOfColumnsSize - 1)
                builder.append("+");
            else
                builder.append(padCharacter);
        }

        return builder.toString();
    }

    private List<Integer> calculateLengthOfColumns() {
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

    public StringBuilder getRowString(List<String> row, List<Integer> lengthOfColumns) {
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

    static String padRight(String target, int length) {
        int lengthCorrection = target.length() - getConsoleLength(target);

        int adjustedLength = length + lengthCorrection;
        return String.format("%1$-" + adjustedLength + "s", target);
    }

    static String repeatCharacter(Character c, int length) {
        return new String(new char[length]).replace('\0', c);
    }

    static int getConsoleLength(String str) {
        int koreanChracterSize = 0;
        for (int i = 0; i < str.length(); i++) {
            int codepoint = str.codePointAt(i);
            if (HANGUL == UnicodeScript.of(codepoint))
                koreanChracterSize += 1;
        }

        return str.length() + koreanChracterSize;
    }

}