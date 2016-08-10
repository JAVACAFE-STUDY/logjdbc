package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc._testhelper.LogReadableTestBase;
import net.chandol.logjdbc.config.LogJdbcConfig;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class DefaultSqlPrinterTest extends LogReadableTestBase {
    @Test
    public void SQL포메팅테스트() throws Exception {
        //given
        DefaultSqlPrinter sqlPrinter = DefaultSqlPrinter.getInstance();

        //when
        sqlPrinter.logSql(new LogJdbcConfig(), "SELECT * FROM DUAL");

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("\n    SELECT"));
    }

    @Test
    public void 이미_포메팅된_SQL은_포메팅하지_않는다() throws Exception {
        //given
        DefaultSqlPrinter sqlPrinter = DefaultSqlPrinter.getInstance();

        //when
        sqlPrinter.logSql(new LogJdbcConfig(), "SELECT * \nFROM DUAL");

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("SELECT * \nFROM DUAL"));
    }

}