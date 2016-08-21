package net.chandol.logjdbc.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AsciiTable4jTest {
    @Test
    public void renderTable() throws Exception {
        AsciiTable4j table = new AsciiTable4j();

        table.addRow(Arrays.asList("id", "name", "hobby"));
        table.addRow(Arrays.asList("1", "박세종", "Programming"));
        table.addRow(Arrays.asList("2", "Gordon Park", "Swimming "));
        table.addRow(Arrays.asList("3", "Sejong Park", "Playing Piano"));

        System.out.println(table.renderTable());
        List<String> result = Arrays.asList(table.renderTable().split("\n"));

        assertThat(result.size(), is(7));
        assertThat(result.get(0), is("+----+-------------+---------------+"));
        assertThat(result.get(1), is("| id | name        | hobby         |"));
        assertThat(result.get(2), is("|----+-------------+---------------|"));
        assertThat(result.get(3), is("| 1  | 박세종      | Programming   |"));
        assertThat(result.get(4), is("| 2  | Gordon Park | Swimming      |"));
        assertThat(result.get(5), is("| 3  | Sejong Park | Playing Piano |"));
        assertThat(result.get(6), is("+----+-------------+---------------+"));
    }

    @Test
    public void padRightTest() {
        //given
        //when
        String paddedResult = ">" + AsciiTable4j.padRight("test", 10) + "<";

        //then
        assertThat(paddedResult, is(">test      <"));
    }
}