package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc._testhelper.LogReadableTestBase;
import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.config.LogJdbcProperties;
import net.chandol.logjdbc.logging.LogContext;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultSqlPrinterTest extends LogReadableTestBase {
    private DefaultSqlPrinter sqlPrinter;

    @Before
    public void setup(){
        sqlPrinter = DefaultSqlPrinter.getInstance();
    }

    @Test
    public void SQL포메팅테스트() throws Exception {
        //given
        LogJdbcConfig config = new LogJdbcConfig();
        LogContext context = LogContext.of(config, "SELECT * FROM DUAL");

        //when
        sqlPrinter.printSql(context);

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("\n    SELECT"));
    }

    @Test
    public void 이미_포메팅된_SQL은_포메팅하지_않는다() throws Exception {
        //given
        LogJdbcConfig config = new LogJdbcConfig();
        LogContext context = LogContext.of(config, "SELECT * \nFROM DUAL");

        //when
        sqlPrinter.printSql(context);

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("SELECT * \nFROM DUAL"));
    }

    @Test
    public void format기능은Properties로끌수있다() throws Exception {
        //given
        LogJdbcProperties prop = new LogJdbcProperties();
        prop.setSqlAutoFormatActive(false);

        LogJdbcConfig config = new LogJdbcConfig(prop);
        LogContext context = LogContext.of(config, "SELECT * FROM DUAL");

        //when
        sqlPrinter.printSql(context);

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("SELECT * FROM DUAL"));
    }

    @Test
    public void 여러줄의개행은_하나로_변경(){
        //given
        String source = "\n\n\n\n안녕\n반갑습니다.\n\n\n\n\n\n개행치환하기";

        //when
        String result = sqlPrinter.removeExtraLineBreak(source);

        //then
        assertThat(result, is("\n안녕\n반갑습니다.\n개행치환하기"));
    }

}