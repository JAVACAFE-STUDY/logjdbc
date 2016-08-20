package net.chandol.logjdbc.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AsciiTable4jTest {

    @Test
    public void 아스키테이블만들기() {
        AsciiTable4j table = new AsciiTable4j();

        // create table
        table.addRow(Arrays.asList("이름", "나이", "취미", "Married"));
        table.addRow(Arrays.asList("John", "22", "KravMaga", "No"));
        table.addRow(Arrays.asList("Alexandra", "28", "Painting", "No"));
        table.addRow(Arrays.asList("Quentin", "32", "Running", "Yes"));
        table.addRow(Arrays.asList("Sebastien", "36", "VideoGames", "Yes"));
        table.addRow(Arrays.asList("Jeanine", "60", "Sew", "Yes"));

        String result = table.renderTable();

        List<String> lineByLineResult = Arrays.asList(result.split("\n"));

        System.out.println(result);

        assertThat(lineByLineResult.size(), is(9));
        assertThat(lineByLineResult.get(1), is("| 이름        | 나이 | 취미         | Married |"));
    }

    @Test
    public void 한글일경우길이를2로계산(){
        //given
        String str = "안녕하세요. Hello!!";

        //when
        int consoleLength = AsciiTable4j2.getConsoleLength(str);

        //then
        assertThat(str.length(), is(14));
        assertThat(consoleLength, is(19));
    }

}