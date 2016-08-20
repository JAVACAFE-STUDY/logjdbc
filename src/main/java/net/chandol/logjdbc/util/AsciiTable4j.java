package net.chandol.logjdbc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * forked at "https://github.com/jvanhouteghem/java.AsciiTable4j"
 *
 * change showTable method to return String value
 */
public class AsciiTable4j {

    /**
     * ********************************************************
     * Description :
     * .........................................................
     * <p>
     * AsciiTable4j is a Minimalistic Ascii Table Generator for Java
     *
     * @author Jonathan Vanhouteghem - http://vanhouteghem-jonathan.fr/
     * Last edit : 2016-03-19
     * <p>
     * ********************************************************
     * Using example :
     * .........................................................
     * <p>
     * AsciiTable4j t = new AsciiTable4j();
     * <p>
     * // create table
     * t.addRow(Arrays.asList("Name", "Age", "Hobbies", "Married"));
     * t.addRow(Arrays.asList("John", "22", "KravMaga", "No"));
     * t.addRow(Arrays.asList("Alexandra", "28", "Painting", "No"));
     * t.addRow(Arrays.asList("Quentin", "32", "Running", "Yes"));
     * t.addRow(Arrays.asList("Sebastien", "36", "VideoGames", "Yes"));
     * t.addRow(Arrays.asList("Jeanine", "60", "Sew", "Yes"));
     * <p>
     * // show table
     * t.showTable();
     * <p>
     * ********************************************************
     * Output :
     * .........................................................
     * | Name      | Age | Hobbies    | Married |
     * ------------------------------------------
     * | John      | 22  | KravMaga   | No      |
     * | Alexandra | 28  | Painting   | No      |
     * | Quentin   | 32  | Running    | Yes     |
     * | Sebastien | 36  | VideoGames | Yes     |
     * | Jeanine   | 60  | Sew        | Yes     |
     * <p>
     * ********************************************************
     */


    ArrayList<ArrayList<String>> baseArray = new ArrayList<ArrayList<String>>();


    /**
     * Add row in the table
     *
     * @param linputRow Row in ArrayList format
     */
    public void addRow(List<String> linputRow) {

        ArrayList<String> row1 = new ArrayList<String>();
        for (String l : linputRow) {
            row1.add(l);
        }
        baseArray.add(row1);
    }


    /**
     * Add a cell to the specified row in the table
     *
     * @param rowNumber
     */
    public void addCell(int rowNumber, String input) {
        baseArray.get(rowNumber).add(input);
    }


    /**
     * Show ArrayList
     */
    public void showArray() {
        for (ArrayList<String> currentRow : baseArray) {
            System.out.println(currentRow);
        }
    }


    /**
     * Show Table
     */
    public String renderTable() {
        formatTable(false);
        StringBuilder builder = new StringBuilder();

        int j = 0;
        for (ArrayList<String> currentRow : baseArray) {
            if (j == 0) {
                builder.append(addsStripes(getRowWidth(0), getNumberOfCells(0)) + "\n");
            }

            for (int i = 0; i < currentRow.size(); i++) {
                // Add pipe at the start of the cell unless this is the last cell of the row table
                if (i < currentRow.size() && i + 1 != currentRow.size()) {
                    builder.append("|" + baseArray.get(j).get(i));
                } else {
                    builder.append("|" + baseArray.get(j).get(i) + "|");
                }
            }
            if (j == 0) {
                builder.append("\n" + addsStripes(getRowWidth(0), getNumberOfCells(0))); // number of celles
            }

            builder.append("\n");
            j++;
        }

        builder.append(addsStripes(getRowWidth(0), getNumberOfCells(0)) + "\n");


        return builder.toString();
    }


    /**
     * Add spaces
     *
     * @param nbSpaces
     * @return
     */
    public String addSpaces(int nbSpaces) {
        String output = "";
        for (int i = 0; i < nbSpaces; i++) {
            output = output + " ";
        }
        return output;
    }


    /**
     * Get the number of cells of the specified row
     *
     * @param rowIndex
     * @return
     */
    public int getNumberOfCells(int rowIndex) {
        int numberOfCells = 0;
        for (int iCellIndex = 0; iCellIndex < baseArray.get(rowIndex).size(); iCellIndex++) {
            numberOfCells++;
        }
        return numberOfCells;
    }


    /**
     * Edit cell
     *
     * @param iRowIndex
     * @param iCellIndex
     * @param sValue     The input value
     */
    public void editCell(int iRowIndex, int iCellIndex, String sValue) {
        baseArray.get(iRowIndex).set(iCellIndex, sValue);
    }


    /**
     * Get max length of the string in the array
     *
     * @return
     */
    public int getMaxLenghtOfStringInTable() {
        int maxLenght = 0;
        for (ArrayList<String> l : baseArray) {
            for (int i = 0; i < l.size(); i++) {
                maxLenght = ((l.get(i).length() - countSpaces(l.get(i))) > maxLenght) ? l.get(i).length() - countSpaces(l.get(i)) : maxLenght;
            }
        }
        return maxLenght;
    }


    /**
     * Récupére la taille maximale du string dans la colonne
     *
     * @param input
     * @return
     */
    public int getMaxLenghtOfStringInColumn(int input) {
        int maxLenght = 0;
        int i = 0;
        for (ArrayList<String> l : baseArray) {
            maxLenght = ((l.get(input).length() - countSpaces(l.get(input))) > maxLenght) ? l.get(input).length() - countSpaces(l.get(input)) : maxLenght;
            i++;
        }
        return maxLenght;
    }


    /**
     * Format Array to make the table which contains spaces and pipes
     *
     * @param uniform
     */
    public void formatTable(boolean uniform) {
        int maxLength = getMaxLenghtOfStringInTable();
        int iRowIndex = 0;
        // if uniform mod true
        if (uniform) {
            for (ArrayList<String> iRow : baseArray) {
                for (int iCellIndex = 0; iCellIndex < iRow.size(); iCellIndex++) {
                    editCell(iRowIndex, iCellIndex, addSpaces(1) + iRow.get(iCellIndex) + addSpaces(maxLength - iRow.get(iCellIndex).length() + 1));
                }
                iRowIndex++;
            }
        }
        // else
        else {
            for (ArrayList<String> iRow : baseArray) {
                for (int iCellIndex = 0; iCellIndex < iRow.size(); iCellIndex++) {
                    try {
                        // 1 space + text + (max column length - text length + 1 ) spaces
                        String insert = addSpaces(1) + iRow.get(iCellIndex) + addSpaces(getMaxLenghtOfStringInColumn(iCellIndex) - iRow.get(iCellIndex).length() + 1);
                        editCell(iRowIndex, iCellIndex, insert);
                    } catch (Exception e) {
                    }
                }
                iRowIndex++;
            }
        }
    }


    public int countSpaces(String s) {
        int spaces = s.length() - s.replace(" ", "").length();
        return spaces;
    }


    public int getRowWidth(int rowIndex) {
        int rowWidth = 0;
        for (int iCellIndex = 0; iCellIndex < baseArray.get(rowIndex).size(); iCellIndex++) {
            rowWidth = rowWidth + baseArray.get(rowIndex).get(iCellIndex).length();
        }

        return rowWidth;
    }


    public String addsStripes(int barretteNumber, int numberofCells) {
        String stripes = "";

        for (int i = 0; i < barretteNumber; i++) {
            stripes = stripes + "-";
        }

        // add missing number about cells
        for (int i = 0; i < numberofCells; i++) {
            stripes = stripes + "-";
        }

        return stripes + "-";
    }

}